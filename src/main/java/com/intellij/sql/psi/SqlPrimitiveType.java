package com.intellij.sql.psi;

import com.intellij.database.model.DataType;
import org.jetbrains.annotations.NotNull;

public class SqlPrimitiveType extends SqlType {
  private final DataType myType;
  private final Category myCategory;

  public SqlPrimitiveType(@NotNull DataType dataType, @NotNull Category category) {
    myType = dataType;
    myCategory = category;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return myType.getSpecification();
  }

  @NotNull
  @Override
  public Category getCategory() {
    return myCategory;
  }

  @NotNull
  @Override
  public DataType getDataType() {
    return myType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SqlPrimitiveType type = (SqlPrimitiveType)o;
    return myType.equals(type.myType) && myCategory == type.myCategory;
  }

  @Override
  public int hashCode() {
    int result = myType.hashCode();
    result = 31 * result + myCategory.hashCode();
    return result;
  }
}