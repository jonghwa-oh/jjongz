package com.intellij.sql.psi;

import com.intellij.database.model.DasTrigger;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlCreateTriggerStatement extends SqlCreateTriggerStatementBase, SqlTargetContextProvider, DasTrigger {
  @Nullable
  SqlExpression getTriggerProcedure();
}