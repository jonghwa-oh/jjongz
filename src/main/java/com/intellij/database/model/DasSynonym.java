package com.intellij.database.model;

import com.intellij.database.util.DasUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @see DasUtil#resolveFinalTarget(DasSynonym) for synonyms of synonyms
 *
 * @author Leonid Bushuev from JetBrains
 **/
public interface DasSynonym extends DasObject  {

  /**
   * Returns the origin object of this synonym (in other words,
   * the object that this synonym for.
   * @return the origin object, or null if not exists or is nor resolved.
   */
  @Nullable
  DasObject resolveTarget();

  /**
   * Returns the origin object path.
   * @return the path.
   */
  @NotNull
  Iterable<String> getTargetPath();
}
