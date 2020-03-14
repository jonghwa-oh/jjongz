package com.intellij.database.script;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Gregory.Shrago
 */
public abstract class PersistenceConsoleProvider {
  public static final ExtensionPointName<PersistenceConsoleProvider> EP_NAME = ExtensionPointName.create("com.intellij.database.consoleProvider");

  public abstract boolean hasRunners(@NotNull DataContext dataContext);

  public abstract boolean hasRunners(@NotNull final PsiElement psiElement, @Nullable Editor editor);

  @NotNull
  public abstract List<Runner> getRunners(@NotNull final DataContext dataContext);

  @NotNull
  public abstract List<Runner> getRunners(@NotNull final PsiElement psiElement, @Nullable Editor editor);

  public abstract static class Runner implements Runnable {

    public abstract String getDisplayName();

    public abstract Icon getIcon();

    @Nullable
    public Color getColor() {
      return null;
    }

    public boolean isAlreadyRunning() {
      return false;
    }

    @Nullable
    public String getSubRunnersTitle() {
      return null;
    }

    @NotNull
    public List<Runner> getSubRunners() {
      return Collections.emptyList();
    }

    public boolean isDefaultSubRunner() {
      return false;
    }
  }
}
