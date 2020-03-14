package com.intellij.sql.psi;

import com.intellij.database.model.ObjectKind;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlReferenceExpression extends SqlExpression, SqlNameElement {

  @Nullable
  SqlExpression getQualifierExpression();

  @Nullable
  SqlIdentifier getIdentifier();

  @Override
  @NotNull
  SqlReference getReference();

  ResolveResult[] multiResolve(boolean incompleteCode);

  ResolveResult resolveSingle();

  @Nullable
  PsiElement resolve();

  @NotNull
  SqlReferenceElementType getReferenceElementType();

  @NotNull
  String getReferencePart(@NotNull ObjectKind kind);
}
