package com.intellij.sql.psi;

/**
 * @author Gregory.Shrago
 */
public interface SqlJoinConditionClause extends SqlClause {
  SqlExpression getExpression();
}