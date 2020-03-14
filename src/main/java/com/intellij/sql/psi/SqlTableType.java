/*
 * Copyright 2000-2015 JetBrains s.r.o.
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

import com.intellij.database.model.DataType;
import com.intellij.database.model.PsiObject;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public abstract class SqlTableType extends SqlType {

  @Nullable
  public abstract String getTypeName();

  @NotNull
  public abstract List<PsiObject> getMethods();

  @Nullable
  public abstract PsiElement getTypeElement();

  @Nullable
  public abstract PsiElement getImmediateTypeElement();

  public abstract int getColumnCount();

  public abstract String getColumnName(int i);

  public abstract boolean isColumnQuoted(int i);

  public abstract SqlType getColumnType(int i);

  @NotNull
  public abstract PsiElement getColumnElement(int i);

  @Nullable
  public abstract PsiElement getSourceColumnElement(int i);

  @Nullable
  public abstract PsiElement getImmediateSourceColumnElement(int i);

  @Nullable
  public abstract PsiElement getColumnQualifier(int i);

  @Nullable
  public abstract String getColumnTypeAlias(int i);

  public abstract boolean isColumnTypeAliasQuoted(int i);

  public abstract SqlTableType join(SqlTableType type);

  public abstract SqlTableType add(SqlTableType type);

  public abstract SqlTableType subtract(PsiElement element);

  public abstract SqlTableType alias(String aliasName, PsiElement aliasElement, @NotNull List<? extends SqlNameElement> newColumns);
  public abstract SqlTableType aliasByDefs(String aliasName, PsiElement aliasElement, @NotNull List<? extends SqlDefinition> newColumns);
  public abstract SqlTableType flattenedAlias(String aliasName, PsiElement aliasElement, SqlElement sourceElement);

  public abstract SqlTableType concretize(@Nullable Category category);

  @Override
  @NotNull
  public String getDisplayName() {
    final StringBuilder sb = new StringBuilder();
    sb.append(StringUtil.notNullize(getTypeName())).append("(");
    boolean first = true;
    for (int i = 0, len = getColumnCount(); i < len; i++) {
      if (first) first = false;
      else sb.append(", ");
      sb.append(getColumnDisplayName(i));
    }
    sb.append(")");
    return sb.toString();
  }

  public String getColumnDisplayName(int i) {
    String name = getColumnName(i);
    SqlType type = getColumnType(i);
    DataType dataType = type.getDataType();
    String typeText = dataType != DataType.UNKNOWN
                      ? ":" + dataType.getSpecification()
                      : type != UNKNOWN
                        ? ":" + type.getDisplayName()
                        : "";
    return name + typeText;
  }
}