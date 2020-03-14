package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlLimitClause extends SqlClause {
  @Nullable
  SqlExpression getRowCountExpression();
}