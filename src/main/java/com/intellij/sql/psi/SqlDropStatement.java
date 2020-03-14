package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlDropStatement extends SqlDdlStatement {
  @Nullable
  SqlReferenceExpression getTargetExpression();

  @Nullable
  SqlReferenceExpression getTargetContextExpression();
}