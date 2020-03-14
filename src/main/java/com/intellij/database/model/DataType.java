/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.database.model;

import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;

import static com.intellij.database.util.DbUtil.intern2;

/**
 * Type of one datum.
 * <p/>
 * Value object.
 */
public final class DataType implements Serializable {

  /**
   * For fields like varchar(max).
   */
  public static final int MAX_SIZE = Integer.MAX_VALUE;

  /**
   * For fields like number(*) or varchar(*).
   */
  public static final int STAR_SIZE = Integer.MAX_VALUE - 1;

  /**
   * For fields like int or date, where the size is not specified.
   */
  public static final int NO_SIZE = -1;

  /**
   * For fields like number, where the scale is not specified.
   */
  public static final int NO_SCALE = 0;


  public static final DataType UNKNOWN = new DataType(null, null, "unknown", NO_SIZE, NO_SCALE, LengthUnit.NONE,
                                                      false, null, false, Types.NULL);

  /**
   * Name of the schema.
   * Null for common/internal types.
   */
  @Nullable
  public final String schemaName;

  /**
   * Name of the oracle package.
   * Null for common/internal types.
   */
  @Nullable
  public final String packageName;

  /**
   * Name of the type (without schema prefix).
   *
   * @see #schemaName
   */
  @NotNull
  public final String typeName;

  /**
   * Size or precision of the type.
   * <p/>
   * <p>
   * Contains the size of datum, or one of the following 'magic' values:
   * <ul>
   * <li>{@link #STAR_SIZE} mean '<b>*</b>';</li>
   * <li>{@link #MAX_SIZE} means '<b>max</b>';</li>
   * <li>{@link #NO_SIZE} means the size is not specified.</li>
   * </ul>
   * </p>
   *
   * @see #sizeUnit
   * @see #scale
   */
  @MagicConstant(intValues = {STAR_SIZE, MAX_SIZE, NO_SIZE})
  public final int size;

  /**
   * Scale, or {@link #NO_SCALE} if N/A.
   *
   * @see #size
   */
  @MagicConstant(intValues = {NO_SCALE})
  public final int scale;

  /**
   * Unit of size (as declared in code, if possible to determine).
   *
   * @see #size
   */
  @NotNull
  public final LengthUnit sizeUnit;

  /**
   * Indefinite argument inside parentheses.
   * For example, in "geometry(point,4126)" it's "point,4126"
   */
  public final String vagueArg;

  /**
   * A rest part of the type that is written after the closing parenthesis.
   */
  @Nullable
  public final String suffix;

  /**
   * The size units are specified explicitly;
   */
  public final boolean sizeUnitExplicit;

  /**
   * The type is defined by a user.
   */
  public final boolean custom;

  /**
   * Enumeration value.
   */
  @Nullable
  public final List<String> enumValues;


  @Deprecated  //todo REMOVEME ASAP
  public final int jdbcType;

  private DataType(@Nullable String schemaName,
                   @Nullable String packageName,
                   @NotNull String typeName,
                   @MagicConstant(intValues = {STAR_SIZE, MAX_SIZE, NO_SIZE})
                   int size,
                   @MagicConstant(intValues = {NO_SCALE})
                   int scale,
                   @Nullable LengthUnit sizeUnit,
                   boolean sizeUnitExplicit,
                   @Nullable String vagueArg,
                   @Nullable String suffix,
                   boolean custom,
                   @Nullable List<String> enumValues,
                   int jdbcType) {
    this.schemaName = intern2(schemaName);
    this.packageName = intern2(packageName);
    this.typeName = intern2(typeName);
    boolean notASize = size == NO_SIZE || size == STAR_SIZE || size == MAX_SIZE;
    this.size = size;
    this.scale = notASize ? NO_SCALE : scale;
    this.sizeUnit = sizeUnit != null && !notASize ? sizeUnit : LengthUnit.NONE;
    this.vagueArg = intern2(StringUtil.nullize(vagueArg));
    this.suffix = intern2(StringUtil.nullize(suffix));
    this.custom = custom;
    this.sizeUnitExplicit = sizeUnitExplicit && this.sizeUnit != LengthUnit.NONE;
    this.jdbcType = jdbcType;

    this.enumValues = enumValues == null || enumValues.isEmpty()
                      ? null
                      : ContainerUtil.immutableList(enumValues);
  }

  public DataType(@Nullable String schemaName,
                  @NotNull String typeName,
                  @MagicConstant(intValues = {STAR_SIZE, MAX_SIZE, NO_SIZE})
                  int size,
                  @MagicConstant(intValues = {NO_SCALE})
                  int scale,
                  @Nullable LengthUnit sizeUnit,
                  boolean sizeUnitExplicit,
                  @Nullable String suffix,
                  boolean custom,
                  int jdbcType) {
    this(schemaName, null, typeName, size, scale, sizeUnit, sizeUnitExplicit, null, suffix, custom, null, jdbcType);
  }

  public DataType(@Nullable String schemaName,
                  @Nullable String packageName,
                  @NotNull String typeName,
                  @MagicConstant(intValues = {STAR_SIZE, MAX_SIZE, NO_SIZE})
                  int size,
                  @MagicConstant(intValues = {NO_SCALE})
                  int scale,
                  @Nullable LengthUnit sizeUnit,
                  boolean sizeUnitExplicit,
                  @Nullable String suffix,
                  boolean custom,
                  int jdbcType) {
    this(schemaName, packageName, typeName, size, scale, sizeUnit, sizeUnitExplicit, null, suffix, custom, null, jdbcType);
  }

