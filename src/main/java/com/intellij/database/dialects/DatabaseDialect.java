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
package com.intellij.database.dialects;

import com.intellij.database.Dbms;
import com.intellij.database.DbmsExtension;
import com.intellij.database.model.DataType;
import org.jetbrains.annotations.NotNull;

public interface DatabaseDialect {

  DbmsExtension<DatabaseDialect> EP = new DbmsExtension<>("com.intellij.database.dialect");

  @NotNull
  Dbms getDbms();

  @NotNull
  String getDisplayName();

  boolean canUnquoteAlias(@NotNull String alias, boolean afterAS);

  @NotNull
  String getTypeName(DataType dataType);

  int getJavaTypeForNativeType(@NotNull String nativeColumnTypeName);

  String getBinaryLiteralString(byte[] binaryData);

  @Deprecated
  boolean supportsAutoIncrement();
  @Deprecated
  boolean supportsAlterAutoIncrement();

  @Deprecated
  boolean supportsEmptyTables();

  boolean supportsInsertInto();

  @Deprecated
  boolean supportsAlterColumnNull();

  @Deprecated
  boolean supportsAlterColumnType();

  @Deprecated
  boolean supportsAlterColumnDefault();

  boolean supportsViewDefinition();

  boolean supportsSystemViewDefinition();

  boolean supportsTableDefinition();

  boolean supportsTableInfo();

  boolean supportsProcedureDefinition();

  boolean supportsPackageDefinition();

  boolean supportsSequenceDefinition();

  boolean supportsSynonymDefinition();

  boolean supportsQualifiedAsteriskInCalls();

  boolean supportsCommonTableExpression();

  @Deprecated
  boolean supportsNotNullColumns();

  @Deprecated
  boolean supportsDefaults();

  @Deprecated
  boolean supportsUniqueIndices();

  @Deprecated
  boolean supportsIndexColumnOrdering();

  boolean similarTo(@NotNull DatabaseDialect dialect);
}
