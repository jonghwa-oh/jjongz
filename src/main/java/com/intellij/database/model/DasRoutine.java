/*
 * Copyright 2000-2011 JetBrains s.r.o.
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

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DasRoutine extends DasObject {

  @Nullable
  String getPackageName();

  @NotNull
  Kind getRoutineKind();

  @NotNull
  Iterable<? extends DasArgument> getArguments();

  @Nullable
  DasArgument getReturnArgument();


  enum Kind {
    NONE,
    FUNCTION,
    PROCEDURE;

    @NotNull
    public static Kind byChar(final char ch) {
      switch (ch) {
        case 'F':
        case 'f':
          return FUNCTION;
        case 'P':
        case 'p':
          return PROCEDURE;
        default:
          return NONE;
      }
    }

    public char toChar() {
      return this == NONE ? 'N' : this == PROCEDURE ? 'P' : 'F';
    }

    @Override
    public String toString() {
      return StringUtil.toLowerCase(name());
    }
  }
}
