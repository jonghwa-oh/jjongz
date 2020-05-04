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

import com.intellij.configurationStore.Property;
import com.intellij.database.util.Case;
import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.sql.psi.SqlLanguage;
import org.intellij.lang.annotations.MagicConstant;
import org.jdom.Element;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

import static com.intellij.psi.codeStyle.CommonCodeStyleSettings.*;
import static com.intellij.sql.formatter.settings.SqlCodeStyleSettingsDefault.LEGACY;
import static com.intellij.sql.formatter.settings.SqlCodeStyleSettingsDefault.MODERN;
import static java.lang.String.format;


/**
 * @author Gregory.Shrago, Leonid Bushuev, ignatov
 */
@SuppressWarnings({"SameParameterValue", "DeprecatedIsStillUsed"})
public class SqlCodeStyleSettings extends CustomCodeStyleSettings implements SqlCodeStyleConst {

  public int myVersion = SqlCodeStyleSettingsDefault.CURRENT_VERSION;
  protected boolean myInitialized = false;

  private static final Set<String> SETTINGS_TO_HIDE = Collections.emptySet();
  
  
  /// INITIALIZATION \\\

  public SqlCodeStyleSettings(String dialectCode, CodeStyleSettings container) {
    super(dialectCode + "CodeStyleSettings", container);
  }

  @Override
  public SqlCodeStyleSettings clone() {
    SqlCodeStyleSettings clone = (SqlCodeStyleSettings)super.clone();
    clone.myVersion = this.myVersion;
    clone.myInitialized = this.myInitialized;
    return clone;
  }

  /**
   * The dialect these settings are related to.
   * Inheritors must override this method and return the correct language dialect.
   * @return the dialect.
   */
  @NotNull
  public Language getCorrespondedDialect() {
    return SqlLanguage.INSTANCE;       
  }

  /**
   * The corresponded common settings.
   * @return the common settings.
   */
  @NotNull
  public CommonCodeStyleSettings getCorrespondedCommonSettings() {
    CodeStyleSettings container = getContainer();
    assert container != null : "The container of SQL custom settings must not be null";
    Language correspondedDialect = getCorrespondedDialect();
    return container.getCommonSettings(correspondedDialect);
  }
  
  /**
   * The corresponded indent options.
   * @return the indent options.
   */
  @NotNull
  public IndentOptions getCorrespondedIndentOptions() {
    CodeStyleSettings container = getContainer();
    assert container != null : "The container of SQL custom settings must not be null";
    Language correspondedDialect = getCorrespondedDialect();
    IndentOptions indentOptions = container.getLanguageIndentOptions(correspondedDialect);
    assert indentOptions != null : "The container should contain indent options for class " + this.getClass().getSimpleName() + " and language dialect " + correspondedDialect;
    return indentOptions;
  }

  /**
   * Settings that are not applicable to this dialect.
   * @return names of the non-applicable settings.
   */
  @NotNull
  public Set<String> getSettingsToHide() {
    return SETTINGS_TO_HIDE;
  } 
  
  public boolean isSettingHidden(@NotNull String settingName) {
    return getSettingsToHide().contains(settingName);
  }
  
  public boolean isInitialized() {
    return myInitialized;
  }

  
  /// GLOBAL OPTIONS \\\

  public boolean USE_GENERIC_STYLE = false;

  public boolean DISABLE_FORMATTING = false;


  /// CASING \\\

  @AliasCase
  public int ALIAS_CASE = MODERN.ALIAS_CASE;

  @IdentifierCase
  public int KEYWORD_CASE = MODERN.KEYWORD_CASE;

  @IdentifierCaseExt
  public int TYPE_CASE = MODERN.TYPE_CASE;

  @IdentifierCase
  public int IDENTIFIER_CASE = MODERN.IDENTIFIER_CASE;
  
  @IdentifierCase
  public int QUOTED_IDENTIFIER_CASE = MODERN.QUOTED_IDENTIFIER_CASE;

  @NotNull
  public static Case getCaseMode(@IdentifierCase int codeStyleSetting) {
    return codeStyleSetting == TO_UPPER ? Case.UPPER :
           codeStyleSetting == TO_LOWER ? Case.LOWER :
           codeStyleSetting == TO_TITLE ? Case.TITLE :
           Case.MIXED;
  }

  @NotNull
  public Case getCaseModeExt(@IdentifierCaseExt int codeStyleSetting) {
    if (codeStyleSetting == AS_KEYWORDS) return getCaseModeExt(KEYWORD_CASE);
    return getCaseMode(codeStyleSetting);
  }


  /// IDENTIFIER QUOTATION \\\

  @MagicConstant(intValues = {QUOTE, UNQUOTE, DO_NOT_CHANGE})
  public int QUOTE_IDENTIFIER = MODERN.QUOTE_IDENTIFIER;

  @MagicConstant(intValues = {QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_ONLY, QUOTE_BRACKET_AUTO, QUOTE_BRACKET_ONLY, QUOTE_GRAVE_ACCENT_AUTO, QUOTE_GRAVE_ACCENT_ONLY})
  public int QUOTE_TYPE = MODERN.QUOTE_TYPE;

  public boolean quotationSupportBrackets() { return false; }
  public boolean quotationSupportGraveAccent() { return false; }

  @Nullable
  public String getQuotesPriority() {
    switch (QUOTE_TYPE) {
      case QUOTE_AUTO: return null;
      case QUOTE_DOUBLE_QUOTE_ONLY: return "\"";
      case QUOTE_BRACKET_AUTO: return "[\"`";
      case QUOTE_BRACKET_ONLY: return "[";
      case QUOTE_GRAVE_ACCENT_AUTO: return "`\"[";
      case QUOTE_GRAVE_ACCENT_ONLY: return "`";
    }
    return null;
  }



  /// QUERY & DML COMMON  \\

  @Property(externalName = "query_sections_align")
  @MagicConstant(intValues = {AS_IS, QUERY_SECTION_1ST_WORD_ALIGN_LEFT, QUERY_SECTION_1ST_WORD_ALIGN_LEFT_INDENT, QUERY_SECTION_1ST_WORD_ALIGN_RIGHT})
  public int QUERY_SECTION_1ST_WORD_ALIGN = MODERN.QUERY_SECTION_1ST_WORD_ALIGN;

  @Property(externalName = "query_elements_place")
  @ElementsLineMagicValues
  public int QUERY_EL_LINE = MODERN.QUERY_EL_LINE;

  @Property(externalName = "query_elements_comma")
  @CommaMagicValues
  public int QUERY_EL_COMMA = MODERN.QUERY_EL_COMMA;

  @Property(externalName = "query_collapse_to_one_string") // better name?
  @MagicConstant(intValues = {AS_IS, QUERY_IN_ONE_STRING_NO, QUERY_IN_ONE_STRING_INNER_ONLY, QUERY_IN_ONE_STRING_YES})
  public int QUERY_IN_ONE_STRING = MODERN.QUERY_IN_ONE_STRING;

  @Property(externalName = "query_elements_true_indent") // better name?
  public boolean QUERY_TRUE_INDENT = MODERN.QUERY_TRUE_INDENT;

  @Property(externalName = "query_elements_align")
  public boolean QUERY_ALIGN_ELEMENTS = MODERN.QUERY_ALIGN_ELEMENTS;

  @Property(externalName = "query_line_comments_align")
  public boolean QUERY_ALIGN_LINE_COMMENTS = MODERN.QUERY_ALIGN_LINE_COMMENTS;


  /// SUBQUERY \\\

  @Property(externalName = "subquery_opening_place")
  @OpeningMagicValues
  public int SUBQUERY_OPENING = MODERN.SUBQUERY_OPENING;

  @Property(externalName = "subquery_content_place")
  @ContentMagicValues
  public int SUBQUERY_CONTENT = MODERN.SUBQUERY_CONTENT;

  @Property(externalName = "subquery_closing_place")
  @ClosingMagicValues
  public int SUBQUERY_CLOSING = MODERN.SUBQUERY_CLOSING;

  @Property(externalName = "subquery_space_within_parentheses")
  public boolean SUBQUERY_PAR_SPACE_INSIDE = MODERN.SUBQUERY_PAR_SPACE_INSIDE;


  /// SECTIONS INSERT AND VALUES \\\

