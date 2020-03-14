package com.intellij.database.model;

import org.jetbrains.annotations.Nullable;

/**
 * Data direction or a routine argument.
 * @author Leonid Bushuev
 */
public enum ArgumentDirection {
  IN     ('I'),
  INOUT  ('M'), // 'M' means 'mix in/out'
  OUT    ('O'),
  RETURN ('R'),
  RESULT ('T'), // 'T' from the 'Table' word
  SELF   ('S');

  public final char code;

  ArgumentDirection(final char code) {
    this.code = code;
  }

  @Nullable
  public static ArgumentDirection of(char code) {
    switch (Character.toUpperCase(code)) {
      case 'I': return IN;
      case 'M': return INOUT;
      case 'O': return OUT;
      case 'R': return RETURN;
      case 'T': return RESULT;
      case 'S': return SELF;
      default: return null;
    }
  }

  public boolean isIn() {
    return this == IN || this == INOUT;
  }

  public boolean isOut() {
    return this == INOUT || this == OUT;
  }

  public boolean isReturnOrResult() {
    return this == RETURN || this == RESULT;
  }
}
