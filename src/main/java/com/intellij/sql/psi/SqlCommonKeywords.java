/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License", SqlKeywordTokenType.FACTORY);
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
package com.intellij.sql.psi;

import static com.intellij.sql.util.SqlTokenRegistry.getType;

/**
 * @author Gregory.Shrago
 */
public interface SqlCommonKeywords {
  SqlTokenType SQL_ACTION = getType("ACTION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ADD = getType("ADD", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_AFTER = getType("AFTER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ALL = getType("ALL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ALTER = getType("ALTER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ALWAYS = getType("ALWAYS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_AND = getType("AND", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ANY = getType("ANY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_APPLY = getType("APPLY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ARRAY = getType("ARRAY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_AS = getType("AS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ASC = getType("ASC", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ASCENDING = getType("ASCENDING", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_AT = getType("AT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_AUTONOMOUS_TRANSACTION = getType("AUTONOMOUS_TRANSACTION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_BEFORE = getType("BEFORE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_BEGIN = getType("BEGIN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_BETWEEN = getType("BETWEEN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_BODY = getType("BODY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_BREAK = getType("BREAK", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_BY = getType("BY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ERRCODE = getType("ERRCODE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CASE = getType("CASE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CALL = getType("CALL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CASCADE = getType("CASCADE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CATCH = getType("CATCH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CHANGE = getType("CHANGE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CHECK = getType("CHECK", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_COLLATE = getType("COLLATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_COLLATION = getType("COLLATION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_COMMIT = getType("COMMIT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_COMPILE = getType("COMPILE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CONFLICT = getType("CONFLICT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CONNECT = getType("CONNECT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CONSTANT = getType("CONSTANT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CONSTRAINT = getType("CONSTRAINT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CONTINUE = getType("CONTINUE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_COPY = getType("COPY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CREATE = getType("CREATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CROSS = getType("CROSS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CUBE = getType("CUBE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_CURSOR = getType("CURSOR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DATA = getType("DATA", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DATE = getType("DATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DAY = getType("DAY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DEBUG = getType("DEBUG", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DESC = getType("DESC", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DECLARE = getType("DECLARE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DEFAULT = getType("DEFAULT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DEFERRABLE = getType("DEFERRABLE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DEFERRED = getType("DEFERRED", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DELETE = getType("DELETE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DISTINCT = getType("DISTINCT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DISTINCTROW = getType("DISTINCTROW", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DO = getType("DO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DROP = getType("DROP", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_DUPLICATE = getType("DUPLICATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EDITIONABLE = getType("EDITIONABLE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NONEDITIONABLE = getType("NONEDITIONABLE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ELSE = getType("ELSE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ELSEIF = getType("ELSEIF", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ELSIF = getType("ELSIF", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_END = getType("END", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ENUM = getType("ENUM", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ESCAPE = getType("ESCAPE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EVENT = getType("EVENT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EXCEPT = getType("EXCEPT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EXCEPTION = getType("EXCEPTION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SQLEXCEPTION = getType("SQLEXCEPTION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EXECUTE = getType("EXECUTE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EXISTS = getType("EXISTS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EXIT = getType("EXIT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_EXPLAIN = getType("EXPLAIN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FALSE = getType("FALSE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FOR = getType("FOR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FOREACH = getType("FOREACH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FORCE = getType("FORCE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FOREIGN = getType("FOREIGN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FROM = getType("FROM", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FULL = getType("FULL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_FUNCTION = getType("FUNCTION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_GROUP = getType("GROUP", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_GO = getType("GO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_GROUPING = getType("GROUPING", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_HASH = getType("HASH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_HAVING = getType("HAVING", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_HOUR = getType("HOUR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_IDENTITY = getType("IDENTITY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_IDENTITY_INSERT = getType("IDENTITY_INSERT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_IN = getType("IN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INDEX = getType("INDEX", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INDICATOR = getType("INDICATOR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INFO = getType("INFO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INITIALLY = getType("INITIALLY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INNER = getType("INNER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INSERT = getType("INSERT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INSTEAD = getType("INSTEAD", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INTERSECT = getType("INTERSECT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INTERVAL = getType("INTERVAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INTO = getType("INTO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_IS = getType("IS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ITERATE = getType("ITERATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_KEY = getType("KEY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_JOIN = getType("JOIN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LAST = getType("LAST", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LEAVE = getType("LEAVE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LIKE = getType("LIKE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LIMIT = getType("LIMIT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LANGUAGE = getType("LANGUAGE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LATERAL = getType("LATERAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LIBRARY = getType("LIBRARY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LOCAL = getType("LOCAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LOG = getType("LOG", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MATCH = getType("MATCH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MERGE = getType("MERGE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MINUTE = getType("MINUTE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MODIFY = getType("MODIFY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MODULE = getType("MODULE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MONTH = getType("MONTH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_MINUS = getType("MINUS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NAME = getType("NAME", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NATURAL = getType("NATURAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NO = getType("NO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NOT = getType("NOT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NOTICE = getType("NOTICE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NULL = getType("NULL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_NULLS = getType("NULLS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OFFSET = getType("OFFSET", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OIDS = getType("OIDS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ON = getType("ON", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ONLY = getType("ONLY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OPTION = getType("OPTION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OR = getType("OR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ORDER = getType("ORDER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ORDINALITY = getType("ORDINALITY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OTHERS = getType("OTHERS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OVERLAPS = getType("OVERLAPS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PARTIAL = getType("PARTIAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PCTFREE = getType("PCTFREE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PCTUSED = getType("PCTUSED", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PRAGMA = getType("PRAGMA", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PRESERVE = getType("PRESERVE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PRIMARY = getType("PRIMARY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PRIOR = getType("PRIOR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PROCEDURE = getType("PROCEDURE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RAISE = getType("RAISE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RAISERROR = getType("RAISERROR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_READ = getType("READ", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REFERENCES = getType("REFERENCES", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REDUCE = getType("REDUCE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REDISTRIBUTE = getType("REDISTRIBUTE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REFRESH = getType("REFRESH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REMOTE = getType("REMOTE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REPLACE = getType("REPLACE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REPLICATE = getType("REPLICATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RESIGNAL = getType("RESIGNAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RESTRICT = getType("RESTRICT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RETURN = getType("RETURN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REVERSE = getType("REVERSE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ROLLUP = getType("ROLLUP", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ROUTINE = getType("ROUTINE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ROWS = getType("ROWS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SECOND = getType("SECOND", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SET = getType("SET", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SIGNAL = getType("SIGNAL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SOME = getType("SOME", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SELECT = getType("SELECT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_START = getType("START", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_STDIN = getType("STDIN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_STORAGE = getType("STORAGE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SQL = getType("SQL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TABLE = getType("TABLE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TABLESPACE = getType("TABLESPACE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TEMPORARY = getType("TEMPORARY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_THEN = getType("THEN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_THROW = getType("THROW", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TIME = getType("TIME", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TIMESTAMP = getType("TIMESTAMP", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TO = getType("TO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TOTALS = getType("TOTALS", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TRIGGER = getType("TRIGGER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TRUE = getType("TRUE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TRY = getType("TRY", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_TYPE = getType("TYPE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UNDER = getType("UNDER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UNDO = getType("UNDO", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UNION = getType("UNION", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UNIQUE = getType("UNIQUE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UNKNOWN = getType("UNKNOWN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UNTIL = getType("UNTIL", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_UPDATE = getType("UPDATE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_USER = getType("USER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_USING = getType("USING", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_VALUE = getType("VALUE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_VALUES = getType("VALUES", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_VIEW = getType("VIEW", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_SQLWARNING = getType("SQLWARNING", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_WARNING = getType("WARNING", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_WHEN = getType("WHEN", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_WHERE = getType("WHERE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_WHILE = getType("WHILE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_WITH = getType("WITH", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_WITHOUT = getType("WITHOUT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_YEAR = getType("YEAR", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ZONE = getType("ZONE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_ROW = getType("ROW", SqlKeywordTokenType.FACTORY);

  SqlTokenType SQL_OUT = getType("OUT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_OUTER = getType("OUTER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_INOUT = getType("INOUT", SqlKeywordTokenType.FACTORY);
  
  SqlTokenType SQL_LEFT = getType("LEFT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RIGHT = getType("RIGHT", SqlKeywordTokenType.FACTORY);
  
  SqlTokenType SQL_PACKAGE = getType("PACKAGE", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_PERFORM = getType("PERFORM", SqlKeywordTokenType.FACTORY);

  SqlTokenType SQL_DELIMITER = getType("DELIMITER", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_IF = getType("IF", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RENAME = getType("RENAME", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_REPEAT = getType("REPEAT", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_LOOP = getType("LOOP", SqlKeywordTokenType.FACTORY);
  SqlTokenType SQL_RECURSIVE = getType("RECURSIVE", SqlKeywordTokenType.FACTORY);
}