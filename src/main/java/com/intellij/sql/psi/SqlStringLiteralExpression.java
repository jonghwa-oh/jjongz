package com.intellij.sql.psi;

import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.PsiLiteralValue;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlStringLiteralExpression extends SqlLiteralExpression, PsiLanguageInjectionHost, PsiLiteralValue {
  @Nullable
  @Override
  String getValue();
}
