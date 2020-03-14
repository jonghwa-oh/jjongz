package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlWhenClause extends SqlClause {
  @Nullable
  SqlExpression getExpression();
}