package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlBetweenExpression extends SqlExpression {
  @NotNull
  SqlExpression getSubject();

  @NotNull
  PsiElement getOperation();

  @Nullable
  SqlExpression getLowerBound();

  @Nullable
  SqlExpression getUpperBound();
}
