package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlBinaryExpression extends SqlOperatorExpression {
  @NotNull
  SqlExpression getLOperand();

  @Nullable
  SqlExpression getROperand();

  boolean isNot();

  @Override
  @NotNull
  IElementType getOpSign();

  @Override
  @NotNull
  PsiElement getOpSignElement();
}