  @Property(externalName = "insert_into_on_next_line")
  @AddRemoveAsIsMagicValues
  public int INSERT_INTO_NL = MODERN.INSERT_INTO_NL;

  @Property(externalName = "insert_opening_place")
  @OpeningMagicValues
  public int INSERT_OPENING = MODERN.INSERT_OPENING;

  @Property(externalName = "insert_content_place")
  @ContentMagicValues
  public int INSERT_CONTENT = MODERN.INSERT_CONTENT;

  @Property(externalName = "insert_closing_place")
  @ClosingMagicValues
  public int INSERT_CLOSING = MODERN.INSERT_CLOSING;

  @Property(externalName = "insert_table_columns_place")
  @ElementsLineMagicValues
  public int INSERT_TABLE_EL_LINE = MODERN.INSERT_TABLE_EL_LINE;

  @Property(externalName = "insert_values_place")
  @ElementsLineMagicValues
  public int INSERT_VALUES_EL_LINE = MODERN.INSERT_VALUES_EL_LINE;

  @Property(externalName = "insert_values_wrap")
  @WrapMagicValues
  public int INSERT_EL_WRAP = MODERN.INSERT_EL_WRAP;

  @Property(externalName = "insert_values_comma")
  @CommaMagicValues
  public int INSERT_EL_COMMA = MODERN.INSERT_EL_COMMA;

  @Property(externalName = "insert_space_within_parentheses")
  public boolean INSERT_SPACE_WITHIN_PARENTHESES = MODERN.INSERT_SPACE_WITHIN_PARENTHESES;

  @Property(externalName = "insert_collapse_multi_rows")
  public boolean INSERT_COLLAPSE_MULTI_ROW_VALUES = MODERN.INSERT_COLLAPSE_MULTI_ROW_VALUES;


  /// SECTION SET \\\

  @Property(externalName = "set_elements_place")
  @ElementsLineMagicValues
  public int SET_EL_LINE = MODERN.SET_EL_LINE;

  @Property(externalName = "set_elements_wrap")
  @WrapMagicValues
  public int SET_EL_WRAP = MODERN.SET_EL_WRAP;

  @Property(externalName = "set_elements_comma")
  @ContentMagicValues
  public int SET_EL_COMMA = MODERN.SET_EL_COMMA;

  @Property(externalName = "set_equal_sign_align")
  public boolean SET_ALIGN_EQUAL_SIGN = MODERN.SET_ALIGN_EQUAL_SIGN;


  /// SECTION WITH \\\

  @Property(externalName = "with_elements_place")
  @ElementsLineMagicValues
  public int WITH_EL_LINE = MODERN.SET_EL_LINE;

  @Property(externalName = "with_elements_wrap")
  @Wrap2MagicValues
  public int WITH_EL_WRAP = MODERN.WITH_EL_WRAP;

  @Property(externalName = "with_elements_comma")
  @CommaMagicValues
  public int WITH_EL_COMMA = MODERN.WITH_EL_COMMA;

  @Property(externalName = "with_as_align")
  public boolean WITH_ALIGN_AS = MODERN.WITH_ALIGN_AS;


  /// SECTION SELECT \\\

  @Property(externalName = "select_elements_place")
  @ElementsLineMagicValues
  public int SELECT_EL_LINE = MODERN.SET_EL_LINE;

  @Property(externalName = "select_elements_wrap")
  @WrapMagicValues
  public int SELECT_EL_WRAP = MODERN.SELECT_EL_WRAP;

  @Property(externalName = "select_elements_comma")
  @CommaMagicValues
  public int SELECT_EL_COMMA = MODERN.SELECT_EL_COMMA;

  @Property(externalName = "select_break_after_distinct")  // better name?
  public boolean SELECT_NEW_LINE_AFTER_ALL_DISTINCT = MODERN.SELECT_NEW_LINE_AFTER_ALL_DISTINCT;

  @Property(externalName = "select_collapse_when")
  public int SELECT_KEEP_N_ITEMS_IN_LINE = MODERN.SELECT_KEEP_N_ITEMS_IN_LINE; // change to 0

  @Property(externalName = "select_as_use")
  @MagicConstant(intValues = {AS_IS, ADD, REMOVE})
  public int SELECT_USE_AS_WORD = MODERN.SELECT_USE_AS_WORD;

  @Property(externalName = "select_as_align")
  public boolean SELECT_ALIGN_AS = MODERN.SELECT_ALIGN_AS;


  /// SECTION FROM \\\

  @Property(externalName = "from_elements_place")
  @ElementsLineMagicValues
  public int FROM_EL_LINE = MODERN.SET_EL_LINE;

  @Property(externalName = "from_elements_wrap")
  @WrapMagicValues
  public int FROM_EL_WRAP = MODERN.FROM_EL_WRAP;

  @Property(externalName = "from_elements_comma")
  @CommaMagicValues
  public int FROM_EL_COMMA = MODERN.FROM_EL_COMMA;

  @Property(externalName = "from_first_join_wrap")
  public boolean FROM_WRAP_JOIN_1 = MODERN.FROM_WRAP_JOIN_1;

  @Property(externalName = "from_next_join_wrap")
  public boolean FROM_WRAP_JOIN_2 = MODERN.FROM_WRAP_JOIN_2;

  @Property(externalName = "from_on_wrap")
  public boolean FROM_WRAP_ON = MODERN.FROM_WRAP_ON;

  @Property(externalName = "from_join_align")
  public boolean FROM_ALIGN_JOIN_TABLES = MODERN.FROM_ALIGN_JOIN_TABLES;

  @Property(externalName = "from_aliases_align")
  public boolean FROM_ALIGN_ALIASES = MODERN.FROM_ALIGN_ALIASES;

  @Property(externalName = "from_join_indent")
  public boolean FROM_INDENT_JOIN = MODERN.FROM_INDENT_JOIN;

  @Property(externalName = "from_only_join_indent")
  @FromOnlyJoinIndentValues
  public int FROM_ONLY_JOIN_INDENT = MODERN.FROM_ONLY_JOIN_INDENT;

  @Property(externalName = "from_on_place")
  @FromPlaceOnValues
  public int FROM_PLACE_ON = MODERN.FROM_PLACE_ON;


  /// SECTION WHERE \\\

  @Property(externalName = "where_elements_place")
  @ElementsLineMagicValues
  public int WHERE_EL_LINE = MODERN.SET_EL_LINE;

  @Property(externalName = "where_elements_wrap")
  @WrapMagicValues
  public int WHERE_EL_WRAP = MODERN.WHERE_EL_WRAP;

  @Property(externalName = "where_elements_comma")
  @CommaMagicValues
  public int WHERE_EL_BOUND = MODERN.WHERE_EL_BOUND;


  /// SECTIONS GROUP/ORDER \\\

  @Property(externalName = "order_elements_place")
  @ElementsLineMagicValues
  public int ORDER_EL_LINE = MODERN.SET_EL_LINE;

  @Property(externalName = "order_elements_wrap")
  @WrapMagicValues
  public int ORDER_EL_WRAP = MODERN.ORDER_EL_WRAP;

  @Property(externalName = "order_elements_comma")
  @CommaMagicValues
  public int ORDER_EL_COMMA = MODERN.ORDER_EL_COMMA;

  @Property(externalName = "order_direction_align")
  public boolean ORDER_ALIGN_ASC_DESC = MODERN.ORDER_ALIGN_ASC_DESC;


  /// TABLE \\\

  @Property(externalName = "table_elements_place")
  @OpeningMagicValues
  public int TABLE_OPENING = MODERN.TABLE_OPENING;

  @Property(externalName = "table_elements_wrap")
  @ContentMagicValues
  public int TABLE_CONTENT = MODERN.TABLE_CONTENT;

  @Property(externalName = "table_elements_comma")
  @ClosingMagicValues
  public int TABLE_CLOSING = MODERN.TABLE_CLOSING;

  @Property(externalName = "table_types_align")
  public boolean TABLE_TYPES_ALIGN = MODERN.TABLE_TYPES_ALIGN;

  @Property(externalName = "table_defaults_align")
  public boolean TABLE_DEFAULTS_ALIGN = MODERN.TABLE_DEFAULTS_ALIGN;

