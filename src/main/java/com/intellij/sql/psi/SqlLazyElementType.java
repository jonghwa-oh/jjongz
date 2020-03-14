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
package com.intellij.sql.psi;

import com.intellij.psi.tree.IReparseableElementType;
import com.intellij.sql.dialects.SqlLanguageDialect;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public abstract class SqlLazyElementType extends IReparseableElementType {
  public SqlLazyElementType(@NotNull String debugName, SqlLanguageDialect dialect) {
    super(debugName, dialect);
  }

  public SqlLazyElementType(@NotNull String debugName) {
    super(debugName, SqlLanguage.INSTANCE);
  }
}