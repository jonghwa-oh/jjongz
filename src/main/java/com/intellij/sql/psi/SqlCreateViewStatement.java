package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public interface SqlCreateViewStatement extends SqlCreateStatement, SqlTableDefinition {
  @NotNull
  SqlDefinition[] getColumnAliases();

}
