package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlElseIfClause extends SqlClause {
  @Nullable
  SqlExpression getCondition();

  @Nullable
  SqlThenClause getThenClause();
}
