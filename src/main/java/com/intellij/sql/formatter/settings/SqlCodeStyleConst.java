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
package com.intellij.sql.formatter.settings;

import org.intellij.lang.annotations.MagicConstant;

/**
 * @author Leonid Bushuev
 */
public interface SqlCodeStyleConst {

  /**
   * Name of the code style preview file — for files with this name formatter doesn't select a style related to data sources.
   */
  String PREVIEW_FILE_NAME = "preview\u22C8style.sql";


  // @formatter:off

  /// CASING \\\

  int TO_UPPER       = 0;
  int TO_LOWER       = 1;
  int DO_NOT_CHANGE  = 2;
  int AS_KEYWORDS    = 3;
  int AS_IDENTIFIERS = 4;
  int TO_TITLE       = 5;

  int[]    TOKEN_CASE =     {DO_NOT_CHANGE, TO_UPPER, TO_LOWER, TO_TITLE};
  int[]    TOKEN_CASE_EXT = {DO_NOT_CHANGE, AS_KEYWORDS, TO_UPPER, TO_LOWER, TO_TITLE};
  int[]    ALIAS_CASE_EXT = {DO_NOT_CHANGE, AS_IDENTIFIERS, TO_UPPER, TO_LOWER, TO_TITLE};
  String[] TOKEN_CASE_NAMES =     {"Do not change", "To upper", "To lower", "To title"};
  String[] TOKEN_CASE_NAMES_EXT = {"Do not change", "As keywords", "To upper", "To lower", "To title"};
  String[] ALIAS_CASE_NAMES =     {"Do not change", "As identifiers", "To upper", "To lower", "To title"};

  @MagicConstant(intValues = {TO_UPPER, TO_LOWER, TO_TITLE, DO_NOT_CHANGE})
  @interface IdentifierCase {}

  @MagicConstant(intValues = {TO_UPPER, TO_LOWER, TO_TITLE, DO_NOT_CHANGE, AS_KEYWORDS})
  @interface IdentifierCaseExt {}

  @MagicConstant(intValues = {TO_UPPER, TO_LOWER, TO_TITLE, DO_NOT_CHANGE, AS_IDENTIFIERS})
  @interface AliasCase {}


  /// QUOTATION \\\

  int QUOTE   = 0;
  int UNQUOTE = 1;

  int QUOTE_AUTO              = 0;
  int QUOTE_DOUBLE_QUOTE_AUTO = 10;
  int QUOTE_DOUBLE_QUOTE_ONLY = 11;
  int QUOTE_BRACKET_AUTO      = 20;
  int QUOTE_BRACKET_ONLY      = 21;
  int QUOTE_GRAVE_ACCENT_AUTO = 30;
  int QUOTE_GRAVE_ACCENT_ONLY = 31;

  int[]    QUOTE_UNQUOTE_OPTIONS = {DO_NOT_CHANGE, QUOTE, UNQUOTE};
  String[] QUOTE_UNQUOTE_OPTIONS_NAMES = {"Do not change", "Quote", "Unquote"};

  int[]    QUOTE_TYPE_ALL_OPTIONS = {QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_ONLY, QUOTE_BRACKET_AUTO, QUOTE_BRACKET_ONLY, QUOTE_GRAVE_ACCENT_AUTO, QUOTE_GRAVE_ACCENT_ONLY};
  String[] QUOTE_TYPE_ALL_OPTIONS_NAMES = {"Auto", "\"Double Quotes\" or another", "\"Double Quotes\" only", "[Brackets] or \"Double Quotes\"", "[Brackets] only", "`Grave Accents` or \"Double Quotes\"", "`Grave Accents` only"};

  int[]    QUOTE_TYPE_BR_OPTIONS = {QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_ONLY, QUOTE_BRACKET_AUTO, QUOTE_BRACKET_ONLY};
  String[] QUOTE_TYPE_BR_OPTIONS_NAMES = {"Auto", "\"Double Quotes\" or [Brackets]", "\"Double Quotes\" only", "[Brackets] or \"Double Quotes\"", "[Brackets] only"};

  int[]    QUOTE_TYPE_GA_OPTIONS = {QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_ONLY, QUOTE_GRAVE_ACCENT_AUTO, QUOTE_GRAVE_ACCENT_ONLY};
  String[] QUOTE_TYPE_GA_OPTIONS_NAMES = {"Auto", "\"Double Quotes\" or `Grave Accent`", "\"Double Quotes\" only", "`Grave Accents` or \"Double Quotes\"", "`Grave Accents` only"};


  /// COMMON STUFF \\\

  int COMMONLY  = -1;
  int AS_IS     = 0;

  int ADD       = 1;
  int REMOVE    = 2;

