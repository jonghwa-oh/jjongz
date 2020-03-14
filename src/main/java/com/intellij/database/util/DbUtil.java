/*
 * Copyright 2000-2015 JetBrains s.r.o.
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
package com.intellij.database.util;

import com.intellij.database.model.DasObject;
import com.intellij.database.psi.DbDataSource;
import com.intellij.database.psi.DbElement;
import com.intellij.database.psi.DbPsiFacade;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.Function;
import com.intellij.util.FunctionUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.Interner;
import com.intellij.util.containers.JBIterable;
import com.intellij.util.containers.WeakStringInterner;
import com.intellij.util.text.CaseInsensitiveStringHashingStrategy;
import com.intellij.util.text.UniqueNameGenerator;
import gnu.trove.THashSet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gregory.Shrago
 */
public class DbUtil {

  private DbUtil() {
  }

  private static final Interner<String> INTERNER = new WeakStringInterner();

  public static final Function<DbElement, Object> TO_DELEGATE = e -> e.getDelegate();

  @NotNull
  public static JBIterable<DbDataSource> getDataSources(@NotNull Project project) {
    return JBIterable.from(DbPsiFacade.getInstance(project).getDataSources());
  }

  @Contract("!null->!null")
  public static String intern2(@Nullable String str) {
    return str == null ? null : INTERNER.intern(str);
  }

  @NotNull
  public static String intern(@Nullable String str) {
    return intern(str, "");
  }

  @Contract("_, !null->!null")
  public static String intern(@Nullable String str, @Nullable String def) {
    return str == null || str.isEmpty() ? def : INTERNER.intern(str);
  }

  @NotNull
  public static Set<String> getExistingDataSourceNames(@NotNull Project project) {
    Set<String> result = new THashSet<>(CaseInsensitiveStringHashingStrategy.INSTANCE);
    for (DbDataSource dataSource : DbPsiFacade.getInstance(project).getDataSources()) {
      ContainerUtil.addIfNotNull(result, dataSource.getName());
    }
    return result;
  }

  @NotNull
  public static String generateUniqueDataSourceName(@NotNull Project project, @NotNull String baseName) {
    return generateUniqueName(baseName, getExistingDataSourceNames(project));
  }

  @NotNull
  public static String generateUniqueName(@NotNull String baseName, @NotNull Set<String> existingNames) {
    Matcher matcher = Pattern.compile("([^\\[]*)(?:\\[\\d+])?").matcher(baseName);
    String fixedName = matcher.matches() ? matcher.group(1).trim() : baseName;
    return new UniqueNameGenerator(existingNames, FunctionUtil.id()).
      generateUniqueName(fixedName, "", "", " [", "]");
  }

  public static boolean isCaseSensitive(@Nullable String name, @NotNull DbElement source) {
    if (StringUtil.isEmpty(name)) return false;
    Casing modes = source.getDataSource().getModel().getCasing(source.getKind(), source);
    return isCaseSensitive(name, modes);
  }

  public static boolean isCaseSensitive(@Nullable String name, Casing modes) {
    return Case.guessForName(name, null, modes.plain, modes.quoted) == Case.EXACT;
  }

  @NotNull
  public static String[] intern(@NotNull String[] arr) {
    return ContainerUtil.map(arr, str -> intern(str, null), ArrayUtilRt.EMPTY_STRING_ARRAY);
  }

  @Nullable
  public static DasObject getDasObject(@Nullable Object object) {
    return object instanceof DbDataSource ? (DasObject)object :
           object instanceof DbElement ? (DasObject)((DbElement)object).getDelegate() :
           object instanceof DasObject ? (DasObject)object : null;
  }
}
