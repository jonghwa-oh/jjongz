package com.intellij.sql.psi;

import java.util.List;

/**
 * @author gregsh
 */
public interface SqlParameterList extends SqlElement {
  List<? extends SqlParameterDefinition> getParameters();
}