  int[]    VALUES_AS_IS_ADD_REMOVE = {AS_IS, ADD, REMOVE};
  String[] NAMES_DONT_CHANGE_YES_NO = {"Do not change", "Yes", "No"};

  @MagicConstant(intValues = {AS_IS, ADD, REMOVE})
  @interface AddRemoveAsIsMagicValues {}


  // OPENING PARENTHESIS

  int OPENING_SAME       = 1;
  int OPENING_UNINDENT   = 2;
  int OPENING_ALIGN      = 3;
  int OPENING_INDENT     = 4;

  @MagicConstant(intValues = {AS_IS, OPENING_SAME, OPENING_UNINDENT, OPENING_ALIGN, OPENING_INDENT})
  @interface OpeningMagicValues {}

  String   OPENING_SETTING_NAME = "Place the opening parenthesis";
  int[]    OPENING_VALUES_ALL   = {AS_IS,           OPENING_SAME,       OPENING_UNINDENT, OPENING_ALIGN, OPENING_INDENT};
  String[] OPENING_NAMES_ALL    = {"Do not change", "On the same line", "Unindented",     "Aligned",     "Indented"    };
  int[]    OPENING_VALUES_CRT   = {AS_IS,           OPENING_SAME,                         OPENING_ALIGN, OPENING_INDENT};
  String[] OPENING_NAMES_CRT    = {"Do not change", "On the same line",                   "Aligned",     "Indented"    };

  // CONTENT INDENTATION

  int CONTENT_SAME_ALIGNED     = 1;
  int CONTENT_WRAPPED_EGYPT    = 2;
  int CONTENT_WRAPPED_ALIGNED  = 3;
  int CONTENT_WRAPPED_INDENTED = 4;

  @MagicConstant(intValues = {AS_IS, CONTENT_SAME_ALIGNED, CONTENT_WRAPPED_EGYPT, CONTENT_WRAPPED_ALIGNED, CONTENT_WRAPPED_INDENTED})
  @interface ContentMagicValues {}

  String   CONTENT_SETTING_NAME = "Place elements";
  int[]    CONTENT_VALUES       = {AS_IS,           CONTENT_SAME_ALIGNED, CONTENT_WRAPPED_EGYPT, CONTENT_WRAPPED_ALIGNED, CONTENT_WRAPPED_INDENTED};
  String[] CONTENT_NAMES        = {"Do not change", "Same line aligned",  "Wrapped unindented",  "Wrapped aligned",       "Wrapped indented"      };

  // CLOSING PARENTHESIS

  int CLOSING_SAME            = 1;
  int CLOSING_EGYPT           = 2;
  int CLOSING_UNDER_BEGIN     = 3;
  int CLOSING_UNDER_OPENING   = 4;
  int CLOSING_UNDER_OPENING_R = 5;
  int CLOSING_UNDER_ELEMENT   = 6;

  @MagicConstant(intValues = {AS_IS, CLOSING_SAME, CLOSING_EGYPT, CLOSING_UNDER_BEGIN, CLOSING_UNDER_OPENING, CLOSING_UNDER_OPENING_R, CLOSING_UNDER_ELEMENT})
  @interface ClosingMagicValues {}

  String   CLOSING_SETTING_NAME      = "Place the closing parenthesis";
  int[]    CLOSING_VALUES_FROM_BEGIN = {AS_IS,           CLOSING_SAME,                CLOSING_UNDER_BEGIN, CLOSING_UNDER_OPENING, /*CLOSING_UNDER_OPENING_R,*/ CLOSING_UNDER_ELEMENT};
  String[] CLOSING_NAMES_FROM_BEGIN  = {"Do not change", "At the end",                "To begin",          "Under opening",       /*"Under opening + 1",    */ "Under elements"     };
  int[]    CLOSING_VALUES_EGYPT      = {AS_IS,           CLOSING_SAME, CLOSING_EGYPT, CLOSING_UNDER_BEGIN, CLOSING_UNDER_OPENING, /*CLOSING_UNDER_OPENING_R,*/ CLOSING_UNDER_ELEMENT};
  String[] CLOSING_NAMES_EGYPT       = {"Do not change", "At the end", "Unindented",  "To begin",          "Under opening",       /*"Under opening + 1",    */ "Under elements"     };

  // ELEMENTS COMMA/SEPARATOR

  int EL_COMMA_1ST    = 1;
  int EL_COMMA_LAST   = 2;
  int EL_COMMA_EGYPT  = 4;  // is used with multi-row VALUES

  @MagicConstant(intValues = {COMMONLY, AS_IS, EL_COMMA_1ST, EL_COMMA_LAST})
  @interface CommaMagicValues {}

  @MagicConstant(intValues = {COMMONLY, AS_IS, EL_COMMA_1ST, EL_COMMA_LAST, EL_COMMA_EGYPT})
  @interface CommaPlusMagicValues {}


