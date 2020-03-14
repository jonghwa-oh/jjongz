package com.intellij.sql.psi;

import com.intellij.util.NotNullFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SqlLightDefinitionElementType extends SqlCompositeElementType {
  public static NotNullFunction<String, SqlLightDefinitionElementType> factory(@NotNull SqlReferenceElementType refType) {
    return name -> new SqlLightDefinitionElementType(name, refType);
  }

  protected final SqlReferenceElementType myType;

  public SqlLightDefinitionElementType(@NotNull final String debugName, @Nullable SqlReferenceElementType refType) {
    super(debugName);
    myType = refType;
  }

  @Nullable
  public SqlReferenceElementType getTargetReferenceType() {
    return myType;
  }
}
