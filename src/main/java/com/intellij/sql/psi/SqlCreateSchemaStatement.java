package com.intellij.sql.psi;

import com.intellij.database.model.DasNamespace;

/**
 * @author Gregory.Shrago
 */
public interface SqlCreateSchemaStatement extends SqlCreateStatement, DasNamespace {
  @Override
  SqlReferenceExpression getNameElement();
}
