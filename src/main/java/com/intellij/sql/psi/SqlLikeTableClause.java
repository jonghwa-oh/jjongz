package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlLikeTableClause extends SqlClause {
  @Nullable
  SqlReferenceExpression getTableReference();

  @NotNull
  List<SqlReferenceExpression> getTableReferences();
}
