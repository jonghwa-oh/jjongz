package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SqlExceptionClause extends SqlClause {
  @Nullable
  PsiElement getKeyword();

  @NotNull
  JBIterable<SqlExceptionWhenClause> getHandlers();
}
