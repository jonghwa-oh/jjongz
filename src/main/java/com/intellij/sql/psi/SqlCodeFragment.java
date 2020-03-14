package com.intellij.sql.psi;

import com.intellij.psi.PsiCodeFragment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;

/**
 * @author ignatov
 */
public interface SqlCodeFragment extends SqlFile, PsiCodeFragment {
  IElementType getElementType();

  void setContext(@Nullable PsiElement context);
}
