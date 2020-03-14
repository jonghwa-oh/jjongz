package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public interface SqlValuesExpression extends SqlResultSetExpression {

  @NotNull
  JBIterable<SqlExpression> getExpressions();
}
