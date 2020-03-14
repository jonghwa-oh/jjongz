package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

/**
 * @author Gregory.Shrago
 */
public interface SqlExtraDeclarationsProvider extends SqlElement {
  boolean processExtraDeclarations(final PsiScopeProcessor processor, final ResolveState state, final PsiElement lastParent, final PsiElement place);
}
