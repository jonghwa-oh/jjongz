package com.intellij.sql.psi;

import com.intellij.util.NotNullFunction;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public class SqlCompositeElementType extends SqlElementType {

  public SqlCompositeElementType(@NotNull @NonNls final String debugName) {
    super(debugName);
  }

  public static class External extends SqlCompositeElementType implements IsExternal {
    public static final NotNullFunction<String, SqlCompositeElementType> FACTORY =
      name -> new External(name);

    public External(@NotNull @NonNls String debugName) {
      super(debugName);
    }
  }

  public static class Impure extends SqlCompositeElementType implements IsImpure {
    public static final NotNullFunction<String, SqlCompositeElementType> FACTORY = name -> new Impure(name);

    public Impure(@NotNull String debugName) {
      super(debugName);
    }
  }
}
