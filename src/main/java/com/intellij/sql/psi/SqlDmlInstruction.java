package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Alexey Chmutov
 */
public interface SqlDmlInstruction extends SqlElement {
  SqlExpression getTargetExpression();

  @NotNull
  List<SqlReferenceExpression> getTargetColumnReferences();

  SqlTableType getTargetType();

}
