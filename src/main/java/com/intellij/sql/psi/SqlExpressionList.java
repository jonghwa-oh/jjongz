package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlExpressionList extends SqlElement{
  @NotNull
  List<SqlExpression> getExpressionList();
}