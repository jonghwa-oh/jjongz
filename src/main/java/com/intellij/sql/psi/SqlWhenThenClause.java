package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlWhenThenClause extends SqlClause {
  @Nullable
  SqlWhenClause getWhenClause();

  @Nullable
  SqlThenClause getThenClause();
}