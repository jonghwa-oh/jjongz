package com.intellij.sql.formatter.settings;

import org.intellij.lang.annotations.MagicConstant;

import static com.intellij.psi.codeStyle.CommonCodeStyleSettings.*;
import static com.intellij.sql.formatter.settings.SqlCodeStyleConst.*;

@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
public final class SqlCodeStyleSettingsDefault {

  public static final int CURRENT_VERSION = 2;


  // @formatter:off

  /**
   * The settings that are assigned by default when an instance of {@link SqlCodeStyleSettings} is created.
   * They are also default for new users.
   */
  public static final SqlCodeStyleSettingsDefault MODERN =
    new SqlCodeStyleSettingsDefault(
      /* ALIAS_CASE */                                   DO_NOT_CHANGE,
      /* KEYWORD_CASE */                                 DO_NOT_CHANGE,
      /* TYPE_CASE */                                    DO_NOT_CHANGE,
      /* IDENTIFIER_CASE */                              DO_NOT_CHANGE,
      /* QUOTED_IDENTIFIER_CASE */                       DO_NOT_CHANGE,
      /* QUOTE_IDENTIFIER */                             DO_NOT_CHANGE,
      /* QUOTE_TYPE */                                   QUOTE_AUTO,
      /* QUERY_SECTION_1ST_WORD_ALIGN */                 QUERY_SECTION_1ST_WORD_ALIGN_LEFT,
      /* QUERY_EL_LINE */                                EL_SAME,
      /* QUERY_EL_COMMA */                               AS_IS,
      /* QUERY_IN_ONE_STRING */                          QUERY_IN_ONE_STRING_INNER_ONLY,
      /* QUERY_TRUE_INDENT */                            true,
      /* QUERY_ALIGN_ELEMENTS */                         true,
      /* QUERY_ALIGN_LINE_COMMENTS */                    true,
      /* SUBQUERY_OPENING */                             AS_IS,
      /* SUBQUERY_CONTENT */                             AS_IS,
      /* SUBQUERY_CLOSING */                             AS_IS,
      /* SUBQUERY_L_PAR_NL_OUTSIDE */                    false,
      /* SUBQUERY_L_PAR_NL_INSIDE */                     false,
      /* SUBQUERY_R_PAR_NL_INSIDE */                     false,
      /* SUBQUERY_R_PAR_ALIGN */                         SUBQUERY_R_PAR_ALIGN_TO_L_PAR,
      /* SUBQUERY_INDENT_INSIDE */                       false,
      /* SUBQUERY_PAR_SPACE_INSIDE */                    false,
      /* INSERT_INTO_NL */                               AS_IS,
      /* INSERT_OPENING */                               OPENING_SAME,
      /* INSERT_CONTENT */                               CONTENT_SAME_ALIGNED,
      /* INSERT_CLOSING */                               CLOSING_SAME,
      /* INSERT_TABLE_EL_LINE */                         COMMONLY,
      /* INSERT_VALUES_EL_LINE */                        COMMONLY,
      /* INSERT_EL_WRAP */                               EL_WRAP,
      /* INSERT_EL_COMMA */                              COMMONLY,
      /* INSERT_SPACE_WITHIN_PARENTHESES */              false,
      /* INSERT_COLLAPSE_MULTI_ROW_VALUES */             false,
      /* SET_EL_LINE */                                  COMMONLY,
      /* SET_EL_WRAP */                                  EL_CHOP,
      /* SET_EL_COMMA */                                 COMMONLY,
      /* SET_ALIGN_EQUAL_SIGN */                         true,
      /* WITH_EL_LINE */                                 COMMONLY,
      /* WITH_EL_WRAP */                                 EL_CHOP,
      /* WITH_EL_COMMA */                                COMMONLY,
      /* WITH_ALIGN_AS */                                false,
      /* SELECT_EL_LINE */                               COMMONLY,
      /* SELECT_EL_WRAP */                               EL_CHOP,
      /* SELECT_EL_COMMA */                              COMMONLY,
      /* SELECT_NEW_LINE_AFTER_ALL_DISTINCT */           false,
      /* SELECT_KEEP_N_ITEMS_IN_LINE */                  7,
      /* SELECT_USE_AS_WORD */                           AS_IS,
      /* SELECT_ALIGN_AS */                              true,
      /* FROM_EL_LINE */                                 COMMONLY,
      /* FROM_EL_WRAP */                                 EL_CHOP,
      /* FROM_EL_COMMA */                                COMMONLY,
      /* FROM_WRAP_JOIN_1 */                             true,
      /* FROM_WRAP_JOIN_2 */                             true,
      /* FROM_WRAP_ON */                                 false,
      /* FROM_ALIGN_JOIN_TABLES */                       false,
      /* FROM_ALIGN_ALIASES */                           false,
      /* FROM_INDENT_JOIN */                             true,
      /* FROM_ONLY_JOIN_INDENT */                        FROM_ONLY_JOIN_INDENT_AS_USUAL,
      /* FROM_PLACE_ON */                                FROM_PLACE_ON_TABLE,
      /* WHERE_EL_LINE */                                COMMONLY,
      /* WHERE_EL_WRAP */                                EL_CHOP,
      /* WHERE_EL_BOUND */                               EL_COMMA_1ST,
      /* ORDER_EL_LINE */                                COMMONLY,
      /* ORDER_EL_WRAP */                                EL_WRAP,
      /* ORDER_EL_COMMA */                               COMMONLY,
      /* ORDER_ALIGN_ASC_DESC */                         false,
      /* TABLE_OPENING */                                OPENING_ALIGN,
      /* TABLE_CONTENT */                                CONTENT_WRAPPED_INDENTED,
      /* TABLE_CLOSING */                                CLOSING_UNDER_OPENING,
      /* TABLE_TYPES_ALIGN */                            true,
      /* TABLE_DEFAULTS_ALIGN */                         true,
      /* TABLE_NULLABILITIES_ALIGN */                    true,
      /* TABLE_COLLAPSE */                               false,
      /* TABLE_ALTER_INSTRUCTION_WRAP */                 ADD,
      /* TABLE_ALTER_INSTRUCTION_ALIGN */                true,
      /* CONSTRAINT_WRAP_1 */                            true,
      /* CONSTRAINT_WRAP_2 */                            false,
      /* CONSTRAINT_WRAP_3 */                            false,
      /* CONSTRAINT_WRAP_4 */                            false,
      /* POST_OPT_WRAP_1 */                              false,
      /* POST_OPT_WRAP_2 */                              true,
      /* POST_OPT_INDENT */                              true,
      /* POST_OPT_ALIGN */                               true,
      /* CREATE_SCHEMA_CONTENT_INDENT */                 true,
      /* CREATE_SCHEMA_BLANK_LINES_MIN */                1,
      /* CREATE_SCHEMA_BLANK_LINES_MAX */                1,
      /* VIEW_WRAP_AS */                                 false,
      /* VIEW_WRAP_QUERY */                              true,
      /* VIEW_INDENT_QUERY */                            false,
      /* ROUTINE_ARG_OPENING */                          OPENING_SAME,
      /* ROUTINE_ARG_CONTENT */                          AS_IS,
      /* ROUTINE_ARG_CLOSING */                          AS_IS,
      /* ROUTINE_ARG_WRAP */                             EL_WRAP,
      /* ROUTINE_ARG_COMMA */                            AS_IS,
      /* ROUTINE_ARG_SPACE_WITHIN_PARENTHESES */         false,
      /* ROUTINE_ARG_ALIGN_TYPES */                      false,
      /* ROUTINE_AS_WRAP */                              false,
      /* ROUTINE_PG_L_QUOTE_WRAP_BEFORE */               true,
      /* ROUTINE_PG_L_QUOTE_WRAP_AFTER */                true,
      /* ROUTINE_PG_R_QUOTE_WRAP_BEFORE */               true,
      /* ROUTINE_PG_R_QUOTE_WRAP_AFTER */                false,
      /* IMP_COMMON_WRAP_EVERY_STATEMENT */              false,
      /* IMP_COMMON_KEEP_BLANK_LINES_IN_CODE */          2,
      /* IMP_DECLARE_CONTENT_WRAP */                     true,
      /* IMP_DECLARE_EL_WRAP */                          AS_IS,
      /* IMP_DECLARE_ALIGN_TYPE */                       true,
      /* IMP_DECLARE_ALIGN_EQ */                         true,
      /* IMP_DECLARE_ALIGN_DEFAULT */                    false,
      /* IMP_IF_THEN_WRAP_THEN */                        false,
      /* IMP_IF_THEN_WRAP_ELSE */                        true,
      /* IMP_IF_THEN_WRAP_INNER */                       true,
      /* IMP_IF_THEN_INDENT_THEN_ELSE */                 false,
      /* IMP_IF_THEN_INDENT_END */                       false,
      /* IMP_IF_THEN_COLLAPSE */                         true,
      /* IMP_LOOP_LOOP_WRAP */                           true,
      /* IMP_LOOP_LOOP_INDENT */                         true,
      /* IMP_LOOP_END_INDENT */                          false,
      /* IMP_LOOP_COLLAPSE */                            false,
      /* CORTEGE_SPACE_BEFORE_L_PAREN */                 true,
      /* CORTEGE_COMMA_1ST */                            false,
      /* CORTEGE_CLOSING */                              AS_IS,
      /* CORTEGE_SPACE_WITHIN_PARENTHESES */             false,
      /* CORTEGE_SPACE_BEFORE_COMMA */                   false,
      /* CORTEGE_SPACE_AFTER_COMMA */                    true,
      /* EXPR_SPACE_AROUND_OPERATOR */                   ADD,
      /* EXPR_SPACE_WITHIN_PARENTHESES */                false,
      /* EXPR_BINARY_OP_ALIGN */                         true,
      /* EXPR_CALL_SPACE_INSIDE_PARENTHESES */           false,
      /* EXPR_CALL_SPACE_BEFORE_COMMA */                 false,
      /* EXPR_CALL_SPACE_AFTER_COMMA */                  true,
      /* EXPR_CASE_WHEN_WRAP */                          true,
      /* EXPR_CASE_WHEN_INDENT */                        true,
      /* EXPR_CASE_THEN_WRAP */                          false,
      /* EXPR_CASE_THEN_ALIGN */                         false,
      /* EXPR_CASE_ELSE_ALIGN_THEN */                    false,
      /* EXPR_CASE_END */                                AS_IS,
      /* EXPR_CASE_KEEP_NL_AFTER_THEN */                 false,
      /* EXPR_CASE_COLLAPSE */                           true,
      /* INDEX_NAME_TEMPLATE */                          "{table}_{columns}_{unique?u:}index",
      /* PRIMARY_KEY_NAME_TEMPLATE */                    "{table}_pk",
      /* FOREIGN_KEY_NAME_TEMPLATE */                    "{table}_{ref_table}_{ref_columns}_fk",
      /* SPACES_AROUND_OPERATORS */                      true,
      /* ALIGN_AS_IN_SELECT_STATEMENT */                 false,
      /* ALIGN_TYPE_IN_CREATE_STATEMENT */               false,
      /* ALIGN_TYPE_IN_BLOCK_STATEMENT */                false,
      /* ALIGN_TYPE_IN_ARGUMENT_DEFINITION */            false,
      /* ALIGN_INSIDE_BINARY_EXPRESSION */               false,
      /* ALIGN_INSIDE_QUERY_EXPRESSION */                false,
      /* ALIGN_EQ_INSIDE_SET_CLAUSE */                   false,
      /* NEW_LINE_BEFORE_FROM */                         false,
      /* NEW_LINE_BEFORE_JOIN */                         false,
      /* NEW_LINE_BEFORE_JOIN_CONDITION */               false,
      /* NEW_LINE_BEFORE_WHERE */                        false,
      /* NEW_LINE_BEFORE_GROUP_BY */                     false,
      /* NEW_LINE_BEFORE_ORDER_BY */                     false,
      /* NEW_LINE_BEFORE_HAVING */                       false,
      /* NEW_LINE_BEFORE_THEN */                         false,
      /* NEW_LINE_BEFORE_ELSE */                         false,
      /* NEW_LINE_BEFORE_OTHER_CLAUSES */                false,
      /* NEW_LINE_BEFORE_COMMA */                        false,
      /* NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS */     false,
      /* NEW_LINE_BEFORE_QUERY_INSIDE_DML */             false,
      /* NEW_LINE_AROUND_SEMICOLON */                    false,
      /* INDENT_JOIN */                                  false,
      /* INDENT_JOIN_CONDITION */                        false,
      /* INDENT_SELECT_INTO_CLAUSE */                    false,
      /* WRAP_INSIDE_CREATE_TABLE */                     DO_NOT_WRAP,
      /* WRAP_INSIDE_SELECT */                           DO_NOT_WRAP,
      /* WRAP_INSIDE_JOIN_EXPRESSION */                  DO_NOT_WRAP,
      /* WRAP_INSIDE_GROUP_BY */                         DO_NOT_WRAP,
      /* WRAP_INSIDE_WHERE */                            DO_NOT_WRAP,
      /* WRAP_INSIDE_ORDER_BY */                         DO_NOT_WRAP,
      /* WRAP_INSIDE_SET */                              DO_NOT_WRAP,
      /* WRAP_INSIDE_ARGUMENT_DEFINITION */              DO_NOT_WRAP,
      /* WRAP_INSIDE_CALL_EXPRESSION */                  DO_NOT_WRAP,
      /* WRAP_INSIDE_VALUES_EXPRESSION */                DO_NOT_WRAP,
      /* WRAP_VALUES_EXPRESSION */                       DO_NOT_WRAP,
      /* WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES */  DO_NOT_WRAP,
      /* NEW_LINE_AFTER_SELECT */                        false,
      /* NEW_LINE_AFTER_SELECT_ITEM */                   false,
      /* NEW_LINE_AFTER_SELECT_2 */                      NEVER
    );


