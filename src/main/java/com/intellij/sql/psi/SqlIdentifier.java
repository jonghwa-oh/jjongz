package com.intellij.sql.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.util.IncorrectOperationException;

/**
 * @author Gregory.Shrago
 */
public interface SqlIdentifier extends SqlNameElement, SqlPrefixedElement {

  @Override
  SqlIdentifier setName(String newElementName) throws IncorrectOperationException;

  TextRange getNameRange();
}