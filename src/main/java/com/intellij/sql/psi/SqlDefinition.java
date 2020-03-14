package com.intellij.sql.psi;

import com.intellij.database.model.ObjectKind;
import com.intellij.database.model.PsiObject;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlDefinition extends PsiObject, SqlCompositeElement, PsiNameIdentifierOwner, NavigationItem {
  @Nullable
  SqlNameElement getNameElement();

  boolean isNameQuoted();

  @Override
  @NotNull
  ObjectKind getKind();

  @Nullable
  default SqlReferenceExpression getParentRef() {
    return null;
  }
}
