package com.intellij.database.psi;

import com.intellij.database.model.DasDataSource;
import com.intellij.ide.dnd.DnDTarget;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.extensions.ProjectExtensionPointName;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Consumer;
import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

/**
 * @author Gregory.Shrago
 */
public abstract class DataSourceManager<T extends DasDataSource> {
  public static final ProjectExtensionPointName<DataSourceManager<?>> EP_NAME = new ProjectExtensionPointName<>("com.intellij.database.dataSourceManager");
  public static final Topic<Listener> TOPIC = Topic.create("DATASOURCE_TOPIC", Listener.class);

  @NotNull
  public static List<DataSourceManager<?>> getManagers(@NotNull Project project) {
    return EP_NAME.getExtensions(project);
  }

  @NotNull
  public abstract List<T> getDataSources();

  public abstract boolean containsDataSource(@NotNull T element);

  public abstract void addDataSource(@NotNull T element);

  public abstract void removeDataSource(@NotNull T element);

  @NotNull
  public abstract Configurable createDataSourceEditor(@NotNull T element);

  @Nullable
  public DnDTarget createDnDTarget(@NotNull T element) { return null; }

  @Nullable
  public abstract AnAction getCreateDataSourceAction(@NotNull Consumer<T> consumer);

  @NotNull
  public abstract T copyDataSource(@NotNull String newName, @NotNull T copyFrom);

  public abstract void renameDataSource(@NotNull T element, @NotNull String name);

  public boolean canCreateDataSourceByFiles(@NotNull Collection<VirtualFile> files) { return false; }

  @NotNull
  public List<T> createDataSourceByFiles(@NotNull List<VirtualFile> files) { return Collections.emptyList(); }



  public interface Listener extends EventListener {
    default <T extends DasDataSource> void dataSourceAdded(@NotNull DataSourceManager<T> manager, @NotNull T dataSource) { }

    default <T extends DasDataSource> void dataSourceRemoved(@NotNull DataSourceManager<T> manager, @NotNull T dataSource) { }

    default <T extends DasDataSource> void dataSourceChanged(@Nullable DataSourceManager<T> manager, @Nullable T dataSource) { }
  }

}
