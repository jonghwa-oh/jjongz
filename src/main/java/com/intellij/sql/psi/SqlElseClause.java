package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;

public interface SqlElseClause extends SqlClause {
  @NotNull
  JBIterable<SqlCompositeElement> getBody();
}