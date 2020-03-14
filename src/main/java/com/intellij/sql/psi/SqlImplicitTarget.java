package com.intellij.sql.psi;

import com.intellij.pom.PomNamedTarget;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface SqlImplicitTarget extends PomNamedTarget {
  @NotNull
  PsiElement getContext();
}
