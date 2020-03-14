package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlTableColumnsList extends SqlExpression {
  @Nullable
  SqlExpression getTableExpression();
  SqlReferenceExpression getTableReference();
  SqlReferenceList getColumnsReferenceList();
}
