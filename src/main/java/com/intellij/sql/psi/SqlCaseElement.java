package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface SqlCaseElement extends SqlElement {
  @Nullable
  SqlExpression getExpression();

  @NotNull
  List<SqlClause> getBranches();
}