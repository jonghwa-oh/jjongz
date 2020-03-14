package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

public interface SqlTargetContextProvider extends SqlElement {

  @Nullable
  SqlReferenceExpression getTargetContextExpression();

}