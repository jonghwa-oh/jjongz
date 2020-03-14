package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlCreateTableStatement extends SqlCreateStatement, SqlTableDefinition {

  @NotNull
  List<? extends SqlColumnDefinition> getDeclaredColumns();

  @NotNull
  List<? extends SqlTableKeyDefinition> getDeclaredKeys();

  @NotNull
  List<? extends SqlForeignKeyDefinition> getDeclaredForeignKeys();

  @NotNull
  List<? extends SqlIndexDefinition> getDeclaredIndices();
}
