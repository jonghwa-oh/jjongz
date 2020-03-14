package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SqlBlockStatement extends SqlStatement, SqlLabelHolder, SqlDefinitionHolder {
  @NotNull
  JBIterable<PsiElement> getBlockElements();

  @Nullable
  SqlExceptionClause getExceptionClause();
}