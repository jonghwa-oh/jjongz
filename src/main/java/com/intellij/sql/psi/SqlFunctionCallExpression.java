package com.intellij.sql.psi;

import com.intellij.sql.dialects.BuiltinFunction;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlFunctionCallExpression extends SqlExpression {
  @Nullable
  SqlReferenceExpression getNameElement();

  @Nullable
  SqlExpression getCallableExpression();

  @Nullable
  SqlExpressionList getParameterList();

  @Nullable
  BuiltinFunction getFunctionDefinition();
}