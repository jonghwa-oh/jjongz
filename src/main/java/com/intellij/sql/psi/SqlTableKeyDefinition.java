package com.intellij.sql.psi;

import com.intellij.database.model.DasTableKey;
import com.intellij.util.ArrayFactory;

/**
 * @author Gregory.Shrago
 */
public interface SqlTableKeyDefinition extends SqlDefinition, DasTableKey {
  SqlTableKeyDefinition[] EMPTY_ARRAY = new SqlTableKeyDefinition[0];
  ArrayFactory<SqlTableKeyDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new SqlTableKeyDefinition[count];
}