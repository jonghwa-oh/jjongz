package com.intellij.sql.psi;

import com.intellij.database.model.DasConstraint;
import com.intellij.openapi.util.Key;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlConstraintDefinition extends SqlElement, SqlDefinition, DasConstraint {
  enum Type { NULLABLE, NOT_NULL, CHECK, DEFAULT, COLLATE, OTHER }

  Key<SqlExpression> EXPRESSION = Key.create("EXPRESSION");
  SqlConstraintDefinition[] EMPTY_ARRAY = new SqlConstraintDefinition[0];
  ArrayFactory<SqlConstraintDefinition> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new SqlConstraintDefinition[count];

  @NotNull
  Type getConstraintType();

  @Override
  @Nullable
  SqlReferenceExpression getNameElement();

  @Nullable
  <T> T getConstraintParameter(final Key<T> key);

}