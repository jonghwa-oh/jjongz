/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package com.intellij.database.psi;

import com.intellij.database.model.DasDataSource;
import com.intellij.database.model.DasObject;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ModificationTrackerListener;
import com.intellij.openapi.util.SimpleModificationTracker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public abstract class DbPsiFacade extends SimpleModificationTracker {

  @NotNull
  public static DbPsiFacade getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, DbPsiFacade.class);
  }

  @NotNull
  public abstract Project getProject();

  /** @deprecated use {@link DataSourceManager#getManagers(Project)} */
  @Deprecated
  @NotNull
  public List<DataSourceManager<?>> getDbManagers() {
    return DataSourceManager.getManagers(getProject());
  }

  @NotNull
  public abstract DataSourceManager<DasDataSource> getDataSourceManager(@NotNull DbDataSource dataSource);

  @NotNull
  public abstract List<DbDataSource> getDataSources();
  
  @Nullable
  public abstract DbDataSource findDataSource(@Nullable String id);

  @Nullable
  public abstract DbDataSource findDataSource(@Nullable DasObject o);

  @Nullable
  public abstract DbElement findElement(@Nullable DasObject o);

  public abstract void addModificationTrackerListener(@NotNull ModificationTrackerListener<DbPsiFacade> listener,
                                                      @NotNull Disposable parent);
}
