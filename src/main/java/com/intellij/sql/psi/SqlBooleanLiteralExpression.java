package com.intellij.sql.psi;

public interface SqlBooleanLiteralExpression extends SqlLiteralExpression {
  boolean isFalse();
  boolean isTrue();
}
