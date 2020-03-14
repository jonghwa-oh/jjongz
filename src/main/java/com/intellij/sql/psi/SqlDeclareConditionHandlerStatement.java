package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface SqlDeclareConditionHandlerStatement extends SqlDeclareStatement, SqlExceptionHandler {
  @Nullable
  PsiElement getKindKeyword();
}