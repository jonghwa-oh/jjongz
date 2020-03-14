package com.intellij.sql.psi;

import com.intellij.database.model.DasSynonym;
import org.jetbrains.annotations.Nullable;

public interface SqlSynonymDefinition extends SqlDefinition, DasSynonym {

  @Nullable
  SqlReferenceExpression getTargetReference();
}