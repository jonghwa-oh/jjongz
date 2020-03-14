package com.intellij.sql.psi;

import com.intellij.util.ArrayFactory;

/**
 * @author Gregory.Shrago
 */
public interface SqlColumnAliasDefinition extends SqlTypedDefinition, SqlNameElement {
  SqlColumnAliasDefinition[] EMPTY_ARRAY = new SqlColumnAliasDefinition[0];
  ArrayFactory<SqlColumnAliasDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new SqlColumnAliasDefinition[count];
}
