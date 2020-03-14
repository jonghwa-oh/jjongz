package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlSelectStatement extends SqlStatement{
  @Nullable
  SqlQueryExpression getQueryExpression();
  @Nullable
  SqlUpdatabilityClause getUpdatabilityClause();
}