  @Property(externalName = "table_nullabilities_align")
  public boolean TABLE_NULLABILITIES_ALIGN = MODERN.TABLE_NULLABILITIES_ALIGN;

  @Property(externalName = "table_collapse")
  public boolean TABLE_COLLAPSE = MODERN.TABLE_COLLAPSE;

  @Property(externalName = "table_alter_wrap")
  @AddRemoveAsIsMagicValues
  public int TABLE_ALTER_INSTRUCTION_WRAP = MODERN.TABLE_ALTER_INSTRUCTION_WRAP;

  @Property(externalName = "table_alter_align")
  public boolean TABLE_ALTER_INSTRUCTION_ALIGN = MODERN.TABLE_ALTER_INSTRUCTION_ALIGN;


  /// CONSTRAINT \\\

  @Property(externalName = "constraint_level_1_wrap")
  public boolean CONSTRAINT_WRAP_1 = MODERN.CONSTRAINT_WRAP_1;

  @Property(externalName = "constraint_level_2_wrap")
  public boolean CONSTRAINT_WRAP_2 = MODERN.CONSTRAINT_WRAP_2;

  @Property(externalName = "constraint_level_3_wrap")
  public boolean CONSTRAINT_WRAP_3 = MODERN.CONSTRAINT_WRAP_3;

  @Property(externalName = "constraint_level_4_wrap")
  public boolean CONSTRAINT_WRAP_4 = MODERN.CONSTRAINT_WRAP_4;


  /// POSTFIX CLAUSES \\\

  @Property(externalName = "postfix_first_option_wrap")
  public boolean POST_OPT_WRAP_1 = MODERN.POST_OPT_WRAP_1;

  @Property(externalName = "postfix_next_option_wrap")
  public boolean POST_OPT_WRAP_2 = MODERN.POST_OPT_WRAP_2;

  @Property(externalName = "postfix_option_indent")
  public boolean POST_OPT_INDENT = MODERN.POST_OPT_INDENT;

  @Property(externalName = "postfix_option_align")
  public boolean POST_OPT_ALIGN = MODERN.POST_OPT_ALIGN;


  /// CREATE SCHEMA \\\

  @Property(externalName = "schema_content_indent")
  public boolean CREATE_SCHEMA_CONTENT_INDENT = MODERN.CREATE_SCHEMA_CONTENT_INDENT;

  @Property(externalName = "schema_blank_lines_min")
  public int CREATE_SCHEMA_BLANK_LINES_MIN = MODERN.CREATE_SCHEMA_BLANK_LINES_MIN;

  @Property(externalName = "schema_blank_lines_max")
  public int CREATE_SCHEMA_BLANK_LINES_MAX = MODERN.CREATE_SCHEMA_BLANK_LINES_MAX;


  /// VIEW \\\

  @Property(externalName = "view_as_wrap")
  public boolean VIEW_WRAP_AS = MODERN.VIEW_WRAP_AS;

  @Property(externalName = "view_query_wrap")
  public boolean VIEW_WRAP_QUERY = MODERN.VIEW_WRAP_QUERY;

  @Property(externalName = "view_query_indent")
  public boolean VIEW_INDENT_QUERY = MODERN.VIEW_INDENT_QUERY;


  /// ROUTINE \\\

  @Property(externalName = "routine_arg_opening_place")
  @OpeningMagicValues
  public int ROUTINE_ARG_OPENING = MODERN.ROUTINE_ARG_OPENING;

  @Property(externalName = "routine_arg_content_place")
  @ContentMagicValues
  public int ROUTINE_ARG_CONTENT = MODERN.ROUTINE_ARG_CONTENT;

  @Property(externalName = "routine_arg_closing_place")
  @ClosingMagicValues
  public int ROUTINE_ARG_CLOSING = MODERN.ROUTINE_ARG_CLOSING;

  @Property(externalName = "routine_arg_wrap")
  @WrapMagicValues
  public int ROUTINE_ARG_WRAP = MODERN.ROUTINE_ARG_WRAP;

  @Property(externalName = "routine_arg_comma")
  @CommaMagicValues
  public int ROUTINE_ARG_COMMA = MODERN.ROUTINE_ARG_COMMA;

  @Property(externalName = "routine_arg_space_within_parentheses")
  public boolean ROUTINE_ARG_SPACE_WITHIN_PARENTHESES = MODERN.ROUTINE_ARG_SPACE_WITHIN_PARENTHESES;

  @Property(externalName = "routine_arg_types_align")
  public boolean ROUTINE_ARG_ALIGN_TYPES = MODERN.ROUTINE_ARG_ALIGN_TYPES;

  @Property(externalName = "routine_as_wrap")
  public boolean ROUTINE_AS_WRAP = MODERN.ROUTINE_AS_WRAP;


  /// POSTGRES ROUTINES \\\

  //@Property(externalName = "routine_")
  public boolean ROUTINE_PG_L_QUOTE_WRAP_BEFORE = MODERN.ROUTINE_PG_L_QUOTE_WRAP_BEFORE;

  //@Property(externalName = "routine_")
  public boolean ROUTINE_PG_L_QUOTE_WRAP_AFTER = MODERN.ROUTINE_PG_L_QUOTE_WRAP_AFTER;

  //@Property(externalName = "routine_")
  public boolean ROUTINE_PG_R_QUOTE_WRAP_BEFORE = MODERN.ROUTINE_PG_R_QUOTE_WRAP_BEFORE;

  //@Property(externalName = "routine_")
  public boolean ROUTINE_PG_R_QUOTE_WRAP_AFTER = MODERN.ROUTINE_PG_R_QUOTE_WRAP_AFTER;


  /// IMPERATIVE CODE \\\

  @Property(externalName = "imp_statements_force_wrap")
  public boolean IMP_COMMON_WRAP_EVERY_STATEMENT = MODERN.IMP_COMMON_WRAP_EVERY_STATEMENT;

  @Property(externalName = "imp_statements_keep_blank_lines")
  public int IMP_COMMON_KEEP_BLANK_LINES_IN_CODE = MODERN.IMP_COMMON_KEEP_BLANK_LINES_IN_CODE;

  @Property(externalName = "imp_declare_content_wrap")
  public boolean IMP_DECLARE_CONTENT_WRAP = MODERN.IMP_DECLARE_CONTENT_WRAP;

  @Property(externalName = "imp_declare_elements_wrap")
  @WrapMagicValues
  public int IMP_DECLARE_EL_WRAP = MODERN.IMP_DECLARE_EL_WRAP;

  @Property(externalName = "imp_declare_types_align")
  public boolean IMP_DECLARE_ALIGN_TYPE = MODERN.IMP_DECLARE_ALIGN_TYPE;

  @Property(externalName = "imp_declare_assignments_align")
  public boolean IMP_DECLARE_ALIGN_EQ = MODERN.IMP_DECLARE_ALIGN_EQ;

  @Property(externalName = "imp_declare_defaults_align")
  public boolean IMP_DECLARE_ALIGN_DEFAULT = MODERN.IMP_DECLARE_ALIGN_DEFAULT;

  @Property(externalName = "imp_fork_then_wrap")
  public boolean IMP_IF_THEN_WRAP_THEN = MODERN.IMP_IF_THEN_WRAP_THEN;

  @Property(externalName = "imp_fork_else_wrap")
  public boolean IMP_IF_THEN_WRAP_ELSE = MODERN.IMP_IF_THEN_WRAP_ELSE;

  @Property(externalName = "imp_fork_content_wrap")
  public boolean IMP_IF_THEN_WRAP_INNER = MODERN.IMP_IF_THEN_WRAP_INNER;

  @Property(externalName = "imp_fork_branch_indent")
  public boolean IMP_IF_THEN_INDENT_THEN_ELSE = MODERN.IMP_IF_THEN_INDENT_THEN_ELSE;

  @Property(externalName = "imp_fork_end_indent")
  public boolean IMP_IF_THEN_INDENT_END = MODERN.IMP_IF_THEN_INDENT_END;

  @Property(externalName = "imp_fork_collapse")
  public boolean IMP_IF_THEN_COLLAPSE = MODERN.IMP_IF_THEN_COLLAPSE;

  @Property(externalName = "imp_loop_keyword_wrap")
  public boolean IMP_LOOP_LOOP_WRAP = MODERN.IMP_LOOP_LOOP_WRAP;

