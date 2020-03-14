package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlUnaryExpression extends SqlOperatorExpression {
  @Override
  @NotNull
  IElementType getOpSign();

  @Override
  @NotNull
  PsiElement getOpSignElement();

  @Nullable
  SqlExpression getOperand();
}