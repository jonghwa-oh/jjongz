package com.intellij.sql.psi;

import com.intellij.database.model.TrigEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface SqlCreateTriggerStatementBase extends SqlCreateStatement {
  @NotNull
  Set<TrigEvent> getEvents();
}