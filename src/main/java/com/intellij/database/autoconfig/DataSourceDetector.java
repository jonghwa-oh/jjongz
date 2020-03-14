/*
 * Copyright 2000-2007 JetBrains s.r.o.
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

package com.intellij.database.autoconfig;

import com.intellij.database.Dbms;
import com.intellij.database.model.DasDataSource;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public abstract class DataSourceDetector {

  public static final ExtensionPointName<DataSourceDetector> EP_NAME = ExtensionPointName.create("com.intellij.database.dataSourceDetector");

  /**
   * Called to discover datasource configurations in project-scope by explicit "Import" action
   * or on project startup. Default implementation calls #collectDataSources(Module, false) for each module.
   *
   * @see #collectDataSources(Module, Builder, boolean)
   */
  public void collectDataSources(@NotNull Project project, @NotNull Builder builder) {
    for (Module module : ModuleManager.getInstance(project).getModules()) {
      collectDataSources(module, builder, false);
    }
  }

  /**
   * Called to discover datasource configurations in module-scope by explicit "Import" action
   * or on project startup with onTheFly=false; otherwise queued by PSI change listener if relevant file is changed
   *
   * @see #isRelevantFile(PsiFile)
   */
  public void collectDataSources(@NotNull Module module, @NotNull Builder builder, boolean onTheFly) {
  }

  /**
   * Override this method to turn on on-thy-fly datasource configuration update on specific file change.
   *
   * @see #collectDataSources(Module, Builder, boolean)
   */
  public boolean isRelevantFile(@NotNull PsiFile file) {
    return false;
  }


  public interface Builder {

    Builder commit();

    Builder reset();

    DriverBuilder driver(@NotNull Dbms dbms);

    Builder withCallback(@NotNull Callback callback);

    Builder withName(@Nullable String name);

    Builder withComment(@Nullable String comment);

    Builder withGroupName(@Nullable String groupName);

    Builder withUrl(@Nullable String url);

    Builder withUser(@Nullable String user);

    Builder withPassword(@Nullable String password);

    Builder withDriverProperty(@Nullable String name, @Nullable String value);

    Builder withJdbcAdditionalProperty(@Nullable String name, @Nullable String value);

    Builder withDriverClass(@Nullable String name);

    Builder withDriver(@Nullable String name);

    Builder withVMOptions(@Nullable String options);

    Builder withVMEnv(@Nullable String name, @Nullable String value);

    Builder withOrigin(@Nullable Object origin);

    /** @deprecated use {@code withComment(category).withOrigin(origin).commit()} */
    @Deprecated
    default Builder commit(@NotNull String category, @Nullable Object origin) { return withComment(category).withOrigin(origin).commit(); }
  }

  public interface DriverBuilder {
    Builder commitDriver();

    DriverBuilder withName(@Nullable String name);

    DriverBuilder withComment(@Nullable String comment);

    DriverBuilder withDriverClass(@Nullable String driver);

    DriverBuilder withAdditionalJar(@Nullable String path);

    DriverBuilder withDriverProperty(@Nullable String name, @Nullable String value);

    DriverBuilder withVMOptions(@Nullable String options);

    DriverBuilder withVMEnv(@Nullable String name, @Nullable String value);
  }

  public abstract static class Callback {
    public void onCreated(@NotNull DasDataSource dataSource) {
    }

    public void onUpdated(@NotNull DasDataSource dataSource) {
    }
  }

}
