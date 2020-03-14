package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlInsertStatement extends SqlDmlStatement {
  @Nullable SqlInsertDmlInstruction getDmlInstruction();
}
