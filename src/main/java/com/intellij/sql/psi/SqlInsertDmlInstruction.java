package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlInsertDmlInstruction extends SqlDmlInstruction {
  @Nullable
  SqlTableColumnsList getColumnsList();

  @Nullable
  SqlValuesExpression getValuesExpression();

  @Nullable
  SqlQueryExpression getQueryExpression();
}