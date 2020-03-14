package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlQueryExpression extends SqlResultSetExpression {
  @Nullable
  SqlTableExpression getTableExpression();

  SqlSelectClause getSelectClause();

  @NotNull
  JBIterable<SqlLimitClause> getLimitClauses();

  @NotNull
  JBIterable<SqlOffsetClause> getOffsetClauses();
}