  @Property(externalName = "imp_loop_keyword_indent")
  public boolean IMP_LOOP_LOOP_INDENT = MODERN.IMP_LOOP_LOOP_INDENT;

  @Property(externalName = "imp_loop_end_indent")
  public boolean IMP_LOOP_END_INDENT = MODERN.IMP_LOOP_END_INDENT;

  @Property(externalName = "imp_loop_collapse")
  public boolean IMP_LOOP_COLLAPSE = MODERN.IMP_LOOP_COLLAPSE;


  /// CORTEGES \\\

  @Property(externalName = "cortege_opening_space_before") // better name?
  public boolean CORTEGE_SPACE_BEFORE_L_PAREN = MODERN.CORTEGE_SPACE_BEFORE_L_PAREN; // change to true

  @Property(externalName = "cortege_comma1st")
  public boolean CORTEGE_COMMA_1ST = MODERN.CORTEGE_COMMA_1ST;

  @Property(externalName = "cortege_closing_place")
  @MagicConstant(intValues = {AS_IS, CORTEGE_CLOSING_UNDER_OPENING, CORTEGE_CLOSING_UNDER_ITEM, CORTEGE_CLOSING_AT_THE_END})
  public int CORTEGE_CLOSING = MODERN.CORTEGE_CLOSING;

  @Property(externalName = "cortege_space_within_parentheses")
  public boolean CORTEGE_SPACE_WITHIN_PARENTHESES = MODERN.CORTEGE_SPACE_WITHIN_PARENTHESES;

  @Property(externalName = "cortege_comma_space_before")
  public boolean CORTEGE_SPACE_BEFORE_COMMA = MODERN.CORTEGE_SPACE_BEFORE_COMMA;

  @Property(externalName = "cortege_comma_space_after")
  public boolean CORTEGE_SPACE_AFTER_COMMA = MODERN.CORTEGE_SPACE_AFTER_COMMA;

  
  /// EXPRESSIONS \\\

  @Property(externalName = "expr_space_around_operator")
  @MagicConstant(intValues = {AS_IS, ADD, REMOVE})
  public int EXPR_SPACE_AROUND_OPERATOR = MODERN.EXPR_SPACE_AROUND_OPERATOR;

  @Property(externalName = "expr_space_within_parentheses")
  public boolean EXPR_SPACE_WITHIN_PARENTHESES = MODERN.EXPR_SPACE_WITHIN_PARENTHESES;

  @Property(externalName = "expr_operator_align")
  public boolean EXPR_BINARY_OP_ALIGN = MODERN.EXPR_BINARY_OP_ALIGN;

  @Property(externalName = "expr_call_space_within_parentheses")
  public boolean EXPR_CALL_SPACE_INSIDE_PARENTHESES = MODERN.EXPR_CALL_SPACE_INSIDE_PARENTHESES;

  @Property(externalName = "expr_call_comma_space_before")
  public boolean EXPR_CALL_SPACE_BEFORE_COMMA = MODERN.EXPR_CALL_SPACE_BEFORE_COMMA;

  @Property(externalName = "expr_call_comma_space_after")
  public boolean EXPR_CALL_SPACE_AFTER_COMMA = MODERN.EXPR_CALL_SPACE_AFTER_COMMA;

  @Property(externalName = "expr_case_when_wrap")
  public boolean EXPR_CASE_WHEN_WRAP = MODERN.EXPR_CASE_WHEN_WRAP;

  @Property(externalName = "expr_case_when_indent")
  public boolean EXPR_CASE_WHEN_INDENT = MODERN.EXPR_CASE_WHEN_INDENT;

  @Property(externalName = "expr_case_then_wrap")
  public boolean EXPR_CASE_THEN_WRAP = MODERN.EXPR_CASE_THEN_WRAP; // change to false

  @Property(externalName = "expr_case_then_align")
  public boolean EXPR_CASE_THEN_ALIGN = MODERN.EXPR_CASE_THEN_ALIGN;

  @Property(externalName = "expr_case_else_under_then")
  public boolean EXPR_CASE_ELSE_ALIGN_THEN = MODERN.EXPR_CASE_ELSE_ALIGN_THEN;

  @Property(externalName = "expr_case_end_place")
  @MagicConstant(intValues = {AS_IS, EXPR_CASE_END_ALIGN_CASE, EXPR_CASE_END_ALIGN_WHEN, EXPR_CASE_END_TO_THE_END})
  public int EXPR_CASE_END = MODERN.EXPR_CASE_END;

  @Property(externalName = "expr_case_then_keep_following_linebreak")
  public boolean EXPR_CASE_KEEP_NL_AFTER_THEN = MODERN.EXPR_CASE_KEEP_NL_AFTER_THEN;

  @Property(externalName = "expr_case_collapse")
  public boolean EXPR_CASE_COLLAPSE = MODERN.EXPR_CASE_COLLAPSE;


  /// OTHER \\\

  public String INDEX_NAME_TEMPLATE       = MODERN.INDEX_NAME_TEMPLATE;
  public String PRIMARY_KEY_NAME_TEMPLATE = MODERN.PRIMARY_KEY_NAME_TEMPLATE;
  public String FOREIGN_KEY_NAME_TEMPLATE = MODERN.FOREIGN_KEY_NAME_TEMPLATE;


  /// DEPRECATED \\\

  @Deprecated public boolean SUBQUERY_L_PAR_NL_OUTSIDE                = LEGACY.SUBQUERY_L_PAR_NL_OUTSIDE;
  @Deprecated public boolean SUBQUERY_L_PAR_NL_INSIDE                 = LEGACY.SUBQUERY_L_PAR_NL_INSIDE;
  @Deprecated public boolean SUBQUERY_R_PAR_NL_INSIDE                 = LEGACY.SUBQUERY_R_PAR_NL_INSIDE;

  @Deprecated public int SUBQUERY_R_PAR_ALIGN                         = LEGACY.SUBQUERY_R_PAR_ALIGN;

  @Deprecated public boolean SUBQUERY_INDENT_INSIDE                   = LEGACY.SUBQUERY_INDENT_INSIDE;

  @Deprecated public boolean SPACES_AROUND_OPERATORS                  = LEGACY.SPACES_AROUND_OPERATORS;

  @Deprecated public boolean ALIGN_AS_IN_SELECT_STATEMENT             = LEGACY.ALIGN_AS_IN_SELECT_STATEMENT;
  @Deprecated public boolean ALIGN_TYPE_IN_CREATE_STATEMENT           = LEGACY.ALIGN_TYPE_IN_CREATE_STATEMENT;
  @Deprecated public boolean ALIGN_TYPE_IN_BLOCK_STATEMENT            = LEGACY.ALIGN_TYPE_IN_BLOCK_STATEMENT;
  @Deprecated public boolean ALIGN_TYPE_IN_ARGUMENT_DEFINITION        = LEGACY.ALIGN_TYPE_IN_ARGUMENT_DEFINITION;
  @Deprecated public boolean ALIGN_INSIDE_BINARY_EXPRESSION           = LEGACY.ALIGN_INSIDE_BINARY_EXPRESSION;
  @Deprecated public boolean ALIGN_INSIDE_QUERY_EXPRESSION            = LEGACY.ALIGN_INSIDE_QUERY_EXPRESSION;
  @Deprecated public boolean ALIGN_EQ_INSIDE_SET_CLAUSE               = LEGACY.ALIGN_EQ_INSIDE_SET_CLAUSE;

