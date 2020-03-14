package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlRevokeStatement extends SqlStatement {
  @Nullable
  SqlReferenceExpression getTargetContextExpression();
}
