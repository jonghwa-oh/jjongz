package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlParenthesizedExpression extends SqlExpression, SqlExpressionList {

  /**
   * @return single inner expression or null if no or several inner expressions.
   */
  @Nullable
  SqlExpression getExpression();

}
