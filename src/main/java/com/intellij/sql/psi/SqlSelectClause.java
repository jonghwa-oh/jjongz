package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlSelectClause extends SqlQueryClause {
  @NotNull
  List<SqlSelectOption> getOptions();
  @NotNull
  List<SqlExpression> getExpressions();
}