  /**
   * These settings is a combination of the old defaults (were before formatter was refactored)
   * and modern settings converted from default legacy ones (in other words, if the legacy entries
   * of this default set is converted to modern ones, the latter should be the same as these entries).
   * These settings are used for compatibility purposes.
   */
  public static final SqlCodeStyleSettingsDefault LEGACY =
    new SqlCodeStyleSettingsDefault(
      /* ALIAS_CASE */                                   AS_IDENTIFIERS,
      /* KEYWORD_CASE */                                 DO_NOT_CHANGE,
      /* TYPE_CASE */                                    AS_KEYWORDS,
      /* IDENTIFIER_CASE */                              DO_NOT_CHANGE,
      /* QUOTED_IDENTIFIER_CASE */                       DO_NOT_CHANGE,
      /* QUOTE_IDENTIFIER */                             DO_NOT_CHANGE,
      /* QUOTE_TYPE */                                   QUOTE_AUTO,
      /* QUERY_SECTION_1ST_WORD_ALIGN */                 QUERY_SECTION_1ST_WORD_ALIGN_LEFT,
      /* QUERY_EL_LINE */                                EL_SAME,
      /* QUERY_EL_COMMA */                               AS_IS,
      /* QUERY_IN_ONE_STRING */                          QUERY_IN_ONE_STRING_INNER_ONLY,
      /* QUERY_TRUE_INDENT */                            false,
      /* QUERY_ALIGN_ELEMENTS */                         false,
      /* QUERY_ALIGN_LINE_COMMENTS */                    false,
      /* SUBQUERY_OPENING */                             AS_IS,
      /* SUBQUERY_CONTENT */                             AS_IS,
      /* SUBQUERY_CLOSING */                             AS_IS,
      /* SUBQUERY_L_PAR_NL_OUTSIDE */                    false,
      /* SUBQUERY_L_PAR_NL_INSIDE */                     false,
      /* SUBQUERY_R_PAR_NL_INSIDE */                     false,
      /* SUBQUERY_R_PAR_ALIGN */                         SUBQUERY_R_PAR_ALIGN_TO_L_PAR,
      /* SUBQUERY_INDENT_INSIDE */                       false,
      /* SUBQUERY_PAR_SPACE_INSIDE */                    false,
      /* INSERT_INTO_NL */                               AS_IS,
      /* INSERT_OPENING */                               OPENING_SAME,
      /* INSERT_CONTENT */                               CONTENT_SAME_ALIGNED,
      /* INSERT_CLOSING */                               CLOSING_SAME,
      /* INSERT_TABLE_EL_LINE */                         COMMONLY,
      /* INSERT_VALUES_EL_LINE */                        COMMONLY,
      /* INSERT_EL_WRAP */                               EL_WRAP,
      /* INSERT_EL_COMMA */                              EL_COMMA_LAST,
      /* INSERT_SPACE_WITHIN_PARENTHESES */              false,
      /* INSERT_COLLAPSE_MULTI_ROW_VALUES */             false,
      /* SET_EL_LINE */                                  COMMONLY,
      /* SET_EL_WRAP */                                  AS_IS,
      /* SET_EL_COMMA */                                 AS_IS,
      /* SET_ALIGN_EQUAL_SIGN */                         true,
      /* WITH_EL_LINE */                                 COMMONLY,
      /* WITH_EL_WRAP */                                 AS_IS,
      /* WITH_EL_COMMA */                                AS_IS,
      /* WITH_ALIGN_AS */                                false,
      /* SELECT_EL_LINE */                               COMMONLY,
      /* SELECT_EL_WRAP */                               EL_WRAP,
      /* SELECT_EL_COMMA */                              EL_COMMA_LAST,
      /* SELECT_NEW_LINE_AFTER_ALL_DISTINCT */           false,
      /* SELECT_KEEP_N_ITEMS_IN_LINE */                  7,
      /* SELECT_USE_AS_WORD */                           AS_IS,
      /* SELECT_ALIGN_AS */                              true,
      /* FROM_EL_LINE */                                 COMMONLY,
      /* FROM_EL_WRAP */                                 EL_CHOP_LONG,
      /* FROM_EL_COMMA */                                EL_COMMA_LAST,
      /* FROM_WRAP_JOIN_1 */                             true,
      /* FROM_WRAP_JOIN_2 */                             true,
      /* FROM_WRAP_ON */                                 false,
      /* FROM_ALIGN_JOIN_TABLES */                       false,
      /* FROM_ALIGN_ALIASES */                           false,
      /* FROM_INDENT_JOIN */                             true,
      /* FROM_ONLY_JOIN_INDENT */                        FROM_ONLY_JOIN_INDENT_AS_USUAL,
      /* FROM_PLACE_ON */                                FROM_PLACE_ON_JOIN,
      /* WHERE_EL_LINE */                                COMMONLY,
      /* WHERE_EL_WRAP */                                EL_WRAP,
      /* WHERE_EL_BOUND */                               EL_COMMA_LAST,
      /* ORDER_EL_LINE */                                COMMONLY,
      /* ORDER_EL_WRAP */                                EL_WRAP,
      /* ORDER_EL_COMMA */                               EL_COMMA_LAST,
      /* ORDER_ALIGN_ASC_DESC */                         false,
      /* TABLE_OPENING */                                OPENING_SAME,
      /* TABLE_CONTENT */                                CONTENT_WRAPPED_EGYPT,
      /* TABLE_CLOSING */                                CLOSING_UNDER_BEGIN,
      /* TABLE_TYPES_ALIGN */                            false,
      /* TABLE_DEFAULTS_ALIGN */                         false,
      /* TABLE_NULLABILITIES_ALIGN */                    false,
      /* TABLE_COLLAPSE */                               false,
      /* TABLE_ALTER_INSTRUCTION_WRAP */                 ADD,
      /* TABLE_ALTER_INSTRUCTION_ALIGN */                false,
      /* CONSTRAINT_WRAP_1 */                            true,
      /* CONSTRAINT_WRAP_2 */                            false,
      /* CONSTRAINT_WRAP_3 */                            false,
      /* CONSTRAINT_WRAP_4 */                            false,
      /* POST_OPT_WRAP_1 */                              true,
      /* POST_OPT_WRAP_2 */                              true,
      /* POST_OPT_INDENT */                              true,
      /* POST_OPT_ALIGN */                               false,
      /* CREATE_SCHEMA_CONTENT_INDENT */                 true,
      /* CREATE_SCHEMA_BLANK_LINES_MIN */                1,
      /* CREATE_SCHEMA_BLANK_LINES_MAX */                1,
      /* VIEW_WRAP_AS */                                 false,
      /* VIEW_WRAP_QUERY */                              true,
      /* VIEW_INDENT_QUERY */                            false,
      /* ROUTINE_ARG_OPENING */                          OPENING_SAME,
      /* ROUTINE_ARG_CONTENT */                          AS_IS,
      /* ROUTINE_ARG_CLOSING */                          AS_IS,
      /* ROUTINE_ARG_WRAP */                             EL_WRAP,
      /* ROUTINE_ARG_COMMA */                            EL_COMMA_LAST,
      /* ROUTINE_ARG_SPACE_WITHIN_PARENTHESES */         false,
      /* ROUTINE_ARG_ALIGN_TYPES */                      false,
      /* ROUTINE_AS_WRAP */                              false,
      /* ROUTINE_PG_L_QUOTE_WRAP_BEFORE */               true,
      /* ROUTINE_PG_L_QUOTE_WRAP_AFTER */                true,
      /* ROUTINE_PG_R_QUOTE_WRAP_BEFORE */               true,
      /* ROUTINE_PG_R_QUOTE_WRAP_AFTER */                false,
      /* IMP_COMMON_WRAP_EVERY_STATEMENT */              false,
      /* IMP_COMMON_KEEP_BLANK_LINES_IN_CODE */          2,
      /* IMP_DECLARE_CONTENT_WRAP */                     true,
      /* IMP_DECLARE_EL_WRAP */                          AS_IS,
      /* IMP_DECLARE_ALIGN_TYPE */                       true,
      /* IMP_DECLARE_ALIGN_EQ */                         true,
      /* IMP_DECLARE_ALIGN_DEFAULT */                    false,
      /* IMP_IF_THEN_WRAP_THEN */                        false,
      /* IMP_IF_THEN_WRAP_ELSE */                        true,
      /* IMP_IF_THEN_WRAP_INNER */                       true,
      /* IMP_IF_THEN_INDENT_THEN_ELSE */                 false,
      /* IMP_IF_THEN_INDENT_END */                       false,
      /* IMP_IF_THEN_COLLAPSE */                         true,
      /* IMP_LOOP_LOOP_WRAP */                           true,
      /* IMP_LOOP_LOOP_INDENT */                         true,
      /* IMP_LOOP_END_INDENT */                          false,
      /* IMP_LOOP_COLLAPSE */                            false,
      /* CORTEGE_SPACE_BEFORE_L_PAREN */                 false,
      /* CORTEGE_COMMA_1ST */                            false,
      /* CORTEGE_CLOSING */                              AS_IS,
      /* CORTEGE_SPACE_WITHIN_PARENTHESES */             false,
      /* CORTEGE_SPACE_BEFORE_COMMA */                   false,
      /* CORTEGE_SPACE_AFTER_COMMA */                    true,
      /* EXPR_SPACE_AROUND_OPERATOR */                   ADD,
      /* EXPR_SPACE_WITHIN_PARENTHESES */                false,
      /* EXPR_BINARY_OP_ALIGN */                         false,
      /* EXPR_CALL_SPACE_INSIDE_PARENTHESES */           false,
      /* EXPR_CALL_SPACE_BEFORE_COMMA */                 false,
      /* EXPR_CALL_SPACE_AFTER_COMMA */                  true,
      /* EXPR_CASE_WHEN_WRAP */                          false,
      /* EXPR_CASE_WHEN_INDENT */                        true,
      /* EXPR_CASE_THEN_WRAP */                          true,
      /* EXPR_CASE_THEN_ALIGN */                         false,
      /* EXPR_CASE_ELSE_ALIGN_THEN */                    false,
      /* EXPR_CASE_END */                                AS_IS,
      /* EXPR_CASE_KEEP_NL_AFTER_THEN */                 false,
      /* EXPR_CASE_COLLAPSE */                           true,
      /* INDEX_NAME_TEMPLATE */                          "{table}_{columns}_{unique?u:}index",
      /* PRIMARY_KEY_NAME_TEMPLATE */                    "{table}_{columns}_pk",
      /* FOREIGN_KEY_NAME_TEMPLATE */                    "{table}_{ref_table}_{ref_columns}_fk",
      /* SPACES_AROUND_OPERATORS */                      true,
      /* ALIGN_AS_IN_SELECT_STATEMENT */                 true,
      /* ALIGN_TYPE_IN_CREATE_STATEMENT */               true,
      /* ALIGN_TYPE_IN_BLOCK_STATEMENT */                true,
      /* ALIGN_TYPE_IN_ARGUMENT_DEFINITION */            true,
      /* ALIGN_INSIDE_BINARY_EXPRESSION */               true,
      /* ALIGN_INSIDE_QUERY_EXPRESSION */                true,
      /* ALIGN_EQ_INSIDE_SET_CLAUSE */                   true,
      /* NEW_LINE_BEFORE_FROM */                         true,
      /* NEW_LINE_BEFORE_JOIN */                         true,
      /* NEW_LINE_BEFORE_JOIN_CONDITION */               false,
      /* NEW_LINE_BEFORE_WHERE */                        true,
      /* NEW_LINE_BEFORE_GROUP_BY */                     true,
      /* NEW_LINE_BEFORE_ORDER_BY */                     true,
      /* NEW_LINE_BEFORE_HAVING */                       true,
      /* NEW_LINE_BEFORE_THEN */                         true,
      /* NEW_LINE_BEFORE_ELSE */                         true,
      /* NEW_LINE_BEFORE_OTHER_CLAUSES */                true,
      /* NEW_LINE_BEFORE_COMMA */                        false,
      /* NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS */     false,
      /* NEW_LINE_BEFORE_QUERY_INSIDE_DML */             false,
      /* NEW_LINE_AROUND_SEMICOLON */                    false,
      /* INDENT_JOIN */                                  true,
      /* INDENT_JOIN_CONDITION */                        true,
      /* INDENT_SELECT_INTO_CLAUSE */                    false,
      /* WRAP_INSIDE_CREATE_TABLE */                     WRAP_ALWAYS,
      /* WRAP_INSIDE_SELECT */                           WRAP_AS_NEEDED,
      /* WRAP_INSIDE_JOIN_EXPRESSION */                  WRAP_AS_NEEDED,
      /* WRAP_INSIDE_GROUP_BY */                         WRAP_AS_NEEDED,
      /* WRAP_INSIDE_WHERE */                            WRAP_AS_NEEDED,
      /* WRAP_INSIDE_ORDER_BY */                         WRAP_AS_NEEDED,
      /* WRAP_INSIDE_SET */                              WRAP_AS_NEEDED,
      /* WRAP_INSIDE_ARGUMENT_DEFINITION */              WRAP_AS_NEEDED,
      /* WRAP_INSIDE_CALL_EXPRESSION */                  WRAP_AS_NEEDED,
      /* WRAP_INSIDE_VALUES_EXPRESSION */                WRAP_AS_NEEDED,
      /* WRAP_VALUES_EXPRESSION */                       WRAP_AS_NEEDED,
      /* WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES */  WRAP_AS_NEEDED,
      /* NEW_LINE_AFTER_SELECT */                        false,
      /* NEW_LINE_AFTER_SELECT_ITEM */                   true,
      /* NEW_LINE_AFTER_SELECT_2 */                      NEVER
    );

