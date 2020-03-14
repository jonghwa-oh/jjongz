package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiQualifiedReference;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.Nullable;

public interface SqlReference extends PsiPolyVariantReference, PsiQualifiedReference {
  ResolveResult resolveSingle();

  @Nullable
  PsiElement resolveImmediate();
}