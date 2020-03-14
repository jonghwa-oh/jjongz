// Copyright 2000-2017 JetBrains s.r.o.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.intellij.sql.psi;

import com.intellij.psi.PsiElement;
import com.intellij.util.ThreeState;
import org.jetbrains.annotations.NotNull;

public interface SqlExecutionFlowAnalyzer<T extends PsiElement> {
  @NotNull
  Flow analyze(@NotNull T element);

  boolean isAcceptable(@NotNull PsiElement e);

  interface Flow {
    @NotNull ThreeState isPure();

    <T> T transform(@NotNull FlowTransformer<T> visitor);
  }

  interface FlowTransformer<T> {
    T common();
    T dml();
    T ddl();
    T routine();
  }
}
