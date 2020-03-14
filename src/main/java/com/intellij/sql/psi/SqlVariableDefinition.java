package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlVariableDefinition extends SqlTypedDefinition {
  @Nullable
  SqlExpression getInitializer();

  @Nullable
  SqlTypeElement getTypeElement();
}