  @Deprecated public boolean NEW_LINE_BEFORE_FROM                     = LEGACY.NEW_LINE_BEFORE_FROM;
  @Deprecated public boolean NEW_LINE_BEFORE_JOIN                     = LEGACY.NEW_LINE_BEFORE_JOIN;
  @Deprecated public boolean NEW_LINE_BEFORE_JOIN_CONDITION           = LEGACY.NEW_LINE_BEFORE_JOIN_CONDITION;
  @Deprecated public boolean NEW_LINE_BEFORE_WHERE                    = LEGACY.NEW_LINE_BEFORE_WHERE;
  @Deprecated public boolean NEW_LINE_BEFORE_GROUP_BY                 = LEGACY.NEW_LINE_BEFORE_GROUP_BY;
  @Deprecated public boolean NEW_LINE_BEFORE_ORDER_BY                 = LEGACY.NEW_LINE_BEFORE_ORDER_BY;
  @Deprecated public boolean NEW_LINE_BEFORE_HAVING                   = LEGACY.NEW_LINE_BEFORE_HAVING;
  @Deprecated public boolean NEW_LINE_BEFORE_THEN                     = LEGACY.NEW_LINE_BEFORE_THEN;
  @Deprecated public boolean NEW_LINE_BEFORE_ELSE                     = LEGACY.NEW_LINE_BEFORE_ELSE;
  @Deprecated public boolean NEW_LINE_BEFORE_OTHER_CLAUSES            = LEGACY.NEW_LINE_BEFORE_OTHER_CLAUSES;
  @Deprecated public boolean NEW_LINE_BEFORE_COMMA                    = LEGACY.NEW_LINE_BEFORE_COMMA;
  @Deprecated public boolean NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS = LEGACY.NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS;
  @Deprecated public boolean NEW_LINE_BEFORE_QUERY_INSIDE_DML         = LEGACY.NEW_LINE_BEFORE_QUERY_INSIDE_DML;
  @Deprecated public boolean NEW_LINE_AROUND_SEMICOLON                = LEGACY.NEW_LINE_AROUND_SEMICOLON;

  @Deprecated public boolean INDENT_JOIN                              = LEGACY.INDENT_JOIN;
  @Deprecated public boolean INDENT_JOIN_CONDITION                    = LEGACY.INDENT_JOIN_CONDITION;
  @Deprecated public boolean INDENT_SELECT_INTO_CLAUSE                = LEGACY.INDENT_SELECT_INTO_CLAUSE;

  @Deprecated public int WRAP_INSIDE_CREATE_TABLE                     = LEGACY.WRAP_INSIDE_CREATE_TABLE;
  @Deprecated public int WRAP_INSIDE_SELECT                           = LEGACY.WRAP_INSIDE_SELECT;
  @Deprecated public int WRAP_INSIDE_JOIN_EXPRESSION                  = LEGACY.WRAP_INSIDE_JOIN_EXPRESSION;
  @Deprecated public int WRAP_INSIDE_GROUP_BY                         = LEGACY.WRAP_INSIDE_GROUP_BY;
  @Deprecated public int WRAP_INSIDE_WHERE                            = LEGACY.WRAP_INSIDE_WHERE;
  @Deprecated public int WRAP_INSIDE_ORDER_BY                         = LEGACY.WRAP_INSIDE_ORDER_BY;
  @Deprecated public int WRAP_INSIDE_SET                              = LEGACY.WRAP_INSIDE_SET;
  @Deprecated public int WRAP_INSIDE_ARGUMENT_DEFINITION              = LEGACY.WRAP_INSIDE_ARGUMENT_DEFINITION;
  @Deprecated public int WRAP_INSIDE_CALL_EXPRESSION                  = LEGACY.WRAP_INSIDE_CALL_EXPRESSION;
  @Deprecated public int WRAP_INSIDE_VALUES_EXPRESSION                = LEGACY.WRAP_INSIDE_VALUES_EXPRESSION;
  @Deprecated public int WRAP_VALUES_EXPRESSION                       = LEGACY.WRAP_VALUES_EXPRESSION;
  @Deprecated public int WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES  = LEGACY.WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES;

  @Deprecated public boolean NEW_LINE_AFTER_SELECT                    = LEGACY.NEW_LINE_AFTER_SELECT;
  @Deprecated public boolean NEW_LINE_AFTER_SELECT_ITEM               = LEGACY.NEW_LINE_AFTER_SELECT_ITEM;
  @Deprecated public int NEW_LINE_AFTER_SELECT_2                      = LEGACY.NEW_LINE_AFTER_SELECT_2;


  /// MIGRATION \\\

  private static final Logger LOG = Logger.getInstance(SqlCodeStyleSettings.class);


  @Override
  public void readExternal(Element parentElement) throws InvalidDataException {
    readFromTag(parentElement, getTagName());
  }

  protected void readFromTag(Element parentElement, @NotNull String tagName) {
    Element sectionElement = parentElement.getChild(tagName);
    if (sectionElement == null) return;

    myVersion = readIntAttribute(sectionElement, "version");
    if (myVersion == 0) assignDefaults(LEGACY);

    super.readExternal(parentElement);
    myInitialized = true;
  }

  @Override
  public void writeExternal(Element parentElement, @NotNull CustomCodeStyleSettings parentSettings) throws WriteExternalException {
    super.writeExternal(parentElement, parentSettings);

    Element sectionElement = parentElement.getChild(getTagName());
    if (sectionElement != null) {
      sectionElement.setAttribute("version", Integer.toString(myVersion));
    }
  }

  private static int readIntAttribute(@Nullable Element element, @NotNull String attributeName) {
    String value = readStringAttribute(element, attributeName);
    if (value == null) return 0;
    value = value.trim();
    try {
      return Integer.parseInt(value);
    }
    catch (NumberFormatException nfe) {
      LOG.warn("Reading SQL code style settings: Wrong integer attribute value: "+'"'+value+'"');
      return 0;
    }
  }

  @Nullable
  private static String readStringAttribute(@Nullable Element element, @NotNull String attributeName) {
    if (element == null) return null;
    return element.getAttributeValue(attributeName);
  }


  @Override
  public void beforeLoading() {
    //myVersion = 0;
  }

  /**
   * Migrations are incremental,
   * so we need to apply them one-by-one.
   */
  @Override
  public void afterLoaded() {
    if (myInitialized) {
      if (myVersion <= 0) migrateFromVersion0();
      if (myVersion <= 1) migrateFromVersion1();
    }
    else {
      initialize();
    }
  }

  
  private void initialize() {
    CodeStyleSettings container = this.getContainer();
    if (container == null) return;
    if (this.getClass() != SqlCodeStyleSettings.class) {
      SqlCodeStyleSettings genericSettings = container.getCustomSettings(SqlCodeStyleSettings.class);
      SqlCodeStyles.copySettings(genericSettings, this);
    }
    myInitialized = true;
  }


  private void migrateFromVersion0() {
    LOG.debug("Migrating SQL code style settings from version 0");

    CommonCodeStyleSettings c = getCorrespondedCommonSettings();

    int x = countValue(WRAP_ALWAYS,
                       WRAP_INSIDE_SET, WRAP_INSIDE_SELECT, WRAP_INSIDE_WHERE, WRAP_INSIDE_GROUP_BY, WRAP_INSIDE_ORDER_BY);
    if (x <= 2) {
      QUERY_EL_LINE = EL_SAME;
    }
    else if (x >= 4) {
      QUERY_EL_LINE = EL_INDENT;
    }
    else {
      QUERY_EL_LINE = AS_IS;
    }

    SUBQUERY_PAR_SPACE_INSIDE = c.SPACE_WITHIN_PARENTHESES;

    SET_ALIGN_EQUAL_SIGN = ALIGN_EQ_INSIDE_SET_CLAUSE;

    SELECT_EL_WRAP = convertWrap(WRAP_INSIDE_SELECT);
    SELECT_KEEP_N_ITEMS_IN_LINE = WRAP_INSIDE_SELECT == WRAP_ALWAYS ? 0 : 7;
    SELECT_ALIGN_AS = ALIGN_AS_IN_SELECT_STATEMENT;

    FROM_EL_WRAP = convertWrap(WRAP_INSIDE_JOIN_EXPRESSION);
    if (FROM_EL_WRAP == EL_WRAP) FROM_EL_WRAP = EL_CHOP_LONG;

    FROM_WRAP_JOIN_1 = NEW_LINE_BEFORE_JOIN;
    FROM_WRAP_JOIN_2 = NEW_LINE_BEFORE_JOIN;
    FROM_INDENT_JOIN = INDENT_JOIN;

    WHERE_EL_WRAP = convertWrap(WRAP_INSIDE_WHERE);

    ORDER_EL_WRAP = convertWrap(WRAP_INSIDE_ORDER_BY);

    SELECT_EL_COMMA = FROM_EL_COMMA = ORDER_EL_COMMA =
      NEW_LINE_BEFORE_COMMA ? EL_COMMA_1ST : EL_COMMA_LAST;

    ROUTINE_ARG_WRAP = convertWrap(WRAP_INSIDE_ARGUMENT_DEFINITION);
    ROUTINE_ARG_COMMA = NEW_LINE_BEFORE_COMMA ? EL_COMMA_1ST : EL_COMMA_LAST;
    ROUTINE_ARG_SPACE_WITHIN_PARENTHESES = c.SPACE_WITHIN_METHOD_PARENTHESES;
    ROUTINE_ARG_ALIGN_TYPES = ALIGN_TYPE_IN_ARGUMENT_DEFINITION;

    CORTEGE_SPACE_BEFORE_L_PAREN = c.SPACE_BEFORE_METHOD_PARENTHESES;
    CORTEGE_COMMA_1ST = NEW_LINE_BEFORE_COMMA;

    EXPR_SPACE_AROUND_OPERATOR = SPACES_AROUND_OPERATORS ? ADD : REMOVE;
    EXPR_BINARY_OP_ALIGN = ALIGN_INSIDE_BINARY_EXPRESSION;
    EXPR_CASE_THEN_WRAP = NEW_LINE_BEFORE_THEN;

    IMP_COMMON_KEEP_BLANK_LINES_IN_CODE = c.KEEP_BLANK_LINES_IN_CODE;
  }


