package com.intellij.sql.psi;

import java.util.List;

/**
 * @author Gregory.Shrago
 */
public interface SqlReferenceList extends SqlElement {
  List<SqlReferenceExpression> getReferenceList();
}
