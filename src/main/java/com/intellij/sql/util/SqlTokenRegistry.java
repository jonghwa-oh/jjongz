/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.sql.util;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.tree.IElementType;
import com.intellij.sql.psi.SqlCompositeElementType;
import com.intellij.sql.psi.SqlKeywordTokenType;
import com.intellij.sql.psi.SqlTokenType;
import com.intellij.util.*;
import com.intellij.util.containers.JBIterable;
import com.intellij.util.text.CaseInsensitiveStringHashingStrategy;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Gregory.Shrago
 */
public class SqlTokenRegistry {

  private static final THashMap<String, SqlTokenType> ourTokensMap = newTokenMap();
  private static final THashMap<Class, Map<String, SqlKeywordTokenType>> ourClassTokensMap = new THashMap<>();
  private static final THashMap<String, IElementType> ourCompositeMap = new THashMap<>();

  private SqlTokenRegistry() {
  }

  @NotNull
  public static SqlTokenType getType(@NotNull String text) {
    return getImpl(ourTokensMap, text, o -> (Character.isLetter(o.charAt(0)) ? new SqlKeywordTokenType(o) : new SqlTokenType(o)));
  }

  @NotNull
  public static SqlTokenType getType(@NotNull String text, @NotNull Function<String, ? extends SqlTokenType> factory) {
    return getImpl(ourTokensMap, text, factory);
  }

  /**
   * For *ElementTypes only
   */
  @NotNull
  public static SqlCompositeElementType getCompositeType(@NotNull String debugName) {
    return getImpl(ourCompositeMap, debugName, o -> new SqlCompositeElementType(o));
  }

  /**
   * For *ElementTypes only
   */
  @NotNull
  public static <T extends IElementType> T getCompositeType(@NotNull String debugName,
                                                            @NotNull Function<String, T> factory) {
    return getImpl(ourCompositeMap, debugName, factory);
  }


  private static Consumer<String> ourTestInterceptor;

  @TestOnly
  public static void setTestInterceptor(@NotNull Consumer<String> testInterceptor) {
    ourTestInterceptor = testInterceptor;
  }

  @NotNull
  private synchronized static <T extends IElementType> T getImpl(@NotNull Map<String, ? super T> map,
                                                                 @NotNull String debugName,
                                                                 @NotNull Function<String, T> factory) {
    //noinspection unchecked
    T type = (T)map.get(debugName);
    if (type == null) {
      map.put(debugName, type = ObjectUtils.notNull(factory.fun(debugName)));
      if (ourTestInterceptor != null) {
        ourTestInterceptor.consume(debugName);
      }
    }
    return type;
  }

  @Nullable
  public static synchronized SqlTokenType findTokenType(@NotNull String text) {
    return ourTokensMap.get(text);
  }

  @Nullable
  public static synchronized IElementType findCompositeType(@NotNull String text) {
    return ourCompositeMap.get(text);
  }

  @Nullable
  public static synchronized IElementType findType(@NotNull String text) {
    SqlTokenType token = ourTokensMap.get(text);
    if (token != null) return token;
    return ourCompositeMap.get(text);
  }

  @NotNull
  public static synchronized Iterable<IElementType> getAllCompositeTypes() {
    return ourCompositeMap.values();
  }

  public static void initTypeMap(@NotNull Class clazz, @Nullable Set<String> exclude) {
    Map<String, SqlKeywordTokenType> map = buildTokenMap(clazz, newTokenMap());
    if (exclude != null) {
      map.keySet().removeAll(exclude);
    }
    setTypeMap(clazz, map, false);
  }

  public static synchronized void addTokensToClassMap(@NotNull Class clazz, @NotNull Set<String> keywordSet) {
    Map<String, SqlKeywordTokenType> existing = ourClassTokensMap.get(clazz);
    Map<String, SqlKeywordTokenType> map = existing == null ? newTokenMap() : existing;
    for (String s : keywordSet) {
      if (!map.containsKey(s)) {
        String upperCase = StringUtil.toUpperCase(s);
        map.put(upperCase, (SqlKeywordTokenType)getType(upperCase));
      }
    }
    setTypeMap(clazz, map, true);
  }

  private static synchronized void setTypeMap(@NotNull Class clazz, @NotNull Map<String, SqlKeywordTokenType> map, boolean force) {
    if (!force && ourClassTokensMap.containsKey(clazz)) return;
    ourClassTokensMap.put(clazz, map);
  }

  @NotNull
  public static NullableFunction<String, IElementType> getTokenProvider(@NotNull Class clazz) {
    return getSafeMap(clazz)::get;
  }

  public static Set<String> getTokens(final Class clazz) {
    return Collections.unmodifiableSet(getSafeMap(clazz).keySet());
  }

  @NotNull
  private synchronized static Map<String, SqlKeywordTokenType> getSafeMap(@NotNull Class clazz) {
    Map<String, SqlKeywordTokenType> result = ourClassTokensMap.get(clazz);
    if (result == null) {
      throw new AssertionError(clazz + " token map not initialized");
    }
    return result;
  }

  private static <T extends IElementType> THashMap<String, T> newTokenMap() {
    return new THashMap<>(CaseInsensitiveStringHashingStrategy.INSTANCE);
  }

  private static Map<String, SqlKeywordTokenType> buildTokenMap(@NotNull Class clazz, @NotNull Map<String, SqlKeywordTokenType> map) {
    consumeStaticFieldsInOrder(clazz, (field, value) -> {
      if (value instanceof SqlKeywordTokenType) {
        SqlKeywordTokenType tokenType = (SqlKeywordTokenType)value;
        map.put(tokenType.toString(), tokenType);
      }
    });
    return map;
  }

  public static void consumeStaticFieldsInOrder(@NotNull Class clazz, @NotNull PairConsumer<Field, Object> processor) {
    JBIterable<Field> fields = ReflectionUtil.classTraverser(clazz)
      .postOrderDfsTraversal()
      .flatMap(o -> JBIterable.of(o.getDeclaredFields()))
      .filter(o -> Modifier.isStatic(o.getModifiers()));
    for (Field field : fields) {
      try {
        processor.consume(field, field.get(null));
      }
      catch (IllegalAccessException ignored) {
      }
    }
  }

  public static void ensureInterfacesAreInitializedInOrder(@NotNull Class<?> factoryClass) {
    // as far as interfaces are not initialized automatically (JLS 12.4.1)
    // make sure all superinterfaces are initialized from top to bottom
    JBIterable<Class<?>> interfaces = ReflectionUtil.classTraverser(factoryClass)
      .postOrderDfsTraversal()
      .filter(o -> o.isInterface());
    for (Class<?> aClass : interfaces) {
      Field[] fields = aClass.getDeclaredFields();
      if (fields.length == 0) continue;
      try {
        fields[0].get(null);
      }
      catch (IllegalAccessException ignored) {
      }
    }
  }
}
