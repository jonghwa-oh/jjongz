/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.sql.psi;

import com.intellij.database.model.DasModel;
import com.intellij.database.model.DasObject;
import com.intellij.database.psi.DbDataSource;
import com.intellij.database.psi.DbElement;
import com.intellij.database.script.ScriptModel;
import com.intellij.database.util.SearchPath;
import com.intellij.database.util.TextWithRanges;
import com.intellij.lang.Language;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NotNullLazyKey;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiCodeFragment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.sql.dialects.SqlLanguageDialect;
import com.intellij.util.PairProcessor;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * @author Gregory.Shrago
 */
public abstract class SqlPsiFacade {

  private static final NotNullLazyKey<SqlPsiFacade, Project> INSTANCE_KEY = ServiceManager.createLazyKey(SqlPsiFacade.class);

  @NotNull
  public static SqlPsiFacade getInstance(Project project) {
    return INSTANCE_KEY.getValue(project);
  }

  @NotNull
  public abstract ScriptModel<?> createScriptModel(@NotNull PsiFile file);

  @NotNull
  public SqlFile createROFile(@NotNull SqlLanguageDialect dialect, @NotNull CharSequence text) {
    return createROFile(dialect, text, null);
  }

  @NotNull
  public abstract SqlFile createROFile(@NotNull SqlLanguageDialect dialect, @NotNull CharSequence text, @Nullable Language hostLanguage);

  @NotNull
  public abstract JBIterable<DbElement> findRelatedDbElements(@Nullable PsiElement element, boolean strict);

  @NotNull
  public abstract CharSequence format(@NotNull Project project,
                                      @NotNull SqlLanguageDialect dialect,
                                      @NotNull TextWithRanges text);

  public abstract void format(@NotNull Project project,
                              @NotNull SqlLanguageDialect dialect,
                              @NotNull Document document);

  @NotNull
  public abstract PsiCodeFragment createTableReferenceFragment(@NotNull Language dialect,
                                                               @Nullable DbDataSource dataSourceElement,
                                                               @Nullable DbElement schemaElement,
                                                               @NotNull String text);

  @NotNull
  public abstract SqlCodeFragment createTypeElementFragment(@NotNull Language dialect,
                                                            @Nullable DbDataSource dataSourceElement,
                                                            @Nullable SearchPath searchPath,
                                                            @NotNull String text);

  @NotNull
  public abstract PsiCodeFragment createEvaluableExpressionFragment(@NotNull Language dialect,
                                                                    @Nullable DbDataSource dataSourceElement,
                                                                    @Nullable SearchPath searchPath,
                                                                    @NotNull String text);

  @NotNull
  public abstract SqlCodeFragment createExpressionFragment(@NotNull Language dialect,
                                                           @Nullable DbDataSource dataSourceElement,
                                                           @Nullable SearchPath searchPath,
                                                           @NotNull String text);

  @NotNull
  public abstract SqlCodeFragment createExpressionFragment(@NotNull Language dialect,
                                                           @Nullable DbDataSource dataSourceElement,
                                                           @Nullable SearchPath searchPath,
                                                           @NotNull String text,
                                                           boolean isPhysical);

  @NotNull
  public abstract SqlCodeFragment createExpressionFragment(@NotNull Language dialect,
                                                           @Nullable DbDataSource dataSourceElement,
                                                           @Nullable SearchPath searchPath,
                                                           @NotNull String text,
                                                           @NotNull String context);

  @NotNull
  public abstract SqlLanguageDialect getDefaultDialect();

  public abstract void setDialectMapping(@NotNull VirtualFile file, @NotNull SqlLanguageDialect dialect);

  @NotNull
  public abstract SqlLanguageDialect getDialectMapping(@NotNull VirtualFile file);

  @NotNull
  public abstract SqlExecutionFlowAnalyzer<PsiElement> getExecutionFlowAnalyzer();

  public abstract Map<? extends DasObject, SmartPsiElementPointer<SqlElement>> buildModel(DasModel model, List<SqlFile> files, PairProcessor<DasObject, DasObject> newHandler);
}