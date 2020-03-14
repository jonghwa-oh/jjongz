package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlParameter extends SqlExpression {
  @Nullable
  SqlNameElement getNameElement();

  @Override
  @NotNull
  String getName();
}
