/*
 * Copyright (c) 2000-2005 by JetBrains s.r.o. All Rights Reserved.
 * Use is subject to license terms.
 */
package com.intellij.sql.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public class SqlElementType extends IElementType {

  public SqlElementType(@NotNull String debugName) {
    super(debugName, SqlLanguage.INSTANCE);
  }

}