package com.intellij.sql.psi;

import com.intellij.database.model.DasUserDefinedType;
import org.jetbrains.annotations.Nullable;

/**
 * @author ignatov
 */
public interface SqlCreateTypeStatement extends SqlCreateStatement, DasUserDefinedType {
  @Nullable
  SqlType getSqlType();
}
