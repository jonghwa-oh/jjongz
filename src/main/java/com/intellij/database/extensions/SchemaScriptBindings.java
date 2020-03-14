package com.intellij.database.extensions;

import com.intellij.database.model.DasObject;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.JBIterable;

public class SchemaScriptBindings {

  public static final Binding<Project>                 PROJECT = new Binding<>("PROJECT");
  public static final Binding<JBIterable<DasObject>> SELECTION = new Binding<>("SELECTION");
  public static final Binding<Clipboard>             CLIPBOARD = new Binding<>("CLIPBOARD");
  public static final Binding<Logger>                      LOG = new Binding<>("LOG");
  public static final Binding<Files>                     FILES = new Binding<>("FILES");

  private SchemaScriptBindings() {
  }
}