  String   EL_COMMA_SETTING_NAME        = "Place comma";
  int[]    EL_COMMA_VALUES              = {AS_IS, EL_COMMA_1ST, EL_COMMA_LAST};
  String[] EL_COMMA_NAMES               = {"Auto", "To begin", "To end"};
  int[]    EL_COMMA_VALUES_C            = {COMMONLY, AS_IS, EL_COMMA_1ST, EL_COMMA_LAST};
  String[] EL_COMMA_NAMES_C             = {"As in common", "Auto", "To begin", "To end"};
  int[]    EL_COMMA_VALUES_M            = {COMMONLY, AS_IS, EL_COMMA_1ST, EL_COMMA_EGYPT, EL_COMMA_LAST};
  String[] EL_COMMA_NAMES_M             = {"As in common", "Auto", "To begin", "In the middle", "To end"};

  // ELEMENTS WRAP

  int EL_CHOP      = 1;  // chop always
  int EL_CHOP_LONG = 2;  // chop if long
  int EL_WRAP      = 3;  // wrap

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP})
  @interface WrapMagicValues {}

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP})
  @interface Wrap2MagicValues {}

  String   EL_WRAP_SETTING_NAME = "Wrap elements";
  int[]    EL_WRAP_VALUES       = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP};
  String[] EL_WRAP_NAMES        = {"Do not change", "Chop", "Chop if long", "Wrap if long"};

  int[]    EL_WRAP_VALUES_2 = {AS_IS, EL_CHOP, EL_CHOP_LONG};
  String[] EL_WRAP_NAMES_2  = {"Do not change", "Chop", "Chop if long"};


  // SECTION FIRST WORD

  int QUERY_SECTION_1ST_WORD_ALIGN_LEFT        = 1;
  int QUERY_SECTION_1ST_WORD_ALIGN_LEFT_INDENT = 2;
  int QUERY_SECTION_1ST_WORD_ALIGN_RIGHT       = 8;

  int[]    QUERY_SECTION_1ST_WORD_ALIGN_VALUES = {AS_IS, QUERY_SECTION_1ST_WORD_ALIGN_LEFT, QUERY_SECTION_1ST_WORD_ALIGN_LEFT_INDENT, QUERY_SECTION_1ST_WORD_ALIGN_RIGHT};
  String[] QUERY_SECTION_1ST_WORD_ALIGN_NAMES  = {"Do not change", "To left", "To left with indent", "To right"};


  // ELEMENTS LINE

  int EL_SAME      = 1;    // on the same line
  int EL_INDENT    = 101;  // indent on the new line
  int EL_INDENT_2  = 102;  // indent on the new line if 2 or more

  String   EL_COMMON_LINE_SETTING_NAME  = "Place clause elements on";
  int[]    EL_COMMON_LINE_VALUES        = {AS_IS, EL_SAME, EL_INDENT};
  String[] EL_COMMON_LINE_NAMES         = {"Do not change", "Same line", "New line"};

  String   EL_SECTION_LINE_SETTING_NAME = "Place elements on";
  int[]    EL_SECTION_LINE_VALUES       = {COMMONLY, AS_IS, EL_SAME, EL_INDENT};
  String[] EL_SECTION_LINE_NAMES        = {"As in common", "Do not change", "Same line", "New line"};

  @MagicConstant(intValues = {COMMONLY, AS_IS, EL_SAME, EL_INDENT})
  @interface ElementsLineMagicValues {}



  /// QUERY ITSELF \\

  int QUERY_IN_ONE_STRING_NO         = 1;
  int QUERY_IN_ONE_STRING_INNER_ONLY = 2;
  int QUERY_IN_ONE_STRING_YES        = 3;

  int[] QUERY_IN_ONE_STRING_VALUES = { AS_IS, QUERY_IN_ONE_STRING_NO, QUERY_IN_ONE_STRING_INNER_ONLY, QUERY_IN_ONE_STRING_YES };
  String[] QUERY_IN_ONE_STRING_NAMES = { "Do not change", "Never", "Subqueries only", "Always" };


  /// SUBQUERY \\\

  @Deprecated int SUBQUERY_R_PAR_ALIGN_TO_L_PAR   = 1;
  @Deprecated int SUBQUERY_R_PAR_ALIGN_TO_L_PAR_2 = 2;
  @Deprecated int SUBQUERY_R_PAR_ALIGN_TO_QUERY   = 3;

  @Deprecated
  int[] SUBQUERY_R_PAR_ALIGN_VALUES = { AS_IS,
                                        SUBQUERY_R_PAR_ALIGN_TO_L_PAR,
                                        //SUBQUERY_R_PAR_ALIGN_TO_L_PAR_2,
                                        SUBQUERY_R_PAR_ALIGN_TO_QUERY };
  @Deprecated
  String[] SUBQUERY_R_PAR_ALIGN_NAMES = { "Do not change",
                                          "With `(`",
                                          //"With the next position to `(`",
                                          "With subquery" };


  /// SECTION SELECT \\\

  int[] SELECT_KEEP_N_ITEMS_IN_LINE_VALUES = {0, 1, 2, 3, 4, 5, 6, 7};
  String[] SELECT_KEEP_N_ITEMS_IN_LINE_NAMES = {"0", "1", "2", "3", "4", "5", "6", "7"};

  int[] SELECT_USE_AS_VALUES = {AS_IS, ADD, REMOVE};
  String[] SELECT_USE_AS_NAMES = {"Do not change", "Add always", "Remove"};


  /// SECTION FROM \\\

  int FROM_ONLY_JOIN_INDENT_AS_USUAL        = -1;
  int FROM_ONLY_JOIN_INDENT_UNINDENT_LITE   = 1;
  int FROM_ONLY_JOIN_INDENT_UNINDENT_STRONG = 2;

  @MagicConstant(intValues = {FROM_ONLY_JOIN_INDENT_AS_USUAL, FROM_ONLY_JOIN_INDENT_UNINDENT_LITE, FROM_ONLY_JOIN_INDENT_UNINDENT_STRONG})
  @interface FromOnlyJoinIndentValues {}

  int[] FROM_ONLY_JOIN_INDENT_VALUES = {FROM_ONLY_JOIN_INDENT_AS_USUAL, FROM_ONLY_JOIN_INDENT_UNINDENT_LITE, FROM_ONLY_JOIN_INDENT_UNINDENT_STRONG};
  String[] FROM_ONLY_JOIN_INDENT_NAMES = {"Table (as usual)", "FROM indented", "FROM"};


  int FROM_PLACE_ON_JOIN             = 10;
  int FROM_PLACE_ON_JOIN_INDENT      = 11;
  int FROM_PLACE_ON_JOIN_INDENT_CONT = 12;
  int FROM_PLACE_ON_TABLE            = 20;
  int FROM_PLACE_ON_TABLE_INDENT     = 21;

  @MagicConstant(intValues = {FROM_PLACE_ON_JOIN, FROM_PLACE_ON_JOIN_INDENT, FROM_PLACE_ON_JOIN_INDENT_CONT, FROM_PLACE_ON_TABLE, FROM_PLACE_ON_TABLE_INDENT})
  @interface FromPlaceOnValues {}

  int[] FROM_PLACE_ON_VALUES = {FROM_PLACE_ON_JOIN, /*FROM_PLACE_ON_JOIN_INDENT, FROM_PLACE_ON_JOIN_INDENT_CONT,*/ FROM_PLACE_ON_TABLE, FROM_PLACE_ON_TABLE_INDENT};
  String[] FROM_PLACE_ON_NAMES = {"JOIN", /*"JOIN indented", "JOIN indented continuously",*/ "Table", "Table indented"};


  /// EXPRESSIONS \\\

  int CORTEGE_CLOSING_UNDER_OPENING = 1;
  int CORTEGE_CLOSING_UNDER_ITEM = 2;
  int CORTEGE_CLOSING_AT_THE_END = 9;

  int[] CORTEGE_CLOSING_VALUES = {AS_IS, CORTEGE_CLOSING_UNDER_OPENING, CORTEGE_CLOSING_UNDER_ITEM, CORTEGE_CLOSING_AT_THE_END};
  String[]  CORTEGE_CLOSING_NAMES = {"Do not change", "With `(`", "With elements", "To end"};

  int EXPR_CASE_END_ALIGN_CASE = 1;
  int EXPR_CASE_END_ALIGN_WHEN = 2;
  int EXPR_CASE_END_TO_THE_END = 9;

  int[] EXPR_CASE_END_ALIGN_VALUES = {AS_IS, EXPR_CASE_END_ALIGN_CASE, EXPR_CASE_END_ALIGN_WHEN, EXPR_CASE_END_TO_THE_END};
  String[]  EXPR_CASE_END_ALIGN_NAMES = {"Do not change", "Align with CASE", "Align with WHEN", "To end"};


  /// DEPRECATED \\\

  @Deprecated int ALWAYS           = 0;
  @Deprecated int IF_MORE_THAN_ONE = 1;
  @Deprecated int NEVER            = 2;

  @Deprecated int[]    NEW_LINE_AFTER_SELECT_VALUES = {ALWAYS, IF_MORE_THAN_ONE, NEVER};
  @Deprecated String[] NEW_LINE_AFTER_SELECT_NAMES = {"Always", "If more than one", "Never"};

  // @formatter:on

}
