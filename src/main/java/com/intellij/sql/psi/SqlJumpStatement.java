package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlJumpStatement extends SqlStatement {
  @Nullable
  SqlReferenceExpression getLabelReference();
}