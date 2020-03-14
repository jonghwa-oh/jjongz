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

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Functions;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class SearchPath {
  public final List<ObjectPath> elements;

  private SearchPath(@NotNull List<ObjectPath> elements) {
    this.elements = elements;
  }

  @NotNull
  public ObjectPath getCurrent() {
    return elements.get(0);
  }

  @Contract("!null->!null")
  public static SearchPath of(@Nullable ObjectPath p) {
    return p == null ? null : new SearchPath(Collections.singletonList(p));
  }

  @Nullable
  public static SearchPath of(@Nullable List<ObjectPath> p) {
    return p == null || p.isEmpty() ? null : new SearchPath(p);
  }

  @Contract("!null->!null")
  public static ObjectPath getCurrent(@Nullable SearchPath p) {
    return p == null ? null : p.getCurrent();
  }

  @NotNull
  public static List<ObjectPath> getElements(@Nullable SearchPath p) {
    return p == null ? Collections.emptyList() : p.elements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SearchPath multi = (SearchPath)o;
    return elements.equals(multi.elements);
  }

  @Override
  public int hashCode() {
    return elements.hashCode();
  }

  @Override
  public String toString() {
    return StringUtil.join(elements, Functions.TO_STRING(), ",");
  }
}