  // @formatter:on


  private SqlCodeStyleSettingsDefault(final int ALIAS_CASE,
                                      final int KEYWORD_CASE,
                                      final int TYPE_CASE,
                                      final int IDENTIFIER_CASE,
                                      final int QUOTED_IDENTIFIER_CASE,
                                      final int QUOTE_IDENTIFIER,
                                      final int QUOTE_TYPE,
                                      final int QUERY_SECTION_1ST_WORD_ALIGN,
                                      final int QUERY_EL_LINE,
                                      final int QUERY_EL_COMMA,
                                      final int QUERY_IN_ONE_STRING,
                                      final boolean QUERY_TRUE_INDENT,
                                      final boolean QUERY_ALIGN_ELEMENTS,
                                      final boolean QUERY_ALIGN_LINE_COMMENTS,
                                      final int SUBQUERY_OPENING,
                                      final int SUBQUERY_CONTENT,
                                      final int SUBQUERY_CLOSING,
                                      final boolean SUBQUERY_L_PAR_NL_OUTSIDE,
                                      final boolean SUBQUERY_L_PAR_NL_INSIDE,
                                      final boolean SUBQUERY_R_PAR_NL_INSIDE,
                                      final int SUBQUERY_R_PAR_ALIGN,
                                      final boolean SUBQUERY_INDENT_INSIDE,
                                      final boolean SUBQUERY_PAR_SPACE_INSIDE,
                                      final int INSERT_INTO_NL,
                                      final int INSERT_OPENING,
                                      final int INSERT_CONTENT,
                                      final int INSERT_CLOSING,
                                      final int INSERT_TABLE_EL_LINE,
                                      final int INSERT_VALUES_EL_LINE,
                                      final int INSERT_EL_WRAP,
                                      final int INSERT_EL_COMMA,
                                      final boolean INSERT_SPACE_WITHIN_PARENTHESES,
                                      final boolean INSERT_COLLAPSE_MULTI_ROW_VALUES,
                                      final int SET_EL_LINE,
                                      final int SET_EL_WRAP,
                                      final int SET_EL_COMMA,
                                      final boolean SET_ALIGN_EQUAL_SIGN,
                                      final int WITH_EL_LINE,
                                      final int WITH_EL_WRAP,
                                      final int WITH_EL_COMMA,
                                      final boolean WITH_ALIGN_AS,
                                      final int SELECT_EL_LINE,
                                      final int SELECT_EL_WRAP,
                                      final int SELECT_EL_COMMA,
                                      final boolean SELECT_NEW_LINE_AFTER_ALL_DISTINCT,
                                      final int SELECT_KEEP_N_ITEMS_IN_LINE,
                                      final int SELECT_USE_AS_WORD,
                                      final boolean SELECT_ALIGN_AS,
                                      final int FROM_EL_LINE,
                                      final int FROM_EL_WRAP,
                                      final int FROM_EL_COMMA,
                                      final boolean FROM_WRAP_JOIN_1,
                                      final boolean FROM_WRAP_JOIN_2,
                                      final boolean FROM_WRAP_ON,
                                      final boolean FROM_ALIGN_JOIN_TABLES,
                                      final boolean FROM_ALIGN_ALIASES,
                                      final boolean FROM_INDENT_JOIN,
                                      final int FROM_ONLY_JOIN_INDENT,
                                      final int FROM_PLACE_ON,
                                      final int WHERE_EL_LINE,
                                      final int WHERE_EL_WRAP,
                                      final int WHERE_EL_BOUND,
                                      final int ORDER_EL_LINE,
                                      final int ORDER_EL_WRAP,
                                      final int ORDER_EL_COMMA,
                                      final boolean ORDER_ALIGN_ASC_DESC,
                                      final int TABLE_OPENING,
                                      final int TABLE_CONTENT,
                                      final int TABLE_CLOSING,
                                      final boolean TABLE_TYPES_ALIGN,
                                      final boolean TABLE_DEFAULTS_ALIGN,
                                      final boolean TABLE_NULLABILITIES_ALIGN,
                                      final boolean TABLE_COLLAPSE,
                                      final int TABLE_ALTER_INSTRUCTION_WRAP,
                                      final boolean TABLE_ALTER_INSTRUCTION_ALIGN,
                                      final boolean CONSTRAINT_WRAP_1,
                                      final boolean CONSTRAINT_WRAP_2,
                                      final boolean CONSTRAINT_WRAP_3,
                                      final boolean CONSTRAINT_WRAP_4,
                                      final boolean POST_OPT_WRAP_1,
                                      final boolean POST_OPT_WRAP_2,
                                      final boolean POST_OPT_INDENT,
                                      final boolean POST_OPT_ALIGN,
                                      final boolean CREATE_SCHEMA_CONTENT_INDENT,
                                      final int CREATE_SCHEMA_BLANK_LINES_MIN,
                                      final int CREATE_SCHEMA_BLANK_LINES_MAX,
                                      final boolean VIEW_WRAP_AS,
                                      final boolean VIEW_WRAP_QUERY,
                                      final boolean VIEW_INDENT_QUERY,
                                      final int ROUTINE_ARG_OPENING,
                                      final int ROUTINE_ARG_CONTENT,
                                      final int ROUTINE_ARG_CLOSING,
                                      final int ROUTINE_ARG_WRAP,
                                      final int ROUTINE_ARG_COMMA,
                                      final boolean ROUTINE_ARG_SPACE_WITHIN_PARENTHESES,
                                      final boolean ROUTINE_ARG_ALIGN_TYPES,
                                      final boolean ROUTINE_AS_WRAP,
                                      final boolean ROUTINE_PG_L_QUOTE_WRAP_BEFORE,
                                      final boolean ROUTINE_PG_L_QUOTE_WRAP_AFTER,
                                      final boolean ROUTINE_PG_R_QUOTE_WRAP_BEFORE,
                                      final boolean ROUTINE_PG_R_QUOTE_WRAP_AFTER,
                                      final boolean IMP_COMMON_WRAP_EVERY_STATEMENT,
                                      final int IMP_COMMON_KEEP_BLANK_LINES_IN_CODE,
                                      final boolean IMP_DECLARE_CONTENT_WRAP,
                                      final int IMP_DECLARE_EL_WRAP,
                                      final boolean IMP_DECLARE_ALIGN_TYPE,
                                      final boolean IMP_DECLARE_ALIGN_EQ,
                                      final boolean IMP_DECLARE_ALIGN_DEFAULT,
                                      final boolean IMP_IF_THEN_WRAP_THEN,
                                      final boolean IMP_IF_THEN_WRAP_ELSE,
                                      final boolean IMP_IF_THEN_WRAP_INNER,
                                      final boolean IMP_IF_THEN_INDENT_THEN_ELSE,
                                      final boolean IMP_IF_THEN_INDENT_END,
                                      final boolean IMP_IF_THEN_COLLAPSE,
                                      final boolean IMP_LOOP_LOOP_WRAP,
                                      final boolean IMP_LOOP_LOOP_INDENT,
                                      final boolean IMP_LOOP_END_INDENT,
                                      final boolean IMP_LOOP_COLLAPSE,
                                      final boolean CORTEGE_SPACE_BEFORE_L_PAREN,
                                      final boolean CORTEGE_COMMA_1ST,
                                      final int CORTEGE_CLOSING,
                                      final boolean CORTEGE_SPACE_WITHIN_PARENTHESES,
                                      final boolean CORTEGE_SPACE_BEFORE_COMMA,
                                      final boolean CORTEGE_SPACE_AFTER_COMMA,
                                      final int EXPR_SPACE_AROUND_OPERATOR,
                                      final boolean EXPR_SPACE_WITHIN_PARENTHESES,
                                      final boolean EXPR_BINARY_OP_ALIGN,
                                      final boolean EXPR_CALL_SPACE_INSIDE_PARENTHESES,
                                      final boolean EXPR_CALL_SPACE_BEFORE_COMMA,
                                      final boolean EXPR_CALL_SPACE_AFTER_COMMA,
                                      final boolean EXPR_CASE_WHEN_WRAP,
                                      final boolean EXPR_CASE_WHEN_INDENT,
                                      final boolean EXPR_CASE_THEN_WRAP,
                                      final boolean EXPR_CASE_THEN_ALIGN,
                                      final boolean EXPR_CASE_ELSE_ALIGN_THEN,
                                      final int EXPR_CASE_END,
                                      final boolean EXPR_CASE_KEEP_NL_AFTER_THEN,
                                      final boolean EXPR_CASE_COLLAPSE,
                                      final String INDEX_NAME_TEMPLATE,
                                      final String PRIMARY_KEY_NAME_TEMPLATE,
                                      final String FOREIGN_KEY_NAME_TEMPLATE,
                                      final boolean SPACES_AROUND_OPERATORS,
                                      final boolean ALIGN_AS_IN_SELECT_STATEMENT,
                                      final boolean ALIGN_TYPE_IN_CREATE_STATEMENT,
                                      final boolean ALIGN_TYPE_IN_BLOCK_STATEMENT,
                                      final boolean ALIGN_TYPE_IN_ARGUMENT_DEFINITION,
                                      final boolean ALIGN_INSIDE_BINARY_EXPRESSION,
                                      final boolean ALIGN_INSIDE_QUERY_EXPRESSION,
                                      final boolean ALIGN_EQ_INSIDE_SET_CLAUSE,
                                      final boolean NEW_LINE_BEFORE_FROM,
                                      final boolean NEW_LINE_BEFORE_JOIN,
                                      final boolean NEW_LINE_BEFORE_JOIN_CONDITION,
                                      final boolean NEW_LINE_BEFORE_WHERE,
                                      final boolean NEW_LINE_BEFORE_GROUP_BY,
                                      final boolean NEW_LINE_BEFORE_ORDER_BY,
                                      final boolean NEW_LINE_BEFORE_HAVING,
                                      final boolean NEW_LINE_BEFORE_THEN,
                                      final boolean NEW_LINE_BEFORE_ELSE,
                                      final boolean NEW_LINE_BEFORE_OTHER_CLAUSES,
                                      final boolean NEW_LINE_BEFORE_COMMA,
                                      final boolean NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS,
                                      final boolean NEW_LINE_BEFORE_QUERY_INSIDE_DML,
                                      final boolean NEW_LINE_AROUND_SEMICOLON,
                                      final boolean INDENT_JOIN,
                                      final boolean INDENT_JOIN_CONDITION,
                                      final boolean INDENT_SELECT_INTO_CLAUSE,
                                      final int WRAP_INSIDE_CREATE_TABLE,
                                      final int WRAP_INSIDE_SELECT,
                                      final int WRAP_INSIDE_JOIN_EXPRESSION,
                                      final int WRAP_INSIDE_GROUP_BY,
                                      final int WRAP_INSIDE_WHERE,
                                      final int WRAP_INSIDE_ORDER_BY,
                                      final int WRAP_INSIDE_SET,
                                      final int WRAP_INSIDE_ARGUMENT_DEFINITION,
                                      final int WRAP_INSIDE_CALL_EXPRESSION,
                                      final int WRAP_INSIDE_VALUES_EXPRESSION,
                                      final int WRAP_VALUES_EXPRESSION,
                                      final int WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES,
                                      final boolean NEW_LINE_AFTER_SELECT,
                                      final boolean NEW_LINE_AFTER_SELECT_ITEM,
                                      final int NEW_LINE_AFTER_SELECT_2) {
    this.ALIAS_CASE = ALIAS_CASE;
    this.KEYWORD_CASE = KEYWORD_CASE;
    this.TYPE_CASE = TYPE_CASE;
    this.IDENTIFIER_CASE = IDENTIFIER_CASE;
    this.QUOTED_IDENTIFIER_CASE = QUOTED_IDENTIFIER_CASE;
    this.QUOTE_IDENTIFIER = QUOTE_IDENTIFIER;
    this.QUOTE_TYPE = QUOTE_TYPE;
    this.QUERY_SECTION_1ST_WORD_ALIGN = QUERY_SECTION_1ST_WORD_ALIGN;
    this.QUERY_EL_LINE = QUERY_EL_LINE;
    this.QUERY_EL_COMMA = QUERY_EL_COMMA;
    this.QUERY_IN_ONE_STRING = QUERY_IN_ONE_STRING;
    this.QUERY_TRUE_INDENT = QUERY_TRUE_INDENT;
    this.QUERY_ALIGN_ELEMENTS = QUERY_ALIGN_ELEMENTS;
    this.QUERY_ALIGN_LINE_COMMENTS = QUERY_ALIGN_LINE_COMMENTS;
    this.SUBQUERY_OPENING = SUBQUERY_OPENING;
    this.SUBQUERY_CONTENT = SUBQUERY_CONTENT;
    this.SUBQUERY_CLOSING = SUBQUERY_CLOSING;
    this.SUBQUERY_L_PAR_NL_OUTSIDE = SUBQUERY_L_PAR_NL_OUTSIDE;
    this.SUBQUERY_L_PAR_NL_INSIDE = SUBQUERY_L_PAR_NL_INSIDE;
    this.SUBQUERY_R_PAR_NL_INSIDE = SUBQUERY_R_PAR_NL_INSIDE;
    this.SUBQUERY_R_PAR_ALIGN = SUBQUERY_R_PAR_ALIGN;
    this.SUBQUERY_INDENT_INSIDE = SUBQUERY_INDENT_INSIDE;
    this.SUBQUERY_PAR_SPACE_INSIDE = SUBQUERY_PAR_SPACE_INSIDE;
    this.INSERT_INTO_NL = INSERT_INTO_NL;
    this.INSERT_OPENING = INSERT_OPENING;
    this.INSERT_CONTENT = INSERT_CONTENT;
    this.INSERT_CLOSING = INSERT_CLOSING;
    this.INSERT_TABLE_EL_LINE = INSERT_TABLE_EL_LINE;
    this.INSERT_VALUES_EL_LINE = INSERT_VALUES_EL_LINE;
    this.INSERT_EL_WRAP = INSERT_EL_WRAP;
    this.INSERT_EL_COMMA = INSERT_EL_COMMA;
    this.INSERT_SPACE_WITHIN_PARENTHESES = INSERT_SPACE_WITHIN_PARENTHESES;
    this.INSERT_COLLAPSE_MULTI_ROW_VALUES = INSERT_COLLAPSE_MULTI_ROW_VALUES;
    this.SET_EL_LINE = SET_EL_LINE;
    this.SET_EL_WRAP = SET_EL_WRAP;
    this.SET_EL_COMMA = SET_EL_COMMA;
    this.SET_ALIGN_EQUAL_SIGN = SET_ALIGN_EQUAL_SIGN;
    this.WITH_EL_LINE = WITH_EL_LINE;
    this.WITH_EL_WRAP = WITH_EL_WRAP;
    this.WITH_EL_COMMA = WITH_EL_COMMA;
    this.WITH_ALIGN_AS = WITH_ALIGN_AS;
    this.SELECT_EL_LINE = SELECT_EL_LINE;
    this.SELECT_EL_WRAP = SELECT_EL_WRAP;
    this.SELECT_EL_COMMA = SELECT_EL_COMMA;
    this.SELECT_NEW_LINE_AFTER_ALL_DISTINCT = SELECT_NEW_LINE_AFTER_ALL_DISTINCT;
    this.SELECT_KEEP_N_ITEMS_IN_LINE = SELECT_KEEP_N_ITEMS_IN_LINE;
    this.SELECT_USE_AS_WORD = SELECT_USE_AS_WORD;
    this.SELECT_ALIGN_AS = SELECT_ALIGN_AS;
    this.FROM_EL_LINE = FROM_EL_LINE;
    this.FROM_EL_WRAP = FROM_EL_WRAP;
    this.FROM_EL_COMMA = FROM_EL_COMMA;
    this.FROM_WRAP_JOIN_1 = FROM_WRAP_JOIN_1;
    this.FROM_WRAP_JOIN_2 = FROM_WRAP_JOIN_2;
    this.FROM_WRAP_ON = FROM_WRAP_ON;
    this.FROM_ALIGN_JOIN_TABLES = FROM_ALIGN_JOIN_TABLES;
    this.FROM_ALIGN_ALIASES = FROM_ALIGN_ALIASES;
    this.FROM_INDENT_JOIN = FROM_INDENT_JOIN;
    this.FROM_ONLY_JOIN_INDENT = FROM_ONLY_JOIN_INDENT;
    this.FROM_PLACE_ON = FROM_PLACE_ON;
    this.WHERE_EL_LINE = WHERE_EL_LINE;
    this.WHERE_EL_WRAP = WHERE_EL_WRAP;
    this.WHERE_EL_BOUND = WHERE_EL_BOUND;
    this.ORDER_EL_LINE = ORDER_EL_LINE;
    this.ORDER_EL_WRAP = ORDER_EL_WRAP;
    this.ORDER_EL_COMMA = ORDER_EL_COMMA;
    this.ORDER_ALIGN_ASC_DESC = ORDER_ALIGN_ASC_DESC;
    this.TABLE_OPENING = TABLE_OPENING;
    this.TABLE_CONTENT = TABLE_CONTENT;
    this.TABLE_CLOSING = TABLE_CLOSING;
    this.TABLE_TYPES_ALIGN = TABLE_TYPES_ALIGN;
    this.TABLE_DEFAULTS_ALIGN = TABLE_DEFAULTS_ALIGN;
    this.TABLE_NULLABILITIES_ALIGN = TABLE_NULLABILITIES_ALIGN;
    this.TABLE_COLLAPSE = TABLE_COLLAPSE;
    this.TABLE_ALTER_INSTRUCTION_WRAP = TABLE_ALTER_INSTRUCTION_WRAP;
    this.TABLE_ALTER_INSTRUCTION_ALIGN = TABLE_ALTER_INSTRUCTION_ALIGN;
    this.CONSTRAINT_WRAP_1 = CONSTRAINT_WRAP_1;
    this.CONSTRAINT_WRAP_2 = CONSTRAINT_WRAP_2;
    this.CONSTRAINT_WRAP_3 = CONSTRAINT_WRAP_3;
    this.CONSTRAINT_WRAP_4 = CONSTRAINT_WRAP_4;
    this.POST_OPT_WRAP_1 = POST_OPT_WRAP_1;
    this.POST_OPT_WRAP_2 = POST_OPT_WRAP_2;
    this.POST_OPT_INDENT = POST_OPT_INDENT;
    this.POST_OPT_ALIGN = POST_OPT_ALIGN;
    this.CREATE_SCHEMA_CONTENT_INDENT = CREATE_SCHEMA_CONTENT_INDENT;
    this.CREATE_SCHEMA_BLANK_LINES_MIN = CREATE_SCHEMA_BLANK_LINES_MIN;
    this.CREATE_SCHEMA_BLANK_LINES_MAX = CREATE_SCHEMA_BLANK_LINES_MAX;
    this.VIEW_WRAP_AS = VIEW_WRAP_AS;
    this.VIEW_WRAP_QUERY = VIEW_WRAP_QUERY;
    this.VIEW_INDENT_QUERY = VIEW_INDENT_QUERY;
    this.ROUTINE_ARG_OPENING = ROUTINE_ARG_OPENING;
    this.ROUTINE_ARG_CONTENT = ROUTINE_ARG_CONTENT;
    this.ROUTINE_ARG_CLOSING = ROUTINE_ARG_CLOSING;
    this.ROUTINE_ARG_WRAP = ROUTINE_ARG_WRAP;
    this.ROUTINE_ARG_COMMA = ROUTINE_ARG_COMMA;
    this.ROUTINE_ARG_SPACE_WITHIN_PARENTHESES = ROUTINE_ARG_SPACE_WITHIN_PARENTHESES;
    this.ROUTINE_ARG_ALIGN_TYPES = ROUTINE_ARG_ALIGN_TYPES;
    this.ROUTINE_AS_WRAP = ROUTINE_AS_WRAP;
    this.ROUTINE_PG_L_QUOTE_WRAP_BEFORE = ROUTINE_PG_L_QUOTE_WRAP_BEFORE;
    this.ROUTINE_PG_L_QUOTE_WRAP_AFTER = ROUTINE_PG_L_QUOTE_WRAP_AFTER;
    this.ROUTINE_PG_R_QUOTE_WRAP_BEFORE = ROUTINE_PG_R_QUOTE_WRAP_BEFORE;
    this.ROUTINE_PG_R_QUOTE_WRAP_AFTER = ROUTINE_PG_R_QUOTE_WRAP_AFTER;
    this.IMP_COMMON_WRAP_EVERY_STATEMENT = IMP_COMMON_WRAP_EVERY_STATEMENT;
    this.IMP_COMMON_KEEP_BLANK_LINES_IN_CODE = IMP_COMMON_KEEP_BLANK_LINES_IN_CODE;
    this.IMP_DECLARE_CONTENT_WRAP = IMP_DECLARE_CONTENT_WRAP;
    this.IMP_DECLARE_EL_WRAP = IMP_DECLARE_EL_WRAP;
    this.IMP_DECLARE_ALIGN_TYPE = IMP_DECLARE_ALIGN_TYPE;
    this.IMP_DECLARE_ALIGN_EQ = IMP_DECLARE_ALIGN_EQ;
    this.IMP_DECLARE_ALIGN_DEFAULT = IMP_DECLARE_ALIGN_DEFAULT;
    this.IMP_IF_THEN_WRAP_THEN = IMP_IF_THEN_WRAP_THEN;
    this.IMP_IF_THEN_WRAP_ELSE = IMP_IF_THEN_WRAP_ELSE;
    this.IMP_IF_THEN_WRAP_INNER = IMP_IF_THEN_WRAP_INNER;
    this.IMP_IF_THEN_INDENT_THEN_ELSE = IMP_IF_THEN_INDENT_THEN_ELSE;
    this.IMP_IF_THEN_INDENT_END = IMP_IF_THEN_INDENT_END;
    this.IMP_IF_THEN_COLLAPSE = IMP_IF_THEN_COLLAPSE;
    this.IMP_LOOP_LOOP_WRAP = IMP_LOOP_LOOP_WRAP;
    this.IMP_LOOP_LOOP_INDENT = IMP_LOOP_LOOP_INDENT;
    this.IMP_LOOP_END_INDENT = IMP_LOOP_END_INDENT;
    this.IMP_LOOP_COLLAPSE = IMP_LOOP_COLLAPSE;
    this.CORTEGE_SPACE_BEFORE_L_PAREN = CORTEGE_SPACE_BEFORE_L_PAREN;
    this.CORTEGE_COMMA_1ST = CORTEGE_COMMA_1ST;
    this.CORTEGE_CLOSING = CORTEGE_CLOSING;
    this.CORTEGE_SPACE_WITHIN_PARENTHESES = CORTEGE_SPACE_WITHIN_PARENTHESES;
    this.CORTEGE_SPACE_BEFORE_COMMA = CORTEGE_SPACE_BEFORE_COMMA;
    this.CORTEGE_SPACE_AFTER_COMMA = CORTEGE_SPACE_AFTER_COMMA;
    this.EXPR_SPACE_AROUND_OPERATOR = EXPR_SPACE_AROUND_OPERATOR;
    this.EXPR_SPACE_WITHIN_PARENTHESES = EXPR_SPACE_WITHIN_PARENTHESES;
    this.EXPR_BINARY_OP_ALIGN = EXPR_BINARY_OP_ALIGN;
    this.EXPR_CALL_SPACE_INSIDE_PARENTHESES = EXPR_CALL_SPACE_INSIDE_PARENTHESES;
    this.EXPR_CALL_SPACE_BEFORE_COMMA = EXPR_CALL_SPACE_BEFORE_COMMA;
    this.EXPR_CALL_SPACE_AFTER_COMMA = EXPR_CALL_SPACE_AFTER_COMMA;
    this.EXPR_CASE_WHEN_WRAP = EXPR_CASE_WHEN_WRAP;
    this.EXPR_CASE_WHEN_INDENT = EXPR_CASE_WHEN_INDENT;
    this.EXPR_CASE_THEN_WRAP = EXPR_CASE_THEN_WRAP;
    this.EXPR_CASE_THEN_ALIGN = EXPR_CASE_THEN_ALIGN;
    this.EXPR_CASE_ELSE_ALIGN_THEN = EXPR_CASE_ELSE_ALIGN_THEN;
    this.EXPR_CASE_END = EXPR_CASE_END;
    this.EXPR_CASE_KEEP_NL_AFTER_THEN = EXPR_CASE_KEEP_NL_AFTER_THEN;
    this.EXPR_CASE_COLLAPSE = EXPR_CASE_COLLAPSE;
    this.INDEX_NAME_TEMPLATE = INDEX_NAME_TEMPLATE;
    this.PRIMARY_KEY_NAME_TEMPLATE = PRIMARY_KEY_NAME_TEMPLATE;
    this.FOREIGN_KEY_NAME_TEMPLATE = FOREIGN_KEY_NAME_TEMPLATE;
    this.SPACES_AROUND_OPERATORS = SPACES_AROUND_OPERATORS;
    this.ALIGN_AS_IN_SELECT_STATEMENT = ALIGN_AS_IN_SELECT_STATEMENT;
    this.ALIGN_TYPE_IN_CREATE_STATEMENT = ALIGN_TYPE_IN_CREATE_STATEMENT;
    this.ALIGN_TYPE_IN_BLOCK_STATEMENT = ALIGN_TYPE_IN_BLOCK_STATEMENT;
    this.ALIGN_TYPE_IN_ARGUMENT_DEFINITION = ALIGN_TYPE_IN_ARGUMENT_DEFINITION;
    this.ALIGN_INSIDE_BINARY_EXPRESSION = ALIGN_INSIDE_BINARY_EXPRESSION;
    this.ALIGN_INSIDE_QUERY_EXPRESSION = ALIGN_INSIDE_QUERY_EXPRESSION;
    this.ALIGN_EQ_INSIDE_SET_CLAUSE = ALIGN_EQ_INSIDE_SET_CLAUSE;
    this.NEW_LINE_BEFORE_FROM = NEW_LINE_BEFORE_FROM;
    this.NEW_LINE_BEFORE_JOIN = NEW_LINE_BEFORE_JOIN;
    this.NEW_LINE_BEFORE_JOIN_CONDITION = NEW_LINE_BEFORE_JOIN_CONDITION;
    this.NEW_LINE_BEFORE_WHERE = NEW_LINE_BEFORE_WHERE;
    this.NEW_LINE_BEFORE_GROUP_BY = NEW_LINE_BEFORE_GROUP_BY;
    this.NEW_LINE_BEFORE_ORDER_BY = NEW_LINE_BEFORE_ORDER_BY;
    this.NEW_LINE_BEFORE_HAVING = NEW_LINE_BEFORE_HAVING;
    this.NEW_LINE_BEFORE_THEN = NEW_LINE_BEFORE_THEN;
    this.NEW_LINE_BEFORE_ELSE = NEW_LINE_BEFORE_ELSE;
    this.NEW_LINE_BEFORE_OTHER_CLAUSES = NEW_LINE_BEFORE_OTHER_CLAUSES;
    this.NEW_LINE_BEFORE_COMMA = NEW_LINE_BEFORE_COMMA;
    this.NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS = NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS;
    this.NEW_LINE_BEFORE_QUERY_INSIDE_DML = NEW_LINE_BEFORE_QUERY_INSIDE_DML;
    this.NEW_LINE_AROUND_SEMICOLON = NEW_LINE_AROUND_SEMICOLON;
    this.INDENT_JOIN = INDENT_JOIN;
    this.INDENT_JOIN_CONDITION = INDENT_JOIN_CONDITION;
    this.INDENT_SELECT_INTO_CLAUSE = INDENT_SELECT_INTO_CLAUSE;
    this.WRAP_INSIDE_CREATE_TABLE = WRAP_INSIDE_CREATE_TABLE;
    this.WRAP_INSIDE_SELECT = WRAP_INSIDE_SELECT;
    this.WRAP_INSIDE_JOIN_EXPRESSION = WRAP_INSIDE_JOIN_EXPRESSION;
    this.WRAP_INSIDE_GROUP_BY = WRAP_INSIDE_GROUP_BY;
    this.WRAP_INSIDE_WHERE = WRAP_INSIDE_WHERE;
    this.WRAP_INSIDE_ORDER_BY = WRAP_INSIDE_ORDER_BY;
    this.WRAP_INSIDE_SET = WRAP_INSIDE_SET;
    this.WRAP_INSIDE_ARGUMENT_DEFINITION = WRAP_INSIDE_ARGUMENT_DEFINITION;
    this.WRAP_INSIDE_CALL_EXPRESSION = WRAP_INSIDE_CALL_EXPRESSION;
    this.WRAP_INSIDE_VALUES_EXPRESSION = WRAP_INSIDE_VALUES_EXPRESSION;
    this.WRAP_VALUES_EXPRESSION = WRAP_VALUES_EXPRESSION;
    this.WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES = WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES;
    this.NEW_LINE_AFTER_SELECT = NEW_LINE_AFTER_SELECT;
    this.NEW_LINE_AFTER_SELECT_ITEM = NEW_LINE_AFTER_SELECT_ITEM;
    this.NEW_LINE_AFTER_SELECT_2 = NEW_LINE_AFTER_SELECT_2;
  }