  private void migrateFromVersion1() {
    LOG.debug("Migrating SQL code style settings from version 0");

    CommonCodeStyleSettings c = getCorrespondedCommonSettings();

    TABLE_OPENING = LEGACY.TABLE_OPENING;
    TABLE_CONTENT = LEGACY.TABLE_CONTENT;
    TABLE_CLOSING = LEGACY.TABLE_CLOSING;
    TABLE_ALTER_INSTRUCTION_WRAP = LEGACY.TABLE_ALTER_INSTRUCTION_WRAP;
    CONSTRAINT_WRAP_1 = LEGACY.CONSTRAINT_WRAP_1;
    CONSTRAINT_WRAP_2 = LEGACY.CONSTRAINT_WRAP_2;
    CONSTRAINT_WRAP_3 = LEGACY.CONSTRAINT_WRAP_3;

    POST_OPT_WRAP_1 = NEW_LINE_BEFORE_OTHER_CLAUSES;
    POST_OPT_WRAP_2 = NEW_LINE_BEFORE_OTHER_CLAUSES;

    TABLE_TYPES_ALIGN = ALIGN_TYPE_IN_CREATE_STATEMENT;
    TABLE_DEFAULTS_ALIGN = ALIGN_TYPE_IN_CREATE_STATEMENT;
    TABLE_NULLABILITIES_ALIGN = ALIGN_TYPE_IN_CREATE_STATEMENT;

    CORTEGE_SPACE_WITHIN_PARENTHESES = c.SPACE_WITHIN_PARENTHESES;
    CORTEGE_SPACE_BEFORE_COMMA = c.SPACE_BEFORE_COMMA;
    CORTEGE_SPACE_AFTER_COMMA = c.SPACE_AFTER_COMMA;

    IMP_DECLARE_EL_WRAP = ALIGN_TYPE_IN_BLOCK_STATEMENT ? EL_CHOP : EL_WRAP;
    IMP_DECLARE_ALIGN_TYPE = ALIGN_TYPE_IN_BLOCK_STATEMENT;

    IMP_IF_THEN_WRAP_THEN = NEW_LINE_BEFORE_THEN;
    IMP_IF_THEN_WRAP_ELSE = NEW_LINE_BEFORE_ELSE;
    IMP_IF_THEN_INDENT_THEN_ELSE = !NEW_LINE_BEFORE_THEN;
    IMP_IF_THEN_INDENT_END = !NEW_LINE_BEFORE_THEN;

    EXPR_SPACE_WITHIN_PARENTHESES = c.SPACE_WITHIN_PARENTHESES;
    EXPR_CALL_SPACE_INSIDE_PARENTHESES = c.SPACE_WITHIN_METHOD_CALL_PARENTHESES;
    EXPR_CALL_SPACE_BEFORE_COMMA = c.SPACE_BEFORE_COMMA;
    EXPR_CALL_SPACE_AFTER_COMMA = c.SPACE_AFTER_COMMA;
  }


  @SuppressWarnings("ForLoopReplaceableByForEach")
  private static int countValue(final int valueToCount, final int... values) {
    int m = 0;
    for (int i = 0, n = values.length; i < n; i++) if (values[i] == valueToCount) m++;
    return m;
  }


  @Contract(pure = true)
  @WrapMagicValues
  private static int convertWrap(int oldWrap) {
    switch (oldWrap) {
      case WRAP_ALWAYS: return EL_CHOP;
      case WRAP_ON_EVERY_ITEM: return EL_CHOP_LONG;
      case WRAP_AS_NEEDED: return EL_WRAP;
      default: return AS_IS;
    }
  }


  public void manuallyChanged() {
    if (myVersion < SqlCodeStyleSettingsDefault.CURRENT_VERSION) {
      LOG.info(format("SQL code style settings version updated from %d to %d (because they were manually changed)",
                      myVersion, SqlCodeStyleSettingsDefault.CURRENT_VERSION));
      myVersion = SqlCodeStyleSettingsDefault.CURRENT_VERSION;
    }
  }

  public void assignDefaults(@NotNull SqlCodeStyleSettingsDefault def) {
    assignModernDefaults(def);
    assignLegacyDefaults(def);
  }


