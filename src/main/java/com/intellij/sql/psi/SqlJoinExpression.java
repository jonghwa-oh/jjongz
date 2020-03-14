package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlJoinExpression extends SqlBinaryExpression, SqlLateralAwareExpression {
  @NotNull
  @Override
  SqlTableType getSqlType();

  @NotNull
  @Override
  SqlTableType getSqlType(@Nullable PsiElement end);

  boolean isLeft();

  boolean isRight();
}
