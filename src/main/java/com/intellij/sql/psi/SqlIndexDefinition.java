package com.intellij.sql.psi;

import com.intellij.database.model.DasIndex;
import com.intellij.util.ArrayFactory;

/**
 * @author Gregory.Shrago
 */
public interface SqlIndexDefinition extends SqlDefinition, DasIndex {
  SqlIndexDefinition[] EMPTY_ARRAY = new SqlIndexDefinition[0];
  ArrayFactory<SqlIndexDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new SqlIndexDefinition[count];
}
