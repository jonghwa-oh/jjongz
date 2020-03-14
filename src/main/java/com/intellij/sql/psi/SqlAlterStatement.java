package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlAlterStatement extends SqlDdlStatement {
  @Nullable
  SqlReferenceExpression getAlterTargetReference();

  @NotNull
  SqlAlterInstruction[] getAlterInstructions();
}
