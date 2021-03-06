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

import org.jetbrains.annotations.Nullable;

/**
 * @author Leonid Bushuev
 **/
public enum ClusterType {

  INDEX('I'),
  HASH('H');


  final char code;

  ClusterType(char code) {
    this.code = code;
  }


  @Nullable
  public static ClusterType of(final char code) {
    switch (code) {
      case 'I':
      case 'i':
        return INDEX;
      case 'H':
      case 'h':
        return HASH;
      default:
        return null;
    }
  }

  @Nullable
  public static ClusterType of(@Nullable final String code) {
    return code != null && code.length() > 0 ? of(code.charAt(0)) : null;
  }

}
