package com.intellij.sql.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author ignatov
 */
public interface SqlLabelHolder extends SqlElement {
  @NotNull
  List<? extends SqlDefinition> getLabels();
}
