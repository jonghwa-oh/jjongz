package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlTableExpression extends SqlExpression {

  @Override
  @NotNull
  SqlTableType getSqlType();

  @Nullable
  SqlFromClause getFromClause();

  @Nullable
  SqlWhereClause getWhereClause();

  //should be part of query
  @Deprecated
  @Nullable
  SqlOrderByClause getOrderByClause();

  @Nullable
  SqlGroupByClause getGroupByClause();

  @Nullable
  SqlWhenClause getWhenClause();

  @Nullable
  SqlHavingClause getHavingClause();
}