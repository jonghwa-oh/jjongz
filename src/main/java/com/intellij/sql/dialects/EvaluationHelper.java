package com.intellij.sql.dialects;

import com.intellij.database.DbmsExtension;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.SyntaxTraverser;
import com.intellij.sql.psi.SqlElement;
import com.intellij.sql.script.Script;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Liudmila Kornilova
 */
public interface EvaluationHelper<E> {
  DbmsExtension<EvaluationHelper<?>> EP = new DbmsExtension<>("com.intellij.sql.evaluationHelper");

  String getQuery(@NotNull String text, @NotNull SqlElement sqlElement);

  Condition<E> isStatement();

  Condition<E> isFile();

  SyntaxTraverser<E> statements(final Script<E> script, final SyntaxTraverser<E> s);

  JBIterable<E> parameters(Script<E> script, final SyntaxTraverser<E> s);

  @NotNull
  SyntaxTraverser<E> parse(@NotNull Project project, @NotNull Language dialect, @NotNull CharSequence documentText, @Nullable Language hostLanguage);
}
