package com.intellij.sql.psi;

import com.intellij.database.model.DasForeignKey;
import com.intellij.util.ArrayFactory;

/**
 * @author Gregory.Shrago
 */
public interface SqlForeignKeyDefinition extends SqlDefinition, DasForeignKey {
  SqlForeignKeyDefinition[] EMPTY_ARRAY = new SqlForeignKeyDefinition[0];
  ArrayFactory<SqlForeignKeyDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new SqlForeignKeyDefinition[count];
}