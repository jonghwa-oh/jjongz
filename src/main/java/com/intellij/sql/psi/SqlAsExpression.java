package com.intellij.sql.psi;

import com.intellij.database.model.DasPositioned;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlAsExpression extends SqlExpression, SqlDefinition, DasPositioned {
  @Override
  @Nullable
  SqlIdentifier getNameElement();

  @Nullable
  SqlExpression getExpression();

  @NotNull
  List<SqlDefinition> getColumnAliasList();
}
