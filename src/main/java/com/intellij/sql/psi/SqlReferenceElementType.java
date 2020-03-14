package com.intellij.sql.psi;

import com.intellij.database.model.ObjectKind;
import com.intellij.util.NotNullFunction;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public class SqlReferenceElementType extends SqlCompositeElementType {
  private final ObjectKind myTargetKind;
  private final boolean myQualified;

  public static NotNullFunction<String, SqlReferenceElementType> factory(@NotNull final ObjectKind targetType, final boolean qualified) {
    return s -> new SqlReferenceElementType(s, targetType, qualified);
  }

  public SqlReferenceElementType(@NotNull @NonNls String debugName, @NotNull ObjectKind targetKind, boolean qualified) {
    super(debugName);
    myTargetKind = targetKind;
    myQualified = qualified;
  }

  @NotNull
  public ObjectKind getTargetKind() {
    return myTargetKind;
  }

  public boolean isQualified() {
    return myQualified;
  }
}