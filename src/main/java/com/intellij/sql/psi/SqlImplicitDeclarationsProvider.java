package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

/**
 * @author Gregory.Shrago
 */
public interface SqlImplicitDeclarationsProvider extends SqlElement {
  boolean processImplicitContextDeclarations(final PsiScopeProcessor processor, final ResolveState state, final PsiElement lastParent, final PsiElement place);

  boolean stopProcessingIfFoundInImplicitContext();
}
