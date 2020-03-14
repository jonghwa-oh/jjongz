package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author gregsh
 */
public interface SqlIfStatement extends SqlStatement {
  @Nullable
  SqlExpression getCondition();

  @NotNull
  JBIterable<SqlClause> getBranches();
}