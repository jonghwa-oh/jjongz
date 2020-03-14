package com.intellij.sql.psi;

import com.intellij.psi.NavigatablePsiElement;

/**
 * @author Gregory.Shrago
 */
public interface SqlElement extends NavigatablePsiElement {
  void accept(final SqlVisitor visitor);
  void acceptChildren(final SqlVisitor visitor);
}
