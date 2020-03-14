package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlReturnStatement extends SqlStatement {
  @Nullable
  SqlExpression getExpression();
}