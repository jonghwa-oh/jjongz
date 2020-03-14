package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SqlDeclareVariableStatement extends SqlDeclareStatement {
  @NotNull
  JBIterable<SqlVariableDefinition> getVariables();

  @Nullable
  SqlExpression getInitializer();
}