  /// CASING \\\

  @AliasCase
  public final int ALIAS_CASE;

  @IdentifierCase
  public final int KEYWORD_CASE;

  @IdentifierCaseExt
  public final int TYPE_CASE;

  @IdentifierCase
  public final int IDENTIFIER_CASE;
  @IdentifierCase
  public final int QUOTED_IDENTIFIER_CASE;


  /// IDENTIFIER QUOTATION \\\

  @MagicConstant(intValues = {QUOTE, UNQUOTE, DO_NOT_CHANGE})
  public final int QUOTE_IDENTIFIER;

  @MagicConstant(intValues = {QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_AUTO, QUOTE_DOUBLE_QUOTE_ONLY, QUOTE_BRACKET_AUTO, QUOTE_BRACKET_ONLY, QUOTE_GRAVE_ACCENT_AUTO, QUOTE_GRAVE_ACCENT_ONLY})
  public final int QUOTE_TYPE;

  boolean supportIdentifierQuotationWithBrackets() { return false; }
  boolean supportIdentifierQuotationWithGraveAccent() { return false; }


  /// QUERY & DML COMMON  \\

  @MagicConstant(intValues = {AS_IS, QUERY_SECTION_1ST_WORD_ALIGN_LEFT, QUERY_SECTION_1ST_WORD_ALIGN_LEFT_INDENT, QUERY_SECTION_1ST_WORD_ALIGN_RIGHT})
  public final int QUERY_SECTION_1ST_WORD_ALIGN;

