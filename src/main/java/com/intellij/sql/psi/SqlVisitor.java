/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package com.intellij.sql.psi;

/**
 * @author Gregory.Shrago
 */
public class SqlVisitor {
  public void visitSqlUpdateStatement(final SqlUpdateStatement o) {
    visitSqlDmlStatement(o);
  }

  public void visitSqlUnaryExpression(final SqlUnaryExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlSelectStatement(final SqlSelectStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlExplicitTableExpression(final SqlExplicitTableExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlValuesExpression(final SqlValuesExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlParenthesizedExpression(final SqlParenthesizedExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlConstraintDefinition(final SqlConstraintDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlReferenceList(final SqlReferenceList o) {
    visitSqlElement(o);
  }

  public void visitSqlWhereClause(final SqlWhereClause o) {
    visitSqlClause(o);
  }

  public void visitSqlExceptionClause(final SqlExceptionClause o) {
    visitSqlClause(o);
  }

  public void visitSqlUsingClause(final SqlUsingClause o) {
    visitSqlClause(o);
  }

  public void visitSqlUpdatabilityClause(final SqlUpdatabilityClause o) {
    visitSqlClause(o);
  }

  public void visitSqlCaseExpression(final SqlCaseExpression o) {
    visitSqlExpression(o);
  }
  public void visitSqlTypeElement(final SqlTypeElement o) {
    visitSqlElement(o);
  }

  public void visitSqlDmlInstruction(final SqlDmlInstruction o) {
    visitSqlElement(o);
  }

  public void visitSqlDmlStatement(final SqlDmlStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlInsertStatement(final SqlInsertStatement o) {
    visitSqlDmlStatement(o);
  }

  public void visitSqlMergeStatement(final SqlMergeStatement o) {
    visitSqlDmlStatement(o);
  }

  public void visitSqlFromClause(final SqlFromClause o) {
    visitSqlClause(o);
  }

  public void visitSqlCreateAssertionStatement(final SqlCreateAssertionStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlDeleteStatement(final SqlDeleteStatement o) {
    visitSqlDmlStatement(o);
  }

  public void visitSqlRevokeStatement(final SqlRevokeStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlFunctionCallExpression(final SqlFunctionCallExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlHavingClause(final SqlHavingClause o) {
    visitSqlClause(o);
  }

  public void visitSqlIntervalLiteralExpression(final SqlIntervalLiteralExpression o) {
    visitSqlLiteralExpression(o);
  }

  public void visitSqlSpecialLiteralExpression(final SqlSpecialLiteralExpression o) {
    visitSqlLiteralExpression(o);
  }

  public void visitSqlLiteralExpression(final SqlLiteralExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlDropStatement(final SqlDropStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlBinaryExpression(final SqlBinaryExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlQueryExpression(final SqlQueryExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlIntersectExpression(final SqlIntersectExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlGroupByClause(final SqlGroupByClause o) {
    visitSqlClause(o);
  }

  public void visitSqlUnionExpression(final SqlUnionExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlParameter(final SqlParameter o) {
    visitSqlExpression(o);
  }

  public void visitSqlExpression(final SqlExpression o) {
    visitSqlElement(o);
  }

  public void visitSqlReferenceExpression(final SqlReferenceExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlStringLiteralExpression(final SqlStringLiteralExpression o) {
    visitSqlLiteralExpression(o);
  }

  public void visitSqlWhenClause(final SqlWhenClause o) {
    visitSqlClause(o);
  }

  public void visitSqlThenClause(final SqlThenClause o) {
    visitSqlClause(o);
  }

  public void visitSqlElseIfClause(final SqlElseIfClause o) {
    visitSqlClause(o);
  }

  public void visitSqlElseClause(final SqlElseClause o) {
    visitSqlClause(o);
  }

  public void visitSqlWhenThenClause(final SqlWhenThenClause o) {
    visitSqlClause(o);
  }

  public void visitSqlSelectClause(final SqlSelectClause o) {
    visitSqlClause(o);
  }

  public void visitSqlWithClause(final SqlWithClause o) {
    visitSqlClause(o);
  }

  public void visitSqlSelectIntoClause(final SqlSelectIntoClause o) {
    visitSqlClause(o);
  }

  public void visitSqlReferencesConstraintDefinition(final SqlReferencesConstraintDefinition o) {
    visitSqlConstraintDefinition(o);
  }

  public void visitSqlExpressionList(final SqlExpressionList o) {
    visitSqlElement(o);
  }

  public void visitSqlOrderByClause(final SqlOrderByClause o) {
    visitSqlClause(o);
  }

  public void visitSqlCreateTableStatement(final SqlCreateTableStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateProcedureStatement(final SqlCreateProcedureStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateIndexStatement(final SqlCreateIndexStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateTriggerStatement(final SqlCreateTriggerStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlSetClause(final SqlSetClause o) {
    visitSqlClause(o);
  }

  public void visitSqlSetAssignment(final SqlSetAssignment o) {
    visitSqlClause(o);
  }

  public void visitSqlIfStatement(final SqlIfStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlCaseStatement(final SqlCaseStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlJumpStatement(final SqlJumpStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlConditionalJumpStatement(final SqlConditionalJumpStatement o) {
    visitSqlJumpStatement(o);
  }

  public void visitSqlGotoStatement(final SqlGotoStatement o) {
    visitSqlJumpStatement(o);
  }

  public void visitSqlExitStatement(final SqlBreakStatement o) {
    visitSqlConditionalJumpStatement(o);
  }

  public void visitSqlContinueStatement(final SqlContinueStatement o) {
    visitSqlConditionalJumpStatement(o);
  }

  public void visitSqlLoopStatement(final SqlLoopStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlForLoopStatement(final SqlForLoopStatement o) {
    visitSqlLoopStatement(o);
  }

  public void visitSqlConditionalLoopStatement(final SqlConditionalLoopStatement o) {
    visitSqlLoopStatement(o);
  }

  public void visitSqlWhileLoopStatement(final SqlWhileLoopStatement o) {
    visitSqlConditionalLoopStatement(o);
  }

  public void visitSqlRepeatLoopStatement(final SqlRepeatLoopStatement o) {
    visitSqlConditionalLoopStatement(o);
  }

  public void visitSqlReturnStatement(final SqlReturnStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlRaiseStatement(final SqlRaiseStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlNullStatement(final SqlNullStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlBlockStatement(final SqlBlockStatement o) {
    visitSqlStatement(o);}

  public void visitSqlSetStatement(final SqlSetStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlDeclareConditionHandlerStatement(final SqlDeclareConditionHandlerStatement o) {
    visitSqlDeclareStatement(o);
  }

  public void visitDeclareVariableStatement(final SqlDeclareVariableStatement o) {
    visitSqlDeclareStatement(o);
  }

  public void visitSqlDeclareStatement(final SqlDeclareStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlStatement(final SqlStatement o) {
    visitSqlElement(o);
  }

  public void visitSqlBatchBlock(final SqlBatchBlock o) {
    visitSqlElement(o);
  }

  public void visitSqlClause(final SqlClause o) {
    visitSqlElement(o);
  }

  public void visitSqlCorrespondingClause(final SqlCorrespondingClause o) {
    visitSqlClause(o);
  }

  public void visitSqlGrantStatement(final SqlGrantStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlJoinExpression(final SqlJoinExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlAsExpression(final SqlAsExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlTableExpression(final SqlTableExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlAlterDomainStatement(final SqlAlterDomainStatement o) {
    visitSqlAlterStatement(o);
  }

  public void visitSqlElement(final SqlElement o) {
  }

  public void visitSqlIdentifier(final SqlIdentifier o) {
    visitSqlElement(o);
  }

  public void visitSqlFile(final SqlFile o) {
    visitSqlElement(o);
  }

  public void visitSqlColumnDefinition(final SqlColumnDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlIndexDefinition(final SqlIndexDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlCreateCatalogStatement(final SqlCreateCatalogStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateSchemaStatement(final SqlCreateSchemaStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateCollationStatement(final SqlCreateCollationStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateViewStatement(final SqlCreateViewStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateTranslationStatement(final SqlCreateTranslationStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateCharacterSetStatement(final SqlCreateCharacterSetStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlCreateDomainStatement(final SqlCreateDomainStatement o) {
    visitSqlCreateStatement(o);
  }

  public void visitSqlRoutineDefinition(final SqlRoutineDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlDefinition(final SqlDefinition o) {
    visitSqlElement(o);
  }

  public void visitSqlCreateStatement(final SqlCreateStatement o) {
    visitSqlStatement(o);
// visitSqlDefinition(o);
  }

  public void visitSqlTimeLiteralExpression(final SqlTimeLiteralExpression o) {
    visitSqlLiteralExpression(o);
  }

  public void visitSqlAlterStatement(final SqlAlterStatement o) {
    visitSqlStatement(o);
  }

  public void visitSqlAlterTableInstruction(final SqlAlterInstruction o) {
    visitSqlElement(o);
  }

  public void visitSqlTableColumnList(final SqlTableColumnsList o) {
    visitSqlElement(o);
  }

  public void visitSqlBetweenExpression(final SqlBetweenExpression o) {
    visitSqlExpression(o);
  }

  public void visitSqlRenameClause(SqlRenameToClause o) {
    visitSqlClause(o);
  }

  public void visitSqlSynonymDefinition(SqlSynonymDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlVariableDefinition(final SqlVariableDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlLabelDefinition(final SqlLabelDefinition o) {
    visitSqlDefinition(o);
  }

  public void visitSqlParameterDefinition(final SqlParameterDefinition o) {
    visitSqlVariableDefinition(o);
  }
}
