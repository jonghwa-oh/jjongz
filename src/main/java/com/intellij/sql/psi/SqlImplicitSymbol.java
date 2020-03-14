package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface SqlImplicitSymbol extends SqlImplicitTarget {
  @Nullable
  PsiElement getTargetDefinition();
}