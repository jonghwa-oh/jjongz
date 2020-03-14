package com.intellij.sql.psi;

import com.intellij.database.model.DataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Types;

/**
 * @author Gregory.Shrago
 */
public abstract class SqlType {

  @NotNull
  public abstract String getDisplayName();

  @NotNull
  public abstract Category getCategory();

  @NotNull
  public abstract DataType getDataType();

  public static final SqlPrimitiveType UNKNOWN = new SqlPrimitiveType(DataType.UNKNOWN, Category.UNKNOWN){};

  public enum Category {
    UNKNOWN,
    DEFAULT,
    INTEGER,
    REAL,
    STRING,
    BOOLEAN,
    DATE_TIME,
    DATE,
    TIME,
    TIMESTAMP,
    INTERVAL,
    BYTES,
    REFERENCE,
    ARRAY,
    COLLECTION,
    TABLE,
    RECORD,
    SETOF;

    public boolean is(@Nullable SqlType type) { return type != null && type.getCategory() == this; }

    public static Category findByJdbcType(final int jdbcType) {
      switch (jdbcType) {
        case Types.BIT:
        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.INTEGER:
        case Types.BIGINT:
        case Types.CHAR:
          return INTEGER;
        case Types.FLOAT:
        case Types.REAL:
        case Types.DOUBLE:
        case Types.NUMERIC:
        case Types.DECIMAL:
          return REAL;
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
        case Types.CLOB:
        case Types.NCLOB:
          return STRING;
        case Types.DATE:
          return DATE;
        case Types.TIME:
          return TIME;
        case Types.TIMESTAMP:
          return TIMESTAMP;
        case Types.BINARY:
        case Types.VARBINARY:
        case Types.LONGVARBINARY:
        case Types.JAVA_OBJECT:
        case Types.BLOB:
          return BYTES;
        //case Types.DISTINCT:
        //case Types.STRUCT:
        //case Types.OTHER:
        case Types.ARRAY:
          return ARRAY;
        //case Types.REF:
        //case Types.DATALINK:
        case Types.BOOLEAN:
          return BOOLEAN;
        default:
          return UNKNOWN;
      }
    }

  }

  @Override
  public String toString() {
    return getDisplayName();
  }
}
