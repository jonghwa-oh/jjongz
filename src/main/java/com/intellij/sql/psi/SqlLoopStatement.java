package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;

/**
 * @author gregsh
 */
public interface SqlLoopStatement extends SqlStatement, SqlLabelHolder {
  @NotNull
  JBIterable<SqlStatement> getStatements();
}
