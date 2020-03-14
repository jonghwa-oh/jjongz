package com.intellij.sql.database;

import com.intellij.database.model.DasDataSource;
import com.intellij.database.model.DasObject;
import com.intellij.database.psi.DbDataSource;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.sql.psi.SqlElement;
import com.intellij.sql.psi.SqlFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author gregsh
 */
public interface SqlDataSource extends DasDataSource {
  @Nullable
  DbDataSource getParentDataSource();

  @NotNull
  List<SqlFile> getSqlFiles();

  @NotNull
  List<VirtualFile> getFiles();

  boolean containsFile(@Nullable VirtualFile file);

  @Nullable
  SqlElement fromModel(@Nullable DasObject delegate);
}
