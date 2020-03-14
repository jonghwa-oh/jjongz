package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlConditionalLoopStatement extends SqlLoopStatement {
  @Nullable
  SqlExpression getCondition();
}