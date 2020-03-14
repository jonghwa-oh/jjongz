package com.intellij.sql.psi;

import com.intellij.database.model.DataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Liudmila Kornilova
 */
public class SqlParametrizedType extends SqlType {
  private final DataType myDataType;
  private final Category myCategory;
  private final List<SqlType> myTypeParameters;

  public SqlParametrizedType(@NotNull DataType dataType, @NotNull Category category, @NotNull List<SqlType> typeParameters) {
    myDataType = dataType;
    myCategory = category;
    myTypeParameters = typeParameters;
  }

  public int getTypeParametersCount() {
    return myTypeParameters.size();
  }

  public SqlType getTypeParameter(int i) {
    return myTypeParameters.get(i);
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return myDataType.getSpecification();
  }

  @NotNull
  @Override
  public Category getCategory() {
    return myCategory;
  }

  @NotNull
  @Override
  public DataType getDataType() {
    return myDataType;
  }
}