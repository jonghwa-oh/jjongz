package com.intellij.database.util;

import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
* @author gregsh
*/
public enum Case {

  MIXED(false),
  LOWER(false),
  UPPER(false),
  TITLE(false),
  EXACT(true);

  /**
   * Whether this mode is case-sensitive;
   */
  public final boolean sensitive;


  Case(boolean sensitive) {
    this.sensitive = sensitive;
  }

  public String apply(String s) {
    switch (this) {
      case EXACT:
      case MIXED:
        return s;
      case LOWER: return StringUtil.toLowerCase(s);
      case UPPER: return StringUtil.toUpperCase(s);
      case TITLE: return StringUtil.toTitleCase(StringUtil.toLowerCase(s));
    }
    throw new AssertionError(this);
  }

  @Contract("_,!null->!null")
  public static Case fromString(@Nullable String str, @Nullable Case def) {
    if (str == null) return def;
    for (Case mode : values()) {
      if (Comparing.equal(str, mode.name(), false)) return mode;
    }
    return def;
  }

  public static Case guessForName(@Nullable String str, Boolean isQuoted, Case plainMode, Case quotedMode) {
    if (str == null) return plainMode;
    if (Boolean.TRUE.equals(isQuoted)) return quotedMode;
    if (Boolean.FALSE.equals(isQuoted)) return plainMode;
    // detect
    Case strMode = forString(str);
    if (strMode != MIXED) {
      // lower or upper
      if (plainMode == strMode || quotedMode == strMode) return strMode;
    }
    if (plainMode == EXACT || quotedMode == EXACT) return EXACT;
    if (plainMode == MIXED || quotedMode == MIXED) return MIXED;

    // let plainMode be the default one
    return plainMode;
  }

  // UPPER, LOWER, TITLE or AS_IS
  @Nullable
  public static Case forString(@Nullable String str) {
    Case result = null;
    if (str == null) return null;
    for (int i = 0, len = str.length(); i < len; i++) {
      char c = str.charAt(i);
      boolean upperCase = Character.isUpperCase(c);
      boolean lowerCase = Character.isLowerCase(c);
      if (upperCase == lowerCase) continue;
      Case cur = upperCase ? UPPER : LOWER;
      if (result == null) result = cur;
      else if (result != cur) {
        if (result == UPPER && i == 1) {
          result = TITLE;
          continue;
        }
        if (result == TITLE && cur == LOWER) continue;
        result = MIXED;
        break;
      }
    }
    return result;
  }

}