  public DataType(@Nullable String schemaName,
                  @NotNull String typeName,
                  @Nullable String vagueArg,
                  boolean custom,
                  int jdbcType) {
    this(schemaName, null, typeName, NO_SIZE, NO_SCALE, null, false, vagueArg, null, custom, null, jdbcType);
  }

  public DataType(@Nullable String schemaName,
                  @Nullable String packageName,
                  @NotNull String typeName,
                  @Nullable String vagueArg,
                  boolean custom,
                  int jdbcType) {
    this(schemaName, packageName, typeName, NO_SIZE, NO_SCALE, null, false, vagueArg, null, custom, null, jdbcType);
  }

  public DataType(@Nullable String schemaName,
                  @NotNull String typeName,
                  boolean custom,
                  @Nullable List<String> enumValues,
                  int jdbcType) {
    this(schemaName, null, typeName, NO_SIZE, NO_SCALE, null, false, null, null, custom, enumValues, jdbcType);
  }

  public DataType(@Nullable String schemaName,
                  @Nullable String packageName,
                  @NotNull String typeName,
                  boolean custom,
                  @Nullable List<String> enumValues,
                  int jdbcType) {
    this(schemaName, packageName, typeName, NO_SIZE, NO_SCALE, null, false, null, null, custom, enumValues, jdbcType);
  }

  public DataType withTypeName(@NotNull String typeName) {
    return new DataType(
      schemaName, packageName, typeName, size, scale, sizeUnit,
      sizeUnitExplicit, vagueArg, suffix, custom, enumValues, jdbcType);
  }

  @NotNull
  public String getSpecification() {
    return getSpecification(false, false);
  }

  //todo: DdlBuilder#type(DataType)
  @NotNull
  public String getSpecification(boolean forceDefaults, boolean onlyNumbers) {
    if (schemaName == null && packageName == null && vagueArg == null && enumValues == null && size < 0 && suffix == null) {
      return typeName;
    }

    StringBuilder sb = new StringBuilder(12);
    if (schemaName != null) {
      sb.append(schemaName).append('.');
    }
    if (packageName != null) {
      sb.append(packageName).append('.');
    }

    sb.append(typeName);

    if (vagueArg != null) {
      sb.append('(').append(vagueArg).append(')');
    }
    else if (enumValues != null) {
      sb.append('(');
      StringUtil.join(enumValues, ", ", sb);
      sb.append(')');
    }
    else if (size >= 0) {
      sb.append('(');
      if (!onlyNumbers && size == STAR_SIZE) {
        sb.append('*');
      }
      else if (!onlyNumbers && size == MAX_SIZE) {
        sb.append("max");
      }
      else {
        sb.append(size);
      }
      if (scale != NO_SCALE) {
        sb.append(',').append(scale);
      }
      if (sizeUnit != LengthUnit.NONE && (sizeUnit != LengthUnit.DIGIT && sizeUnitExplicit || forceDefaults)) {
        sb.append(' ').append(StringUtil.toLowerCase(sizeUnit.suffix));
      }
      sb.append(')');
    }
    else if (forceDefaults) {
      sb.append("()");
    }
    if (suffix != null) {
      sb.append(' ').append(suffix);
    }

    return sb.toString();
  }

  public int getLength() {
    return size;
  }

  public int getPrecision() {
    return size;
  }

  public int getScale() {
    return scale;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) return true;
    if (that == null || getClass() != that.getClass()) return false;
    return equals((DataType)that);
  }

  public boolean equals(@NotNull DataType that) {
    return jdbcType == that.jdbcType && equalsWithoutJdbc(that);
  }

  public boolean equalsWithoutJdbc(@NotNull DataType that) {
    if (this == that) return true;

    if (size != that.size) return false;
    if (scale != that.scale) return false;
    if (sizeUnit != that.sizeUnit) return false;
    if (custom != that.custom) return false;
    if (sizeUnitExplicit != that.sizeUnitExplicit) return false;
    if (!Comparing.equal(schemaName, that.schemaName)) return false;
    if (!Comparing.equal(packageName, that.packageName)) return false;
    if (!Comparing.equal(vagueArg, that.vagueArg)) return false;
    if (!Comparing.equal(typeName, that.typeName)) return false;
    if (!Comparing.equal(suffix, that.suffix)) return false;
    if (!Comparing.equal(enumValues, that.enumValues)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = schemaName != null ? schemaName.hashCode() : 0;
    result = packageName != null ? 31 * result + packageName.hashCode() : result;
    result = 31 * result + typeName.hashCode();
    result = 31 * result + size;
    result = 31 * result + scale;
    result = 31 * result + sizeUnit.hashCode();
    result = 31 * result + (vagueArg != null ? vagueArg.hashCode() : 0);
    result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
    result = 31 * result + (sizeUnitExplicit ? 1 : 0);
    result = 31 * result + (custom ? 1 : 0);
    result = 31 * result + (enumValues != null ? enumValues.hashCode() : 0);
    result = 31 * result + jdbcType;
    return result;
  }

  @Override
  public String toString() {
    return getSpecification();
  }

}
