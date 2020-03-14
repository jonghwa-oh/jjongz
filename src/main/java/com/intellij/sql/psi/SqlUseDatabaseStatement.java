package com.intellij.sql.psi;

import java.util.List;

/**
 * @author gregsh
 */
public interface SqlUseDatabaseStatement extends SqlStatement {
  boolean isInclusive();

  List<SqlReferenceExpression> getUseReferences();
}
