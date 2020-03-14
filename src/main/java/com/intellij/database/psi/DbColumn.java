package com.intellij.database.psi;

import com.intellij.database.model.PsiColumn;

/**
 * @author Gregory.Shrago
 */
public interface DbColumn extends DbElement, PsiColumn {

  @Override
  DbTable getParent();

  @Override
  DbTable getTable();
}
