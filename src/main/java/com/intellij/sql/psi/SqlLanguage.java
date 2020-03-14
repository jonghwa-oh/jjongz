/*
 * Copyright (c) 2000-2005 by JetBrains s.r.o. All Rights Reserved.
 * Use is subject to license terms.
 */
package com.intellij.sql.psi;

import com.intellij.lang.Language;

/**
 * @author Gregory.Shrago
 */
public class SqlLanguage extends Language {

  public static final SqlLanguage INSTANCE = new SqlLanguage();

  protected SqlLanguage() {
    super("SQL");
  }

}