package com.intellij.sql.psi;

import com.intellij.psi.tree.TokenSet;

import static com.intellij.sql.util.SqlTokenRegistry.getType;

public interface SqlCommonTokens {
  SqlTokenType SQL_ASTERISK = getType("*");
  SqlTokenType SQL_PERIOD = getType(".");
  SqlTokenType SQL_DOUBLE_PERIOD = getType("..");
  SqlTokenType SQL_COLON = getType(":");
  SqlTokenType SQL_AT_SIGN = getType("@");
  SqlTokenType SQL_PERCENT_SIGN = getType("%");
  SqlTokenType SQL_COMMA = getType(",");
  SqlTokenType SQL_HASHMARK = getType("#");

  SqlTokenType SQL_LEFT_PAREN = getType("(");
  SqlTokenType SQL_RIGHT_PAREN = getType(")");
  SqlTokenType SQL_LEFT_BRACKET = getType("[");
  SqlTokenType SQL_RIGHT_BRACKET = getType("]");
  SqlTokenType SQL_LEFT_BRACE = getType("{");
  SqlTokenType SQL_RIGHT_BRACE = getType("}");

  SqlTokenType SQL_DOLLAR = getType("$");
  SqlTokenType SQL_SEMICOLON = getType(";");
  SqlTokenType SQL_UNDERSCORE = getType("_");
  SqlTokenType SQL_QUESTION_MARK = getType("?");
  SqlTokenType SQL_DOUBLE_COLON = getType("::");
  SqlTokenType SQL_ELLIPSES = getType("...");

  // operators
  SqlTokenType SQL_OP_GT = getType(">");
  SqlTokenType SQL_OP_LT = getType("<");
  SqlTokenType SQL_OP_NEQ = getType("<>");
  SqlTokenType SQL_OP_LE = getType("<=");
  SqlTokenType SQL_OP_GE = getType(">=");
  SqlTokenType SQL_OP_PLUS = getType("+");
  SqlTokenType SQL_OP_MINUS = getType("-");
  SqlTokenType SQL_OP_DIV = getType("/");
  SqlTokenType SQL_OP_MUL = getType("*");
  SqlTokenType SQL_OP_EQ = getType("=");
  SqlTokenType SQL_OP_CONCAT = getType("||");

  // MySQL
  SqlTokenType SQL_OP_LOGICAL_AND = getType("&&");
  SqlTokenType SQL_OP_BITWISE_AND = getType("&");
  SqlTokenType SQL_OP_INVERT = getType("~");
  SqlTokenType SQL_OP_BITWISE_OR = getType("|");
  SqlTokenType SQL_OP_LOGICAL_OR = getType("||");
  SqlTokenType SQL_OP_BITWISE_XOR = getType("^");
  SqlTokenType SQL_OP_NULLSAFE_EQ = getType("<=>");
  SqlTokenType SQL_OP_LEFT_SHIFT = getType("<<");
  SqlTokenType SQL_OP_RIGHT_SHIFT = getType(">>");
  SqlTokenType SQL_OP_JSON_EXTRACT = getType("->");
  SqlTokenType SQL_OP_JSON_EXTRACT_UNQUOTE = getType("->>");
  SqlTokenType SQL_OP_MODULO = getType("%");
  SqlTokenType SQL_OP_NEQ2 = getType("!=");
  SqlTokenType SQL_OP_NEQ3 = getType("^=");
  SqlTokenType SQL_OP_NEQ4 = getType("~=");
  SqlTokenType SQL_OP_NOT2 = getType("!");
  SqlTokenType SQL_OP_ASSIGN = getType(":=");
  SqlTokenType SQL_OP_EQEQ = getType("==");

  // Oracle
  SqlTokenType ORA_OP_JOIN = getType("(+)");
  SqlTokenType ORA_POWER = getType("**");
  SqlTokenType ORA_OP_NAMED_PARAM_BINDING = getType("=>");
  SqlTokenType SQL_OP_NEQ_WS = getType("< >");
  SqlTokenType SQL_OP_NEQ2_WS = getType("! =");
  SqlTokenType SQL_OP_NEQ3_WS = getType("^ =");
  SqlTokenType SQL_OP_NEQ4_WS = getType("~ =");
  SqlTokenType SQL_OP_LE_WS = getType("< =");
  SqlTokenType SQL_OP_GE_WS = getType("> =");
  SqlTokenType SQL_LEFT_ANGLES = getType("<<");
  SqlTokenType SQL_RIGHT_ANGLES = getType(">>");

  // PostgreSQL
  SqlTokenType PG_OP_TYPE_CAST = SQL_DOUBLE_COLON;
  SqlTokenType PG_OP_BITWISE_XOR = getType("#");
  SqlTokenType SQL_OP_RANGE = getType("..");
  SqlTokenType PG_OP_PRIME = getType("`");
  SqlTokenType PG_OP_EXP = getType("^");
  SqlTokenType PG_OP_ABS = getType("@");
  SqlTokenType PG_OP_CUSTOM = getType("<operator>");

  // PostgreSQL:psql tool
  SqlTokenType PG_PSQL_BACKSLASH = getType("\\");
  SqlTokenType PG_COPY_TERMINATOR = getType("\\.");

  // Vertica
  SqlTokenType VERT_DIV_INT = getType("//");
  SqlTokenType VERT_FAIL_CAST = getType("::!");

  // Transact SQL
  SqlTokenType SQL_OP_NOT_LT = getType("!<");
  SqlTokenType SQL_OP_NOT_GT = getType("!>");
  SqlTokenType SQL_OP_BITWISE_NOT = getType("~");
  SqlTokenType SQL_OP_PLUS_EQ = getType("+=");
  SqlTokenType SQL_OP_MINUS_EQ = getType("-=");
  SqlTokenType SQL_OP_MUL_EQ = getType("*=");
  SqlTokenType SQL_OP_EQ_MUL = getType("=*");
  SqlTokenType SQL_OP_DIV_EQ = getType("/=");
  SqlTokenType SQL_OP_MODULO_EQ = getType("%=");
  SqlTokenType SQL_OP_BITWISE_AND_EQ = getType("&=");
  SqlTokenType SQL_OP_BITWISE_OR_EQ = getType("|=");
  SqlTokenType SQL_OP_BITWISE_XOR_EQ = SQL_OP_NEQ3;

  TokenSet STATEMENT_SEPARATORS = TokenSet.create(SQL_SEMICOLON);
}