  @ElementsLineMagicValues
  public final int QUERY_EL_LINE;

  @CommaMagicValues
  public final int QUERY_EL_COMMA;

  @MagicConstant(intValues = {AS_IS, QUERY_IN_ONE_STRING_NO, QUERY_IN_ONE_STRING_INNER_ONLY, QUERY_IN_ONE_STRING_YES})
  public final int QUERY_IN_ONE_STRING;

  public boolean QUERY_TRUE_INDENT;
  public boolean QUERY_ALIGN_ELEMENTS;
  public boolean QUERY_ALIGN_LINE_COMMENTS;

  /// SUBQUERY \\\

  @OpeningMagicValues
  public final int SUBQUERY_OPENING;
  @ContentMagicValues
  public final int SUBQUERY_CONTENT;
  @ClosingMagicValues
  public final int SUBQUERY_CLOSING;

  public final boolean SUBQUERY_PAR_SPACE_INSIDE;


  /// SECTIONS INSERT AND VALUES \\\

  @AddRemoveAsIsMagicValues
  public final int INSERT_INTO_NL;
  @OpeningMagicValues
  public int INSERT_OPENING;
  @ContentMagicValues
  public int INSERT_CONTENT;
  @ClosingMagicValues
  public int INSERT_CLOSING;
  @ElementsLineMagicValues
  public int INSERT_TABLE_EL_LINE;
  @ElementsLineMagicValues
  public int INSERT_VALUES_EL_LINE;
  @WrapMagicValues
  public int INSERT_EL_WRAP;
  @CommaMagicValues
  public int INSERT_EL_COMMA;

