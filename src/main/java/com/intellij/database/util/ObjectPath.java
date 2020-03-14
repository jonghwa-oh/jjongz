/*
 * Copyright 2000-2017 JetBrains s.r.o.
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

import com.intellij.database.model.CasingProvider;
import com.intellij.database.model.ObjectKind;
import com.intellij.database.model.ObjectName;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PairFunction;
import com.intellij.util.ThrowableConsumer;
import com.intellij.util.containers.Interner;
import com.intellij.util.containers.WeakInterner;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author gregsh
 */
public class ObjectPath {
  private static final Interner<ObjectPath> ourInterner = new WeakInterner<>(new TObjectHashingStrategy<ObjectPath>() {
    @Override
    public int computeHashCode(ObjectPath object) {
      int result = System.identityHashCode(object.parent);
      result = 31 * result + object.kind.hashCode();
      result = 31 * result + object.name.hashCode();
      result = 31 * result + Boolean.hashCode(object.isQuoted());
      result = 31 * result + Comparing.hashcode(object.getIdentity());
      return result;
    }

    @Override
    public boolean equals(ObjectPath o1, ObjectPath o2) {
      if (o1 == o2 || o1 == null || o2 == null) return o1 == o2;
      return o1.parent == o2.parent && o1.matches(o2);
    }
  });

  public final ObjectPath parent;

  public final ObjectKind kind;
  public final String name;

  private ObjectPath(@NotNull String name, @NotNull ObjectKind kind, @Nullable ObjectPath parent) {
    this.name = name;
    this.kind = kind;
    this.parent = parent;
  }

  public boolean isQuoted() {
    return true;
  }

  @Nullable
  public String getIdentity() {
    return null;
  }

  @NotNull
  public ObjectPath append(@NotNull String name, @NotNull ObjectKind kind) {
    return create(name, kind, true, null, this);
  }

  public ObjectPath append(@NotNull String name, @NotNull ObjectKind kind, boolean quoted, String identity) {
    return create(name, kind, quoted, identity, this);
  }

  public int getSize() {
    int result = 0;
    for (ObjectPath p = this; p != null; p = p.parent) {
      result ++;
    }
    return result;
  }

  @Nullable
  public ObjectPath getParent(int steps) {
    ObjectPath result = this;
    for (int i = steps; i > 0 && result != null; i--) result = result.parent;
    return result;
  }

  @Nullable
  public ObjectPath findParent(ObjectKind kind, boolean strict) {
    for (ObjectPath p = strict ? parent : this; p != null; p = p.parent) {
      if (p.kind == kind) return p;
    }
    return null;
  }

  public boolean isAncestorOf(@Nullable ObjectPath child, boolean strict) {
    if (strict && this == child) return false;
    while (child != null && child != this) child = child.parent;
    return this == child;
  }

  @NotNull
  public String getDisplayName() {
    return reduce(
      new StringBuilder(), (sb, p) ->
        (sb.length() > 0 && p.name.length() > 0 ? sb.append(".") : sb).append(p.name))
      .toString();
  }

  @NotNull
  public String getName() {
    return name;
  }

  public <T> T reduce(T t, @NotNull PairFunction<T, ObjectPath, T> reducer) {
    T result = t;
    if (parent != null) result = parent.reduce(t, reducer);
    return reducer.fun(result, this);
  }

  @NotNull
  public static ObjectPath create(@NotNull String name, @NotNull ObjectKind kind) {
    return create(name, kind, true, null, null);
  }

  public static ObjectPath create(@NotNull String name,
                                  @NotNull ObjectKind kind,
                                  boolean quoted,
                                  @Nullable String identity,
                                  @Nullable ObjectPath parent) {
    return ourInterner.intern(createInner(name, kind, quoted, identity, parent));
  }

  public static ObjectPath copyUnder(@NotNull ObjectPath child, @Nullable ObjectPath parent) {
    return create(child.name, child.kind, child.isQuoted(), child.getIdentity(), parent);
  }

  @NotNull
  private static ObjectPath createInner(@NotNull String name,
                                        @NotNull ObjectKind kind,
                                        boolean quoted,
                                        @Nullable String identity,
                                        @Nullable ObjectPath parent) {
    if (quoted && identity == null) return new ObjectPath(name, kind, parent);
    if (identity == null) return new ObjectPath(name, kind, parent) {
      @Override public boolean isQuoted() { return false; }
    };
    if (quoted) new ObjectPath(name, kind, parent) {
      @Override public String getIdentity() { return identity; }
    };
    return new ObjectPath(name, kind, parent) {
      @Override public boolean isQuoted() { return quoted; }
      @Override public String getIdentity() { return identity; }
    };
  }

