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
package com.intellij.database.model;

import com.intellij.database.util.Case;
import com.intellij.database.util.Casing;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ObjectName implements Comparable<ObjectName> {
  public static final ObjectName[] EMPTY_ARRAY = new ObjectName[0];
  public static final ObjectName NULL = null;
  public final String name;
  public final boolean quoted;
  private Integer myInsensitiveHash = null;

  public static ObjectName quoted(@Nullable String name) {
    return create(name, true);
  }

  public static ObjectName plain(@Nullable String name) {
    return create(name, false);
  }

  public static ObjectName create(@Nullable String name, boolean quoted) {
    return name == null ? null : new ObjectName(name, quoted);
  }

  public ObjectName(@NotNull String name, boolean quoted) {
    this.name = name;
    this.quoted = quoted;
  }

  public static boolean equals(@Nullable ObjectName a, @Nullable ObjectName b, @NotNull Casing casing) {
    return a == null || b == null ? a == b : a.equals(b, casing);
  }

  public boolean equals(@NotNull ObjectName other, @NotNull Casing casing) { //todo: non transitive
    if (this == other) return true;
    if (hashCode(casing) != other.hashCode(casing)) return false;
    Case case1 = getCase(casing);
    Case case2 = other.getCase(casing);
    return namesEqual(name, case1, other.name, case2);
  }

  public static boolean namesEqual(@NotNull String name1, @NotNull Case case1,
                                   @NotNull String name2, @NotNull Case case2) {
    if (case1 == Case.MIXED || case2 == Case.MIXED) return StringUtil.equalsIgnoreCase(name1, name2);
    return case1.apply(name1).equals(case2.apply(name2));
  }

  @NotNull
  private Case getCase(@NotNull Casing casing) {
    return casing.choose(!quoted);
  }

  public int hashCode(@NotNull Casing casing) {
    if (casing.plain == Case.EXACT && casing.quoted == Case.EXACT) return name.hashCode();
    if (myInsensitiveHash == null) {
      myInsensitiveHash = StringUtil.stringHashCodeInsensitive(name);
    }
    return myInsensitiveHash;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ObjectName name1 = (ObjectName)o;

    if (quoted != name1.quoted) return false;
    if (!name.equals(name1.name)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (quoted ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return quoted ? "`" + name.replace("`", "``") + "`" : name;
  }

  public static class HashingStrategy implements TObjectHashingStrategy<ObjectName> {
    private final Casing myCasing;

    public HashingStrategy(@NotNull Casing casing) {
      myCasing = casing;
    }

    @Override
    public int computeHashCode(ObjectName object) {
      return object.hashCode(myCasing);
    }

    @Override
    public boolean equals(ObjectName o1, ObjectName o2) {
      if (o1 == null || o2 == null) return o1 == o2;
      return o1.equals(o2, myCasing);
    }
  }

  public static ObjectName[] toArray(@Nullable Iterable<ObjectName> items) {
    if (items == null) return null;
    Collection<ObjectName> collection;
    if (items instanceof Collection) {
      collection = (Collection<ObjectName>)items;
    }
    else {
      collection = ContainerUtil.collect(items.iterator());
    }
    return collection.isEmpty() ? EMPTY_ARRAY : collection.toArray(new ObjectName[0]);
  }

  @Override
  public int compareTo(@NotNull ObjectName o) {
    int res = Comparing.compare(name, o.name);
    return res != 0 ? res : Comparing.compare(quoted, o.quoted);
  }

  public static String name(@Nullable ObjectName name) {
    return name == null ? null : name.name;
  }
}
