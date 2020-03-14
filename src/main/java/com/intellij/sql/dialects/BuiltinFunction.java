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
package com.intellij.sql.dialects;

import com.intellij.database.model.DasObject;
import com.intellij.database.model.ObjectKind;
import com.intellij.pom.PomNamedTarget;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents built-in dialect function.
 * @author gregsh
 */
public abstract class BuiltinFunction implements DasObject, PomNamedTarget {

  @NotNull
  @Override
  public ObjectKind getKind() {
    return ObjectKind.ROUTINE;
  }

  public abstract String getPostfixType();

  @Override
  @NotNull
  public JBIterable<? extends DasObject> getDasChildren(@Nullable ObjectKind kind) {
    return JBIterable.empty();
  }

  @Nullable
  @Override
  public DasObject getDasParent() {
    return null;
  }

  @Override
  @Nullable
  public String getComment() {
    return null;
  }
}
