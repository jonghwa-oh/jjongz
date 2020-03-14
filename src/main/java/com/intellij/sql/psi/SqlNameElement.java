package com.intellij.sql.psi;

import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public interface SqlNameElement extends SqlElement {

  @Override
  @NotNull
  String getName();

  boolean isPlainIdentifier();

  boolean isQuotedIdentifier();

  SqlNameElement setName(String newElementName) throws IncorrectOperationException;

}