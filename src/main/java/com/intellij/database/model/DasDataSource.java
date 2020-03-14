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
package com.intellij.database.model;

import com.intellij.database.Dbms;
import com.intellij.database.util.Version;
import com.intellij.openapi.util.Iconable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface DasDataSource extends Iconable {

  @NotNull
  String getUniqueId();

  @NotNull
  String getName();

  @Nullable
  String getComment();

  @NotNull
  NameVersion getDatabaseVersion();

  @Nullable
  RawConnectionConfig getConnectionConfig();

  @NotNull
  DasModel getModel();

  @NotNull
  Dbms getDbms();

  @Nullable
  Version getVersion();
}
