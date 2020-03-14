package com.intellij.sql.dialects;

import com.intellij.database.Dbms;
import com.intellij.database.DbmsExtension;
import com.intellij.database.dialects.DatabaseDialect;
import com.intellij.database.model.ObjectKind;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.sql.psi.SqlLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;
import java.util.Set;

/**
 * @author Gregory.Shrago
 */
public abstract class SqlLanguageDialect extends Language {

  public static final DbmsExtension<SqlLanguageDialect> EP = new DbmsExtension<>("com.intellij.sql.dialect");

  public SqlLanguageDialect(@NonNls @NotNull String id) {
    super(SqlLanguage.INSTANCE, id);
  }

  protected SqlLanguageDialect(@NotNull SqlLanguageDialect base, @NonNls @NotNull String id) {
    super(base, id);
  }

  @NotNull
  public abstract DatabaseDialect getDatabaseDialect();

  @NotNull
  public abstract Icon getIcon();

  @NotNull
  public abstract Dbms getDbms();

  public abstract boolean isReservedKeyword(IElementType tokenType);

  public boolean isReservedKeywordPL(IElementType tokenType) {
    return isReservedKeyword(tokenType);
  }

  public abstract boolean isOperatorSupported(IElementType tokenType);

  @NotNull
  public abstract Set<String> getKeywords();

  @NotNull
  public abstract Set<String> getReservedKeywords();

  @NotNull
  public abstract Set<String> getSystemVariables();

  @NotNull
  public Set<String> getExceptionNames() {
    return Collections.emptySet();
  }

  public boolean isResolveTargetAccepted(@Nullable PsiElement element,
                                         ObjectKind type,
                                         @NotNull Set<ObjectKind> expectedTypes,
                                         @Nullable PsiElement place, boolean strict, boolean isCompletion) {
    return false;
  }

  public boolean isResolveTargetNotAccepted(@Nullable Object element,
                                            ObjectKind type,
                                            @NotNull Set<ObjectKind> expectedTypes,
                                            @Nullable PsiElement place, boolean strict, boolean isCompletion) {
    return false;
  }

  public abstract TokenSet getStatementSeparators();
}
