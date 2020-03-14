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
package com.intellij.sql;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.sql.psi.SqlLanguage;
import icons.DatabaseIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Gregory.Shrago
 */
public class SqlFileType extends LanguageFileType {
  public static final SqlFileType INSTANCE = new SqlFileType();
  public static final String DEFAULT_EXTENSION = "sql";

  private SqlFileType() {
    super(SqlLanguage.INSTANCE);
  }

  @Override
  @NotNull
  @NonNls
  public String getName() {
    return "SQL";
  }

  @Override
  @NotNull
  public String getDescription() {
    return "SQL";
  }

  @Override
  @NotNull
  @NonNls
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  @Override
  @Nullable
  public Icon getIcon() {
    return DatabaseIcons.Sql;
  }
}