  public final boolean INSERT_SPACE_WITHIN_PARENTHESES;
  public final boolean INSERT_COLLAPSE_MULTI_ROW_VALUES;


  /// SECTION SET \\\

  @ElementsLineMagicValues
  public final int SET_EL_LINE;

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP})
  public final int SET_EL_WRAP;

  @MagicConstant(intValues = {AS_IS, EL_COMMA_1ST, EL_COMMA_LAST})
  public final int SET_EL_COMMA;

  public final boolean SET_ALIGN_EQUAL_SIGN;



  /// SECTION WITH \\\

  @ElementsLineMagicValues
  public final int WITH_EL_LINE;

  @WrapMagicValues
  public final int WITH_EL_WRAP;

  @CommaPlusMagicValues
  public final int WITH_EL_COMMA;

  public final boolean WITH_ALIGN_AS;


  /// SECTION SELECT \\\

  @ElementsLineMagicValues
  public final int SELECT_EL_LINE;

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP})
  public final int SELECT_EL_WRAP;

  @MagicConstant(intValues = {AS_IS, EL_COMMA_1ST, EL_COMMA_LAST})
  public final int SELECT_EL_COMMA;

  public final boolean SELECT_NEW_LINE_AFTER_ALL_DISTINCT;

  public final int SELECT_KEEP_N_ITEMS_IN_LINE;

  @MagicConstant(intValues = {AS_IS, ADD, REMOVE})
  public final int SELECT_USE_AS_WORD;

  public final boolean SELECT_ALIGN_AS;


  /// SECTION FROM \\\

  @ElementsLineMagicValues
  public final int FROM_EL_LINE;

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG})
  public final int FROM_EL_WRAP;

  @MagicConstant(intValues = {AS_IS, EL_COMMA_1ST, EL_COMMA_LAST})
  public final int FROM_EL_COMMA;

  public final boolean FROM_WRAP_JOIN_1;
  public final boolean FROM_WRAP_JOIN_2;
  public final boolean FROM_WRAP_ON;

  public final boolean FROM_ALIGN_JOIN_TABLES;
  public final boolean FROM_ALIGN_ALIASES;

  public final boolean FROM_INDENT_JOIN;

  @FromOnlyJoinIndentValues
  public final int FROM_ONLY_JOIN_INDENT;

  @FromPlaceOnValues
  public final int FROM_PLACE_ON;


  /// SECTION WHERE \\\

  @ElementsLineMagicValues
  public final int WHERE_EL_LINE;

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP})
  public final int WHERE_EL_WRAP;

  @MagicConstant(intValues = {AS_IS, EL_COMMA_1ST, EL_COMMA_LAST})
  public final int WHERE_EL_BOUND;


  /// SECTIONS GROUP/ORDER \\\

  @ElementsLineMagicValues
  public final int ORDER_EL_LINE;

  @MagicConstant(intValues = {AS_IS, EL_CHOP, EL_CHOP_LONG, EL_WRAP})
  public final int ORDER_EL_WRAP;

  @MagicConstant(intValues = {AS_IS, EL_COMMA_1ST, EL_COMMA_LAST})
  public final int ORDER_EL_COMMA;

  public final boolean ORDER_ALIGN_ASC_DESC;


  /// TABLE \\\

  @OpeningMagicValues
  public int TABLE_OPENING;
  @ContentMagicValues
  public int TABLE_CONTENT;
  @ClosingMagicValues
  public int TABLE_CLOSING;
  public boolean TABLE_TYPES_ALIGN;
  public boolean TABLE_DEFAULTS_ALIGN;
  public boolean TABLE_NULLABILITIES_ALIGN;
  public boolean TABLE_COLLAPSE;

  @AddRemoveAsIsMagicValues
  public int TABLE_ALTER_INSTRUCTION_WRAP;
  public boolean TABLE_ALTER_INSTRUCTION_ALIGN;

  /// CONSTRAINT \\\

  public boolean CONSTRAINT_WRAP_1;
  public boolean CONSTRAINT_WRAP_2;
  public boolean CONSTRAINT_WRAP_3;
  public boolean CONSTRAINT_WRAP_4;

  /// POSTFIX OPTIONS \\\

  public boolean POST_OPT_WRAP_1;
  public boolean POST_OPT_WRAP_2;
  public boolean POST_OPT_INDENT;
  public boolean POST_OPT_ALIGN;

  /// CREATE SCHEMA \\\

  public boolean CREATE_SCHEMA_CONTENT_INDENT;
  public int CREATE_SCHEMA_BLANK_LINES_MIN;
  public int CREATE_SCHEMA_BLANK_LINES_MAX;

  /// VIEW \\\

  public boolean VIEW_WRAP_AS;
  public boolean VIEW_WRAP_QUERY;
  public boolean VIEW_INDENT_QUERY;

  /// ROUTINE \\\

  @OpeningMagicValues
  public int ROUTINE_ARG_OPENING;
  @ContentMagicValues
  public int ROUTINE_ARG_CONTENT;
  @ClosingMagicValues
  public int ROUTINE_ARG_CLOSING;
  @WrapMagicValues
  public int ROUTINE_ARG_WRAP;
  @CommaMagicValues
  public int ROUTINE_ARG_COMMA;

  public boolean ROUTINE_ARG_SPACE_WITHIN_PARENTHESES;
  public boolean ROUTINE_ARG_ALIGN_TYPES;

  public boolean ROUTINE_AS_WRAP;
  public boolean ROUTINE_PG_L_QUOTE_WRAP_BEFORE;
  public boolean ROUTINE_PG_L_QUOTE_WRAP_AFTER;
  public boolean ROUTINE_PG_R_QUOTE_WRAP_BEFORE;
  public boolean ROUTINE_PG_R_QUOTE_WRAP_AFTER;

  /// IMPERATIVE \\\

  public boolean IMP_COMMON_WRAP_EVERY_STATEMENT;

  public int IMP_COMMON_KEEP_BLANK_LINES_IN_CODE;

  public boolean IMP_DECLARE_CONTENT_WRAP;

  @WrapMagicValues
  public int IMP_DECLARE_EL_WRAP;

  public boolean IMP_DECLARE_ALIGN_TYPE;
  public boolean IMP_DECLARE_ALIGN_EQ;
  public boolean IMP_DECLARE_ALIGN_DEFAULT;


  public boolean IMP_IF_THEN_WRAP_THEN;
  public boolean IMP_IF_THEN_WRAP_ELSE;
  public boolean IMP_IF_THEN_WRAP_INNER;
  public boolean IMP_IF_THEN_INDENT_THEN_ELSE;
  public boolean IMP_IF_THEN_INDENT_END;
  public boolean IMP_IF_THEN_COLLAPSE;

  public boolean IMP_LOOP_LOOP_WRAP;
  public boolean IMP_LOOP_LOOP_INDENT;
  public boolean IMP_LOOP_END_INDENT;
  public boolean IMP_LOOP_COLLAPSE;

  /// EXPRESSIONS \\\

  public final boolean CORTEGE_SPACE_BEFORE_L_PAREN;
  public final boolean CORTEGE_COMMA_1ST;

  @MagicConstant(intValues = {AS_IS, CORTEGE_CLOSING_UNDER_OPENING, CORTEGE_CLOSING_UNDER_ITEM, CORTEGE_CLOSING_AT_THE_END})
  public final int CORTEGE_CLOSING;

  public final boolean CORTEGE_SPACE_WITHIN_PARENTHESES;
  public final boolean CORTEGE_SPACE_BEFORE_COMMA;
  public final boolean CORTEGE_SPACE_AFTER_COMMA;

  public final int EXPR_SPACE_AROUND_OPERATOR;
  public final boolean EXPR_SPACE_WITHIN_PARENTHESES;
  public final boolean EXPR_BINARY_OP_ALIGN;

  public boolean EXPR_CALL_SPACE_INSIDE_PARENTHESES;
  public boolean EXPR_CALL_SPACE_BEFORE_COMMA;
  public boolean EXPR_CALL_SPACE_AFTER_COMMA;

  public final boolean EXPR_CASE_WHEN_WRAP;
  public final boolean EXPR_CASE_WHEN_INDENT;
  public final boolean EXPR_CASE_THEN_WRAP;
  public final boolean EXPR_CASE_THEN_ALIGN;
  public final boolean EXPR_CASE_ELSE_ALIGN_THEN;

  @MagicConstant(intValues = {AS_IS, EXPR_CASE_END_ALIGN_CASE, EXPR_CASE_END_ALIGN_WHEN, EXPR_CASE_END_TO_THE_END})
  public final int EXPR_CASE_END;

  public final boolean EXPR_CASE_KEEP_NL_AFTER_THEN;
  public final boolean EXPR_CASE_COLLAPSE;


  /// OTHER \\\

  public final String INDEX_NAME_TEMPLATE;
  public final String PRIMARY_KEY_NAME_TEMPLATE;
  public final String FOREIGN_KEY_NAME_TEMPLATE;


  /// DEPRECATED \\\

  @Deprecated public boolean SUBQUERY_L_PAR_NL_OUTSIDE;
  @Deprecated public boolean SUBQUERY_L_PAR_NL_INSIDE;
  @Deprecated public boolean SUBQUERY_R_PAR_NL_INSIDE;

  @Deprecated public int SUBQUERY_R_PAR_ALIGN;

  @Deprecated public boolean SUBQUERY_INDENT_INSIDE;
  @Deprecated public final boolean SPACES_AROUND_OPERATORS;

  @Deprecated public final boolean ALIGN_AS_IN_SELECT_STATEMENT;
  @Deprecated public final boolean ALIGN_TYPE_IN_CREATE_STATEMENT;
  @Deprecated public final boolean ALIGN_TYPE_IN_BLOCK_STATEMENT;
  @Deprecated public final boolean ALIGN_TYPE_IN_ARGUMENT_DEFINITION;
  @Deprecated public final boolean ALIGN_INSIDE_BINARY_EXPRESSION;
  @Deprecated public final boolean ALIGN_INSIDE_QUERY_EXPRESSION;
  @Deprecated public final boolean ALIGN_EQ_INSIDE_SET_CLAUSE;

  @Deprecated public final boolean NEW_LINE_BEFORE_FROM;
  @Deprecated public final boolean NEW_LINE_BEFORE_JOIN;
  @Deprecated public final boolean NEW_LINE_BEFORE_JOIN_CONDITION;
  @Deprecated public final boolean NEW_LINE_BEFORE_WHERE;
  @Deprecated public final boolean NEW_LINE_BEFORE_GROUP_BY;
  @Deprecated public final boolean NEW_LINE_BEFORE_ORDER_BY;
  @Deprecated public final boolean NEW_LINE_BEFORE_HAVING;
  @Deprecated public final boolean NEW_LINE_BEFORE_THEN;
  @Deprecated public final boolean NEW_LINE_BEFORE_ELSE;
  @Deprecated public final boolean NEW_LINE_BEFORE_OTHER_CLAUSES;
  @Deprecated public final boolean NEW_LINE_BEFORE_COMMA;
  @Deprecated public final boolean NEW_LINE_BEFORE_QUERY_INSIDE_PARENTHESIS;
  @Deprecated public final boolean NEW_LINE_BEFORE_QUERY_INSIDE_DML;
  @Deprecated public final boolean NEW_LINE_AROUND_SEMICOLON;

  @Deprecated public final boolean INDENT_JOIN;
  @Deprecated public final boolean INDENT_JOIN_CONDITION;
  @Deprecated public final boolean INDENT_SELECT_INTO_CLAUSE;

  @Deprecated public final int WRAP_INSIDE_CREATE_TABLE;
  @Deprecated public final int WRAP_INSIDE_SELECT;
  @Deprecated public final int WRAP_INSIDE_JOIN_EXPRESSION;
  @Deprecated public final int WRAP_INSIDE_GROUP_BY;
  @Deprecated public final int WRAP_INSIDE_WHERE;
  @Deprecated public final int WRAP_INSIDE_ORDER_BY;
  @Deprecated public final int WRAP_INSIDE_SET;
  @Deprecated public final int WRAP_INSIDE_ARGUMENT_DEFINITION;
  @Deprecated public final int WRAP_INSIDE_CALL_EXPRESSION;
  @Deprecated public final int WRAP_INSIDE_VALUES_EXPRESSION;
  @Deprecated public final int WRAP_VALUES_EXPRESSION;
  @Deprecated public final int WRAP_PARENTHESIZED_EXPRESSION_INSIDE_VALUES;

  @Deprecated public final boolean NEW_LINE_AFTER_SELECT;
  @Deprecated public final boolean NEW_LINE_AFTER_SELECT_ITEM;

  @Deprecated public final int NEW_LINE_AFTER_SELECT_2;


}