  private void assignModernDefaults(@NotNull SqlCodeStyleSettingsDefault def) {
    // @formatter:off
    ALIAS_CASE                                    = def.ALIAS_CASE;
    KEYWORD_CASE                                  = def.KEYWORD_CASE;
    TYPE_CASE                                     = def.TYPE_CASE;
    IDENTIFIER_CASE                               = def.IDENTIFIER_CASE;
    QUOTED_IDENTIFIER_CASE                        = def.QUOTED_IDENTIFIER_CASE;
    QUOTE_IDENTIFIER                              = def.QUOTE_IDENTIFIER;
    QUOTE_TYPE                                    = def.QUOTE_TYPE;
    QUERY_SECTION_1ST_WORD_ALIGN                  = def.QUERY_SECTION_1ST_WORD_ALIGN;
    QUERY_EL_LINE                                 = def.QUERY_EL_LINE;
    QUERY_EL_COMMA                                = def.QUERY_EL_COMMA;
    QUERY_IN_ONE_STRING                           = def.QUERY_IN_ONE_STRING;
    QUERY_TRUE_INDENT                             = def.QUERY_TRUE_INDENT;
    QUERY_ALIGN_ELEMENTS                          = def.QUERY_ALIGN_ELEMENTS;
    QUERY_ALIGN_LINE_COMMENTS                     = def.QUERY_ALIGN_LINE_COMMENTS;
    SUBQUERY_OPENING                              = def.SUBQUERY_OPENING;
    SUBQUERY_CONTENT                              = def.SUBQUERY_CONTENT;
    SUBQUERY_CLOSING                              = def.SUBQUERY_CLOSING;
    SUBQUERY_PAR_SPACE_INSIDE                     = def.SUBQUERY_PAR_SPACE_INSIDE;
    INSERT_INTO_NL                                = def.INSERT_INTO_NL;
    INSERT_OPENING                                = def.INSERT_OPENING;
    INSERT_CONTENT                                = def.INSERT_CONTENT;
    INSERT_CLOSING                                = def.INSERT_CLOSING;
    INSERT_TABLE_EL_LINE                          = def.INSERT_TABLE_EL_LINE;
    INSERT_VALUES_EL_LINE                         = def.INSERT_VALUES_EL_LINE;
    INSERT_EL_WRAP                                = def.INSERT_EL_WRAP;
    INSERT_EL_COMMA                               = def.INSERT_EL_COMMA;
    INSERT_SPACE_WITHIN_PARENTHESES               = def.INSERT_SPACE_WITHIN_PARENTHESES;
    INSERT_COLLAPSE_MULTI_ROW_VALUES              = def.INSERT_COLLAPSE_MULTI_ROW_VALUES;
    SET_EL_LINE                                   = def.SET_EL_LINE;
    SET_EL_WRAP                                   = def.SET_EL_WRAP;
    SET_EL_COMMA                                  = def.SET_EL_COMMA;
    SET_ALIGN_EQUAL_SIGN                          = def.SET_ALIGN_EQUAL_SIGN;
    WITH_EL_LINE                                  = def.WITH_EL_LINE;
    WITH_EL_WRAP                                  = def.WITH_EL_WRAP;
    WITH_EL_COMMA                                 = def.WITH_EL_COMMA;
    WITH_ALIGN_AS                                 = def.WITH_ALIGN_AS;
    SELECT_EL_LINE                                = def.SELECT_EL_LINE;
    SELECT_EL_WRAP                                = def.SELECT_EL_WRAP;
    SELECT_EL_COMMA                               = def.SELECT_EL_COMMA;
    SELECT_NEW_LINE_AFTER_ALL_DISTINCT            = def.SELECT_NEW_LINE_AFTER_ALL_DISTINCT;
    SELECT_KEEP_N_ITEMS_IN_LINE                   = def.SELECT_KEEP_N_ITEMS_IN_LINE;
    SELECT_USE_AS_WORD                            = def.SELECT_USE_AS_WORD;
    SELECT_ALIGN_AS                               = def.SELECT_ALIGN_AS;
    FROM_EL_LINE                                  = def.FROM_EL_LINE;
    FROM_EL_WRAP                                  = def.FROM_EL_WRAP;
    FROM_EL_COMMA                                 = def.FROM_EL_COMMA;
    FROM_WRAP_JOIN_1                              = def.FROM_WRAP_JOIN_1;
    FROM_WRAP_JOIN_2                              = def.FROM_WRAP_JOIN_2;
    FROM_WRAP_ON                                  = def.FROM_WRAP_ON;
    FROM_ALIGN_JOIN_TABLES                        = def.FROM_ALIGN_JOIN_TABLES;
    FROM_ALIGN_ALIASES                            = def.FROM_ALIGN_ALIASES;
    FROM_INDENT_JOIN                              = def.FROM_INDENT_JOIN;
    FROM_ONLY_JOIN_INDENT                         = def.FROM_ONLY_JOIN_INDENT;
    FROM_PLACE_ON                                 = def.FROM_PLACE_ON;
    WHERE_EL_LINE                                 = def.WHERE_EL_LINE;
    WHERE_EL_WRAP                                 = def.WHERE_EL_WRAP;
    WHERE_EL_BOUND                                = def.WHERE_EL_BOUND;
    ORDER_EL_LINE                                 = def.ORDER_EL_LINE;
    ORDER_EL_WRAP                                 = def.ORDER_EL_WRAP;
    ORDER_EL_COMMA                                = def.ORDER_EL_COMMA;
    ORDER_ALIGN_ASC_DESC                          = def.ORDER_ALIGN_ASC_DESC;
    TABLE_OPENING                                 = def.TABLE_OPENING;
    TABLE_CONTENT                                 = def.TABLE_CONTENT;
    TABLE_CLOSING                                 = def.TABLE_CLOSING;
    TABLE_TYPES_ALIGN                             = def.TABLE_TYPES_ALIGN;
    TABLE_DEFAULTS_ALIGN                          = def.TABLE_DEFAULTS_ALIGN;
    TABLE_NULLABILITIES_ALIGN                     = def.TABLE_NULLABILITIES_ALIGN;
    TABLE_COLLAPSE                                = def.TABLE_COLLAPSE;
    TABLE_ALTER_INSTRUCTION_WRAP                  = def.TABLE_ALTER_INSTRUCTION_WRAP;
    TABLE_ALTER_INSTRUCTION_ALIGN                 = def.TABLE_ALTER_INSTRUCTION_ALIGN;
    CONSTRAINT_WRAP_1                             = def.CONSTRAINT_WRAP_1;
    CONSTRAINT_WRAP_2                             = def.CONSTRAINT_WRAP_2;
    CONSTRAINT_WRAP_3                             = def.CONSTRAINT_WRAP_3;
    CONSTRAINT_WRAP_4                             = def.CONSTRAINT_WRAP_4;
    POST_OPT_WRAP_1                               = def.POST_OPT_WRAP_1;
    POST_OPT_WRAP_2                               = def.POST_OPT_WRAP_2;
    POST_OPT_INDENT                               = def.POST_OPT_INDENT;
    POST_OPT_ALIGN                                = def.POST_OPT_ALIGN;
    CREATE_SCHEMA_CONTENT_INDENT                  = def.CREATE_SCHEMA_CONTENT_INDENT;
    CREATE_SCHEMA_BLANK_LINES_MIN                 = def.CREATE_SCHEMA_BLANK_LINES_MIN;
    CREATE_SCHEMA_BLANK_LINES_MAX                 = def.CREATE_SCHEMA_BLANK_LINES_MAX;
    VIEW_WRAP_AS                                  = def.VIEW_WRAP_AS;
    VIEW_WRAP_QUERY                               = def.VIEW_WRAP_QUERY;
    VIEW_INDENT_QUERY                             = def.VIEW_INDENT_QUERY;
    ROUTINE_ARG_OPENING                           = def.ROUTINE_ARG_OPENING;
    ROUTINE_ARG_CONTENT                           = def.ROUTINE_ARG_CONTENT;
    ROUTINE_ARG_CLOSING                           = def.ROUTINE_ARG_CLOSING;
    ROUTINE_ARG_WRAP                              = def.ROUTINE_ARG_WRAP;
    ROUTINE_ARG_COMMA                             = def.ROUTINE_ARG_COMMA;
    ROUTINE_ARG_SPACE_WITHIN_PARENTHESES          = def.ROUTINE_ARG_SPACE_WITHIN_PARENTHESES;
    ROUTINE_ARG_ALIGN_TYPES                       = def.ROUTINE_ARG_ALIGN_TYPES;
    ROUTINE_AS_WRAP                               = def.ROUTINE_AS_WRAP;
    ROUTINE_PG_L_QUOTE_WRAP_BEFORE                = def.ROUTINE_PG_L_QUOTE_WRAP_BEFORE;
    ROUTINE_PG_L_QUOTE_WRAP_AFTER                 = def.ROUTINE_PG_L_QUOTE_WRAP_AFTER;
    ROUTINE_PG_R_QUOTE_WRAP_BEFORE                = def.ROUTINE_PG_R_QUOTE_WRAP_BEFORE;
    ROUTINE_PG_R_QUOTE_WRAP_AFTER                 = def.ROUTINE_PG_R_QUOTE_WRAP_AFTER;
    IMP_COMMON_WRAP_EVERY_STATEMENT               = def.IMP_COMMON_WRAP_EVERY_STATEMENT;
    IMP_COMMON_KEEP_BLANK_LINES_IN_CODE           = def.IMP_COMMON_KEEP_BLANK_LINES_IN_CODE;
    IMP_DECLARE_CONTENT_WRAP                      = def.IMP_DECLARE_CONTENT_WRAP;
    IMP_DECLARE_EL_WRAP                           = def.IMP_DECLARE_EL_WRAP;
    IMP_DECLARE_ALIGN_TYPE                        = def.IMP_DECLARE_ALIGN_TYPE;
    IMP_DECLARE_ALIGN_EQ                          = def.IMP_DECLARE_ALIGN_EQ;
    IMP_DECLARE_ALIGN_DEFAULT                     = def.IMP_DECLARE_ALIGN_DEFAULT;
    IMP_IF_THEN_WRAP_THEN                         = def.IMP_IF_THEN_WRAP_THEN;
    IMP_IF_THEN_WRAP_ELSE                         = def.IMP_IF_THEN_WRAP_ELSE;
    IMP_IF_THEN_WRAP_INNER                        = def.IMP_IF_THEN_WRAP_INNER;
    IMP_IF_THEN_INDENT_THEN_ELSE                  = def.IMP_IF_THEN_INDENT_THEN_ELSE;
    IMP_IF_THEN_INDENT_END                        = def.IMP_IF_THEN_INDENT_END;
    IMP_IF_THEN_COLLAPSE                          = def.IMP_IF_THEN_COLLAPSE;
    IMP_LOOP_LOOP_WRAP                            = def.IMP_LOOP_LOOP_WRAP;
    IMP_LOOP_LOOP_INDENT                          = def.IMP_LOOP_LOOP_INDENT;
    IMP_LOOP_END_INDENT                           = def.IMP_LOOP_END_INDENT;
    IMP_LOOP_COLLAPSE                             = def.IMP_LOOP_COLLAPSE;
    CORTEGE_SPACE_BEFORE_L_PAREN                  = def.CORTEGE_SPACE_BEFORE_L_PAREN;
    CORTEGE_COMMA_1ST                             = def.CORTEGE_COMMA_1ST;
    CORTEGE_CLOSING                               = def.CORTEGE_CLOSING;
    CORTEGE_SPACE_WITHIN_PARENTHESES              = def.CORTEGE_SPACE_WITHIN_PARENTHESES;
    CORTEGE_SPACE_BEFORE_COMMA                    = def.CORTEGE_SPACE_BEFORE_COMMA;
    CORTEGE_SPACE_AFTER_COMMA                     = def.CORTEGE_SPACE_AFTER_COMMA;
    EXPR_SPACE_AROUND_OPERATOR                    = def.EXPR_SPACE_AROUND_OPERATOR;
    EXPR_SPACE_WITHIN_PARENTHESES                 = def.EXPR_SPACE_WITHIN_PARENTHESES;
    EXPR_BINARY_OP_ALIGN                          = def.EXPR_BINARY_OP_ALIGN;
    EXPR_CALL_SPACE_INSIDE_PARENTHESES            = def.EXPR_CALL_SPACE_INSIDE_PARENTHESES;
    EXPR_CALL_SPACE_BEFORE_COMMA                  = def.EXPR_CALL_SPACE_BEFORE_COMMA;
    EXPR_CALL_SPACE_AFTER_COMMA                   = def.EXPR_CALL_SPACE_AFTER_COMMA;
    EXPR_CASE_WHEN_WRAP                           = def.EXPR_CASE_WHEN_WRAP;
    EXPR_CASE_WHEN_INDENT                         = def.EXPR_CASE_WHEN_INDENT;
    EXPR_CASE_THEN_WRAP                           = def.EXPR_CASE_THEN_WRAP;
    EXPR_CASE_THEN_ALIGN                          = def.EXPR_CASE_THEN_ALIGN;
    EXPR_CASE_ELSE_ALIGN_THEN                     = def.EXPR_CASE_ELSE_ALIGN_THEN;
    EXPR_CASE_END                                 = def.EXPR_CASE_END;
    EXPR_CASE_KEEP_NL_AFTER_THEN                  = def.EXPR_CASE_KEEP_NL_AFTER_THEN;
    EXPR_CASE_COLLAPSE                            = def.EXPR_CASE_COLLAPSE;
    INDEX_NAME_TEMPLATE                           = def.INDEX_NAME_TEMPLATE;
    PRIMARY_KEY_NAME_TEMPLATE                     = def.PRIMARY_KEY_NAME_TEMPLATE;
    FOREIGN_KEY_NAME_TEMPLATE                     = def.FOREIGN_KEY_NAME_TEMPLATE;
    // @formatter:on
  }


