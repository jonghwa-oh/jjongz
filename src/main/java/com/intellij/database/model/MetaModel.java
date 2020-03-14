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
package com.intellij.database.model;

import com.intellij.database.Dbms;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Leonid Bushuev
 **/
public interface MetaModel {

  MetaModel EMPTY = new MetaModel() { };

  @NotNull
  default Dbms getDbms() { return Dbms.UNKNOWN; }

  @NotNull
  default JBIterable<ObjectKind> getChildKinds(@NotNull ObjectKind kind) { return JBIterable.empty(); }

  @NotNull
  default JBIterable<ObjectKind> getParentKinds(@NotNull ObjectKind kind) { return JBIterable.empty(); }

  @NotNull
  default JBIterable<ObjectKind> getRootNamespaceKinds() { return JBIterable.empty(); }

  @NotNull
  default Set<ObjectKind> getNamespaces() { return Collections.emptySet(); }

  @NotNull
  default JBIterable<List<ObjectKind>> getPathsToRoot(ObjectKind kind) { return JBIterable.empty(); }

  @Nullable
  default ObjectKind findKind(@Nullable String code) { return null; }

  @Nullable
  default Iterable<ObjectKind> getKinds() { return null; }
}
