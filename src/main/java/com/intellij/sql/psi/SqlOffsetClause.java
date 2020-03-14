package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlOffsetClause extends SqlClause {
  @Nullable
  SqlExpression getOffsetExpression();
}
