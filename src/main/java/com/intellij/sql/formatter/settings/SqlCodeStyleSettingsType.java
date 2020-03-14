package com.intellij.sql.formatter.settings;

import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum SqlCodeStyleSettingsType {
  
  CASE_SETTINGS("case", null),
  QUERIES_SETTINGS("queries", null),
  DDL_SETTINGS("ddl", null),
  CODE_SETTINGS("code", null),
  EXPRESSIONS_SETTINGS("expressions", null),
  INDENT_SETTINGS("indent", LanguageCodeStyleSettingsProvider.SettingsType.INDENT_SETTINGS),
  WRAPPING_SETTINGS("wrapping", LanguageCodeStyleSettingsProvider.SettingsType.WRAPPING_AND_BRACES_SETTINGS),
  GENERATION_SETTINGS("generation", null);
  
  @NotNull
  public final String code;
  
  @Nullable
  public final LanguageCodeStyleSettingsProvider.SettingsType commonType;

  SqlCodeStyleSettingsType(@NotNull String code, @Nullable LanguageCodeStyleSettingsProvider.SettingsType type) {
    this.code = code;
    commonType = type;
  }
}
