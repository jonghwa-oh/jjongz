/*
 * Copyright 2000-2015 JetBrains s.r.o.
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
package com.intellij.database.util;

import com.intellij.database.model.DasForeignKey;
import com.intellij.database.model.DataType;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.PairConsumer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.*;

public class JdbcUtil {


  private JdbcUtil() {
  }

  public static boolean setCatalogSafe(@NotNull Connection connection, @Nullable String catalog) {
    try {
      if (catalog != null) {
        connection.setCatalog(catalog);
      }
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  @NotNull
  public static <T> List<T> resultSetToListSafe(@NotNull ResultSet rs, @NotNull String columnName, @Nullable PairConsumer<? super String, ? super Throwable> errorSink) {
    List<T> retVal = new ArrayList<>();
    try {
      while (rs.next()) {
        retVal.add((T)rs.getObject(columnName));
      }
    }
    catch (SQLException ignored) {
    }
    catch (Exception e) {
      columnNotAvailable(columnName, errorSink, e);
    }
    finally {
      closeResultSetSafe(rs);
    }
    return retVal;
  }

  private static void columnNotAvailable(@NotNull String columnName, @Nullable PairConsumer<? super String, ? super Throwable> errorSink, Exception e) {
    String message = e.getMessage();
    if (errorSink != null) {
      errorSink.consume(message != null && message.contains(columnName) ? null : columnName + " unavailable", e);
    }
  }

  @NotNull
  public static String resultSetToStringSafe(@NotNull ResultSet rs) {
    return resultSetToStringSafe(rs, 100, null);
  }

  @NotNull
  public static String resultSetRowToStringSafe(@NotNull ResultSet rs) {
    return resultSetToStringSafe(rs, 0, null);
  }

  @NotNull
  public static String resultSetToStringSafe(@NotNull ResultSet rs, int maxRows, @Nullable PairConsumer<? super String, ? super Throwable> errorSink) {
    StringBuilder sb = new StringBuilder();
    try {
      ResultSetMetaData metaData = rs.getMetaData();
      int count = metaData.getColumnCount();
      String[] columnNames = new String[count];
      for (int i = 1; i <= count; i++) {
        columnNames[i - 1] = metaData.getColumnName(i);
        if (maxRows == 0) continue;
        sb.append(i).append(".").append(columnNames[i - 1]).append("\n");
      }
      int n;
      for (n = 0; n == 0 && maxRows == 0 || rs.next(); n ++) {
        if (maxRows >= 0 && n > maxRows) continue;
        if (n > 0) sb.append("\n");
        sb.append("[").append(n + 1).append("] ");
        for (int i = 1; i <= count; i++) {
          if (i>1) sb.append(", ");
          sb.append(maxRows == 0 ? columnNames[i - 1] : i).append(":").append(rs.getObject(i));
        }
      }
      if (maxRows > 0) {
        sb.insert(0, (n > 0 ? n + " rows total\n" : "no data\n"));
      }
    }
    catch (SQLException e) {
      // nothing
    }
    catch (Exception e) {
      if (errorSink != null) {
        errorSink.consume("row unavailable", e);
      }
    }
    return sb.toString();
  }

  @NotNull
  public static Map<String, Object> resultSetRowToMapSafe(@NotNull ResultSet rs) {
    try {
      Map<String, Object> result = new LinkedHashMap<>();
      ResultSetMetaData metaData = rs.getMetaData();
      int count = metaData.getColumnCount();
      for (int i = 1; i <= count; i++) {
        result.put(metaData.getColumnName(i), rs.getObject(i));
      }
      return result;
    }
    catch (Exception ignored) {
    }
    return Collections.emptyMap();
  }


  @Nullable
  public static String getStringSafe(final ResultSet resultSet, final String columnName, PairConsumer<? super String, ? super Throwable> errorSink) {
    try {
      return resultSet.getString(columnName);
    }
    catch (SQLException ignored) {
    }
    catch (Exception e) {
      columnNotAvailable(columnName, errorSink, e);
    }
    return null;
  }

  public static void closeResultSetSafe(@Nullable final ResultSet resultSet) {
    closeSafe(resultSet);
  }

  public static void closeStatementSafe(@Nullable Statement statement) {
    closeSafe(statement);
  }

  public static void closeConnectionSafe(@Nullable final Connection connection) {
    closeSafe(connection);
  }

  public static void closeSafe(@Nullable AutoCloseable closeable) {
    if (closeable == null) return;
    try {
      closeable.close();
    }
    catch (Exception ignore) { }
  }

  public static boolean hasScaleAndPrecision(int jdbcType) {
    switch(jdbcType) {
      case Types.DECIMAL:
      case Types.NUMERIC:
      case Types.FLOAT:
      case Types.REAL:
      case Types.DOUBLE:
        return true;
      default: return false;
    }
  }

  public static boolean hasLength(int jdbcType) {
    switch(jdbcType) {
      case Types.CHAR:
      case Types.VARCHAR:
      case Types.LONGVARCHAR:
        return true;
      default:return false;
    }
  }

  @NotNull
  public static String getJdbcTypeName(@NotNull DataType dataType) {
    return getJdbcTypeName(dataType, true);
  }

  @NotNull
  public static String getJdbcTypeName(@NotNull final DataType dataType, boolean addLength) {
    final String sqlType = dataType.typeName;
    if (StringUtil.isNotEmpty(sqlType) && sqlType.indexOf('(') > -1) return sqlType;
    final int jdbcType = dataType.jdbcType;
    final String typeName = StringUtil.isNotEmpty(sqlType) ? sqlType : getJdbcTypeName(jdbcType);
    if (addLength) {
      boolean hasLength = hasLength(jdbcType);
      boolean hasScaleAndPrecision = !hasLength && hasScaleAndPrecision(jdbcType);
      int length = hasLength || hasScaleAndPrecision ? dataType.getLength() : -1;
      int scale = hasScaleAndPrecision ? dataType.getScale() : -1;
      if (hasLength && length > 0) {
        return typeName + "(" + length + ")";
      }
      else if (hasScaleAndPrecision && length > 0 && scale >= 0) {
        return typeName + "(" + length + ", " + scale + ")";
      }
    }
    return typeName;
  }

  /**
   * Returns JDBC type name string, i.e. "LONGVARCHAR" for Types.LONGVARCHAR.
   * @param jdbcType one of java.sql.Types.* constants
   * @return type name
   */
  @NotNull
  public static String getJdbcTypeName(final int jdbcType) {
    @NonNls final String result;
    switch (jdbcType) {
      case Types.BIT:
        result = "BIT";
        break;
      case Types.TINYINT:
        result = "TINYINT";
        break;
      case Types.SMALLINT:
        result = "SMALLINT";
        break;
      case Types.INTEGER:
        result = "INTEGER";
        break;
      case Types.BIGINT:
        result = "BIGINT";
        break;
      case Types.FLOAT:
        result = "FLOAT";
        break;
      case Types.REAL:
        result = "REAL";
        break;
      case Types.DOUBLE:
        result = "DOUBLE";
        break;
      case Types.NUMERIC:
        result = "NUMERIC";
        break;
      case Types.DECIMAL:
        result = "DECIMAL";
        break;
      case Types.CHAR:
        result = "CHAR";
        break;
      case Types.VARCHAR:
        result = "VARCHAR";
        break;
      case Types.LONGVARCHAR:
        result = "LONGVARCHAR";
        break;
      case Types.DATE:
        result = "DATE";
        break;
      case Types.TIME:
        result = "TIME";
        break;
      case Types.TIMESTAMP:
        result = "TIMESTAMP";
        break;
      case Types.BINARY:
        result = "BINARY";
        break;
      case Types.VARBINARY:
        result = "VARBINARY";
        break;
      case Types.LONGVARBINARY:
        result = "LONGVARBINARY";
        break;
      case Types.NULL:
        result = "NULL";
        break;
      case Types.OTHER:
        result = "OTHER";
        break;
      case Types.JAVA_OBJECT:
        result = "JAVA_OBJECT";
        break;
      case Types.DISTINCT:
        result = "DISTINCT";
        break;
      case Types.STRUCT:
        result = "STRUCT";
        break;
      case Types.ARRAY:
        result = "ARRAY";
        break;
      case Types.BLOB:
        result = "BLOB";
        break;
      case Types.CLOB:
        result = "CLOB";
        break;
      case Types.REF:
        result = "REF";
        break;
      case Types.DATALINK:
        result = "DATALINK";
        break;
      case Types.BOOLEAN:
        result = "BOOLEAN";
        break;
      default:
        result = "UNKNOWN";
    }
    return result;
  }

  public static int guessJdbcTypeByName(final String name) {
    if (StringUtil.isEmpty(name)) return Types.OTHER;
    @NonNls final String fixed = StringUtil.toUpperCase(name);
    if (fixed.contains("BINARY")) return Types.VARBINARY;
    else if (fixed.contains("BIT")) return Types.BIT;
    else if (fixed.contains("BOOL")) return Types.BOOLEAN;
    else if (fixed.contains("DATE")) return Types.DATE;
    else if (fixed.contains("TIMESTAMP")) return Types.TIMESTAMP;
    else if (fixed.contains("TIME")) return Types.TIME;
    else if (fixed.contains("REAL") || fixed.contains("NUMBER")) return Types.REAL;
    else if (fixed.contains("FLOAT")) return Types.FLOAT;
    else if (fixed.contains("DOUBLE")) return Types.DOUBLE;
    else if (fixed.equals("CHAR") && !fixed.contains("VAR")) return Types.CHAR;
    else if (fixed.contains("INT") && !fixed.contains("INTERVAL")) return Types.INTEGER;
    else if (fixed.contains("DECIMAL")) return Types.DECIMAL;
    else if (fixed.contains("NUMERIC")) return Types.NUMERIC;
    else if (fixed.contains("CHAR") || fixed.contains("TEXT")) return Types.VARCHAR;
    else if (fixed.contains("BLOB")) return Types.BLOB;
    else if (fixed.contains("CLOB")) return Types.CLOB;
    else if (fixed.contains("REFERENCE")) return Types.REF;
    return Types.OTHER;
  }

  public static boolean isNumberType(int jdbcType) {
    boolean result;
    switch (jdbcType) {
      case Types.BIGINT:
      case Types.DECIMAL:
      case Types.DOUBLE:
      case Types.FLOAT:
      case Types.INTEGER:
      case Types.NUMERIC:
        result = true;
        break;
      default:
        result = false;
    }
    return result;
  }

  public static boolean isStringType(int jdbcType) {
    switch (jdbcType) {
      case Types.VARCHAR:
      case Types.CHAR:
      case Types.CLOB:
      case Types.LONGVARCHAR:
      case Types.LONGNVARCHAR:
      case Types.NCHAR:
      case Types.NVARCHAR:
      case Types.NCLOB:
        return true;
      default:
        return false;
    }
  }

  public static boolean isDateTimeType(int jdbcType) {
    switch (jdbcType) {
      case Types.DATE:
      case Types.TIME:
      case Types.TIMESTAMP:
        return true;
      default:
        return false;
    }
  }

  @Nullable
  public static DasForeignKey.RuleAction getRuleAction(short value) {
    if (value == DatabaseMetaData.importedKeyCascade) return DasForeignKey.RuleAction.CASCADE; // 0
    if (value == DatabaseMetaData.importedKeyRestrict) return DasForeignKey.RuleAction.RESTRICT;  // 1
    if (value == DatabaseMetaData.importedKeySetNull) return DasForeignKey.RuleAction.SET_NULL;   // 2
    if (value == DatabaseMetaData.importedKeyNoAction) return DasForeignKey.RuleAction.NO_ACTION;   // 3
    if (value == DatabaseMetaData.importedKeySetDefault) return DasForeignKey.RuleAction.SET_DEFAULT; // 4
    return null;
  }

  @Nullable
  public static DasForeignKey.Deferrability getDeferrability(short value) {
    if (value == DatabaseMetaData.importedKeyInitiallyDeferred) return DasForeignKey.Deferrability.INITIALLY_DEFERRED; // 5
    if (value == DatabaseMetaData.importedKeyInitiallyImmediate) return DasForeignKey.Deferrability.INITIALLY_IMMEDIATE;  // 6
    if (value == DatabaseMetaData.importedKeyNotDeferrable) return DasForeignKey.Deferrability.NOT_DEFERRABLE;             // 7
    return null;
  }

  @NotNull
  public static String getLongMessage(@NotNull Throwable e) {
    return getMessagePrefix(e) + getMessage(e);
  }

  @NotNull
  public static String getMessage(@NotNull Throwable t) {
    String m = t.getMessage();
    return StringUtil.isNotEmpty(m) ? m.trim() : t.getClass().getName();
  }

  @NotNull
  public static String getMessagePrefix(@NotNull Throwable t) {
    if (!(t instanceof SQLException)) return "";
    SQLException e = (SQLException)t;
    String state = e.getSQLState();
    int code = e.getErrorCode();
    if (StringUtil.isEmpty(state)) {
      return code != 0 ? "[" + code + "] " : "";
    }
    else if (code != 0) {
      return "[" + state + "][" + code + "] ";
    }
    else {
      return "[" + state + "] ";
    }
  }
}
