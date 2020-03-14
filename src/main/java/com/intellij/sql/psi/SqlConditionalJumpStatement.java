package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlConditionalJumpStatement extends SqlJumpStatement {
  @Nullable
  SqlWhenClause getWhenClause();
}