  @NotNull
  public static ObjectPath create(@NotNull ObjectKind kind, @NotNull Iterable<String> names) {
    ObjectPath result = null;
    for (Iterator<String> it = names.iterator(); it.hasNext(); ) {
      result = create(it.next(), !it.hasNext() ? kind : ObjectKind.NONE, true, null, result);
    }
    return ObjectUtils.notNull(result);
  }

  public <T extends Throwable> void forEach(@NotNull ThrowableConsumer<ObjectPath, T> r) throws T {
    forEachInner(this, r);
  }

  private static <T extends Throwable> void forEachInner(@NotNull ObjectPath p, @NotNull ThrowableConsumer<ObjectPath, T> r) throws T {
    if (p.parent != null) forEachInner(p.parent, r);
    r.consume(p);
  }

  @Override
  public String toString() {
    String id = getIdentity();
    return (parent != null ? parent + "/" : "") +
           (kind == ObjectKind.NONE ? "" : kind.name() + ":") + name + (id == null ? "" : "@" + id);
  }

  public boolean matches(@NotNull ObjectPath o) {
    if (this == o) return true;
    if (!kind.equals(o.kind)) return false;
    if (!name.equals(o.name)) return false;
    if (!Comparing.equal(isQuoted(), o.isQuoted())) return false;
    if (!Comparing.equal(getIdentity(), o.getIdentity())) return false;
    return true;
  }

  @NotNull
  public String serialize() {
    return reduce(new StringBuilder(), (b, p) -> {
      if (b.length() != 0) b.append("/");
      b.append(escape(p.kind.code()))
        .append("/");
      if (p.isQuoted()) b.append('"');
      b.append(escape(p.name));
      if (p.isQuoted()) b.append('"');
      if (p.getIdentity() != null) b.append(escape(p.getIdentity()));
      return b;
    }).toString();
  }

  @Nullable
  public static ObjectPath deserialize(@NotNull String s) {
    ObjectPath result = null;
    ObjectKind kind = null;
    for (String chunk : StringUtil.split(s, "/", true, false)) {
      if (kind == null) {
        kind = ObjectKind.ourKinds.get(unescape(chunk));
        if (kind == null) {
          kind = ObjectKind.NONE;
        }
      }
      else {
        int idIdx = chunk.indexOf(":");
        String id = idIdx == -1 ? null : unescape(chunk.substring(idIdx + 1));
        if (idIdx != -1) chunk = chunk.substring(0, idIdx);
        boolean quoted = chunk.startsWith("\"") && chunk.endsWith("\"");
        if (quoted) chunk = chunk.substring(1, chunk.length() - 1);
        String name = unescape(chunk);
        result = create(name, kind, true, null, result);
        kind = null;
      }
    }
    if (kind != null) {
      result = create("", kind, true, null, result);
    }
    return result;
  }

  private static final List<String> TO_ESCAPE = Arrays.asList("/", "&", "\"", ":");
  private static final List<String> TO_UNESCAPE = Arrays.asList("&eslash;", "&amp;", "&quot;", "&colon;");
  private static String escape(String s) {
    return StringUtil.replace(s, TO_ESCAPE, TO_UNESCAPE);
  }

  private static String unescape(String s) {
    return StringUtil.replace(s, TO_UNESCAPE, TO_ESCAPE);
  }

  public static boolean namesEqual(@Nullable ObjectPath p1, @Nullable ObjectPath p2, @NotNull CasingProvider casingProvider) {
    return p1 == null || p2 == null ? p1 == p2 : ObjectName.namesEqual(
      p1.name, casingProvider.getCasing(p1.kind, null).choose(!p1.isQuoted()),
      p2.name, casingProvider.getCasing(p2.kind, null).choose(!p2.isQuoted()));
  }

  public static boolean namesEqual(@Nullable ObjectPath p1, @Nullable ObjectPath p2, @NotNull Casing casing) {
    return p1 == null || p2 == null ? p1 == p2 : ObjectName.namesEqual(p1.name, casing.choose(!p1.isQuoted()), p2.name, casing.choose(!p2.isQuoted()));
  }
}
