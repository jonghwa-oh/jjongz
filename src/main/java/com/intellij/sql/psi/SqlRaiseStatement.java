package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlRaiseStatement extends SqlStatement {
  @Nullable
  SqlErrorSpec getErrorSpec();

  @Nullable
  SqlSetClause getOptionsClause();

  boolean isReraise();
}
