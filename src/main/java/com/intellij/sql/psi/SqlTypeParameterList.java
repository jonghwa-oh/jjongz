package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Liudmila Kornilova
 */
public interface SqlTypeParameterList extends SqlElement {

  @NotNull
  List<SqlTypeElement> getTypeParameters();
}
