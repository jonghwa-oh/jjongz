package com.intellij.sql.psi;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlCreateIndexStatement extends SqlCreateStatement, SqlIndexDefinition {
  @Nullable
  SqlReferenceExpression getTargetReference();

  @Nullable
  List<SqlReferenceExpression> getColumnReferences();
}
