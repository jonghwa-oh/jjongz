package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;

public interface SqlThenClause extends SqlClause {
  @NotNull
  JBIterable<SqlElement> getBody();
}
