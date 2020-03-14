/*
 * Copyright 2000-2017 JetBrains s.r.o.
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
package com.intellij.sql.database;

import com.intellij.database.DbmsExtension;
import com.intellij.database.model.DasObject;
import com.intellij.sql.psi.SqlElement;
import org.jetbrains.annotations.NotNull;

public interface SqlObjectBuilder {
  DbmsExtension<SqlObjectBuilder> EP = new DbmsExtension<>("com.intellij.database.sqlObjectBuilder");

  //todo: BasicModElement
  void build(@NotNull DasObject obj, @NotNull DasObject source);
  //todo: replace by concrete calls by introspector
  void partialBuild(@NotNull DasObject obj, @NotNull DasObject source);
  void finalize(@NotNull DasObject obj);

  void build(@NotNull DasObject obj, @NotNull SqlElement source);
}
