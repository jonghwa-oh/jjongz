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

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author gregsh
 */
public final class Casing {

  public final Case plain;
  public final Case quoted;

  @NotNull
  public static Casing create(@NotNull Case plain, @NotNull Case quoted) {
    return new Casing(plain, quoted);
  }

  private Casing(@NotNull Case plain, @NotNull Case quoted) {
    this.plain = plain;
    this.quoted = quoted;
  }

  @NotNull
  public Case choose(boolean isPlain) {
    return  isPlain ? plain : quoted;
  }

  @NotNull
  public String specification() {
    if (quoted == Case.EXACT) {
      return caseToString(plain);
    }
    else {
      return caseToString(plain) + '/' + caseToString(quoted);
    }
  }

  @NotNull
  public static Casing of(@Nullable String str) {
    if (str == null) return EXACT; // by default
    String[] items = str.split("/");
    int n = items.length;
    Case c1 = n >= 1 ? caseFromString(items[0]) : Case.EXACT;
    Case c2 = n >= 2 ? caseFromString(items[1]) : Case.EXACT;
    return create(c1, c2);
  }

  @NotNull
  private static String caseToString(@NotNull Case caze) {
    return StringUtil.toLowerCase(caze.name());
  }

  @NotNull
  private static Case caseFromString(@NotNull String str) {
    char c = str.length() >= 1 ? Character.toLowerCase(str.charAt(0)) : '\0';
    switch (c) {
      case 'm': return Case.MIXED;
      case 'u': return Case.UPPER;
      case 'l': return Case.LOWER;
      default:  return Case.EXACT;
    }
  }

  @Override
  public String toString() {
    return specification();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Casing casing = (Casing)o;
    return plain == casing.plain && quoted == casing.quoted;
  }

  @Override
  public int hashCode() {
    return Objects.hash(plain, quoted);
  }

  public static final Casing EXACT = create(Case.EXACT, Case.EXACT);
}
