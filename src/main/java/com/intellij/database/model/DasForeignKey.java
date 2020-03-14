package com.intellij.database.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface DasForeignKey extends DasConstraint {

  enum RuleAction {CASCADE, RESTRICT, SET_NULL, NO_ACTION, SET_DEFAULT}
  enum Deferrability {INITIALLY_IMMEDIATE, INITIALLY_DEFERRED, NOT_DEFERRABLE}

  String getRefTableName();

  String getRefTableSchema();

  String getRefTableCatalog();

  @Nullable
  DasTable getRefTable();

  @NotNull
  MultiRef<? extends DasTypedObject> getRefColumns();

  RuleAction getDeleteRule();

  RuleAction getUpdateRule();

  Deferrability getDeferrability();
}
