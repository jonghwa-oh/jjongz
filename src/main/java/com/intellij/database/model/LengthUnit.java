package com.intellij.database.model;

import com.intellij.database.util.DbUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Map;


/**
 * Data size unit.
 **/
public final class LengthUnit {

  public static final LengthUnit NONE = new LengthUnit("");
  public static final LengthUnit DIGIT = new LengthUnit("digit");
  public static final LengthUnit BYTE = new LengthUnit("byte");
  public static final LengthUnit CHAR = new LengthUnit("char");

  @NotNull
  public final String suffix;


  LengthUnit(@NotNull String suffix) {
    this.suffix = suffix;
  }


  /**
   * Returns unit by the code or name.
   * @param str the unit code or name; case and trailing spaces don't matter.
   * @return the unit.
   */
  @NotNull
  public static LengthUnit of(String str) throws IllegalArgumentException {
    if (str == null) return NONE;
    String name = StringUtil.toLowerCase(str.trim());
    LengthUnit unit = ourPossibleNames.get(name);
    return unit != null ? unit : new LengthUnit(DbUtil.intern2(name));
  }


  private static final Map<String, LengthUnit> ourPossibleNames =
    ContainerUtil.<String, LengthUnit>immutableMapBuilder()
      .put("b", BYTE)
      .put("byte", BYTE)
      .put("bytes", BYTE)
      .put("c", CHAR)
      .put("char", CHAR)
      .put("chars", CHAR)
      .put("d", DIGIT)
      .put("digit", DIGIT)
      .put("digits", DIGIT)
      .put("", NONE)
      .build();
}
