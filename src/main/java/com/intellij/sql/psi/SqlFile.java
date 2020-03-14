package com.intellij.sql.psi;

import com.intellij.database.model.DasModel;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiFile;
import com.intellij.sql.dialects.SqlLanguageDialect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlFile extends PsiFile, SqlElement, DasModel {

  @NotNull
  SqlLanguageDialect getSqlLanguage();

  @NotNull
  List<SqlElement> getDdl();

  @Nullable
  <T> T getAttributeAt(@NotNull Key<T> attr, int offset);
}
