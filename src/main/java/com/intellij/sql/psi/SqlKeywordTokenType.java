package com.intellij.sql.psi;

import com.intellij.util.NotNullFunction;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public class SqlKeywordTokenType extends SqlTokenType {

  public final static NotNullFunction<String, SqlKeywordTokenType> FACTORY = s -> new SqlKeywordTokenType(s, true);

  public SqlKeywordTokenType(@NotNull @NonNls String debugName) {
    super(debugName, SqlLanguage.INSTANCE, false);
  }

  public SqlKeywordTokenType(@NotNull @NonNls String debugName, boolean register) {
    super(debugName, SqlLanguage.INSTANCE, register);
  }
}