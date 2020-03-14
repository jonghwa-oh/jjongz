package com.intellij.sql.psi;

import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;

public interface SqlExceptionHandler extends SqlControlFlowHolder {
  @NotNull
  JBIterable<SqlErrorSpec> getErrorSpecs();

  @Override
  @NotNull
  JBIterable<SqlStatement> getBody();
}