  @SuppressWarnings("deprecation")
  private void assignLegacyDefaults(@NotNull SqlCodeStyleSettingsDefault def) {
    // @formatter:off
    SUBQUERY_L_PAR_NL_OUTSIDE                     = def.SUBQUERY_L_PAR_NL_OUTSIDE;
    SUBQUERY_L_PAR_NL_INSIDE                      = def.SUBQUERY_L_PAR_NL_INSIDE;
    SUBQUERY_R_PAR_NL_INSIDE                      = def.SUBQUERY_R_PAR_NL_INSIDE;
    SUBQUERY_R_PAR_ALIGN                          = def.SUBQUERY_R_PAR_ALIGN;
    SUBQUERY_INDENT_INSIDE                        = def.SUBQUERY_INDENT_INSIDE;
    SPACES_AROUND_OPERATORS                       = def.SPACES_AROUND_OPERATORS;
    ALIGN_AS_IN_SELECT_STATEMENT                  = def.ALIGN_AS_IN_SELECT_STATEMENT;
    ALIGN_TYPE_IN_CREATE_STATEMENT                = def.ALIGN_TYPE_IN_CREATE_STATEMENT;
    ALIGN_TYPE_IN_BLOCK_STATEMENT                 = def.ALIGN_TYPE_IN_BLOCK_STATEMENT;
    ALIGN_TYPE_IN_ARGUMENT_DEFINITION             = def.ALIGN_TYPE_IN_ARGUMENT_DEFINITION;
    ALIGN_INSIDE_BINARY_EXPRESSION                = def.ALIGN_INSIDE_BINARY_EXPRESSION;
    ALIGN_INSIDE_QUERY_EXPRESSION                 = def.ALIGN_INSIDE_QUERY_EXPRESSION;
    ALIGN_EQ_INSIDE_SET_CLAUSE                    = def.ALIGN_EQ_INSIDE_SET_CLAUSE;
    NEW_LINE_BEFORE_FROM                          = def.NEW_LINE_BEFORE_FROM;
    NEW_LINE_BEFORE_JOIN                          = def.NEW_LINE_BEFORE_JOIN;
    NEW_LINE_BEFORE_JOIN_CONDITION                = def.NEW_LINE_BEFORE_JOIN_CONDITION;
    NEW_LINE_BEFORE_WHERE                         = def.NEW_LINE_BEFORE_WHERE;
    NEW_LINE_BEFORE_GROUP_BY                      = def.NEW_LINE_BEFORE_GROUP_BY;
    NEW_LINE_BEFORE_ORDER_BY                      = def.NEW_LINE_BEFORE_ORDER_BY;
    NEW_LINE_BEFORE_HAVING                        = def.NEW_LINE_BEFORE_HAVING;
    NEW_LINE_BEFORE_THEN                          = def.NEW_LINE_BEFORE_THEN;
    NEW_LINE_BEFORE_ELSE                          = def.NEW_LINE_BEFORE_ELSE;
    NEW_LINE_BEFORE_OTHER_CLAUSES                 = def.NEW_LINE_BEFORE_OTHER_CLAUSES;
    NEW_LINE_BEFORE_COMMA                         = def.NEW_LINE_BEFORE_COMMA;
    NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS      = def.NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS;
    NEW_LINE_BEFORE_QUERY_INSIDE_DML              = def.NEW_LINE_BEFORE_QUERY_INSIDE_DML;
    NEW_LINE_AROUND_SEMICOLON                     = def.NEW_LINE_AROUND_SEMICOLON;
    INDENT_JOIN                                   = def.INDENT_JOIN;
    INDENT_JOIN_CONDITION                         = def.INDENT_JOIN_CONDITION;
    INDENT_SELECT_INTO_CLAUSE                     = def.INDENT_SELECT_INTO_CLAUSE;
    WRAP_INSIDE_CREATE_TABLE                      = def.WRAP_INSIDE_CREATE_TABLE;
    WRAP_INSIDE_SELECT                            = def.WRAP_INSIDE_SELECT;
    WRAP_INSIDE_JOIN_EXPRESSION                   = def.WRAP_INSIDE_JOIN_EXPRESSION;
    WRAP_INSIDE_GROUP_BY                          = def.WRAP_INSIDE_GROUP_BY;
    WRAP_INSIDE_WHERE                             = def.WRAP_INSIDE_WHERE;
    WRAP_INSIDE_ORDER_BY                          = def.WRAP_INSIDE_ORDER_BY;
    WRAP_INSIDE_SET                               = def.WRAP_INSIDE_SET;
    WRAP_INSIDE_ARGUMENT_DEFINITION               = def.WRAP_INSIDE_ARGUMENT_DEFINITION;
    WRAP_INSIDE_CALL_EXPRESSION                   = def.WRAP_INSIDE_CALL_EXPRESSION;
    WRAP_INSIDE_VALUES_EXPRESSION                 = def.WRAP_INSIDE_VALUES_EXPRESSION;
    WRAP_VALUES_EXPRESSION                        = def.WRAP_VALUES_EXPRESSION;
    WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES   = def.WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES;
    NEW_LINE_AFTER_SELECT                         = def.NEW_LINE_AFTER_SELECT;
    NEW_LINE_AFTER_SELECT_ITEM                    = def.NEW_LINE_AFTER_SELECT_ITEM;
    NEW_LINE_AFTER_SELECT_2                       = def.NEW_LINE_AFTER_SELECT_2;
    // @formatter:on
  }


}