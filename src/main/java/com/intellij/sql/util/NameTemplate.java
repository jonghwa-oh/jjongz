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
package com.intellij.sql.util;

import com.intellij.database.model.*;
import com.intellij.database.util.DasUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.sql.formatter.settings.SqlCodeStyleSettings;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.JBIterable;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.regex.Pattern;

public abstract class NameTemplate implements Cloneable {
  public transient Placeholder[] allowed;
  public String template;

  public static class Placeholder {
    public final String text;
    public final String desc;
    public final String example;

    public Placeholder(@NotNull String text, @NotNull String desc, @NotNull String example) {
      this.text = text;
      this.desc = desc;
      this.example = example;
    }

    @NotNull
    public String substitute(@NotNull String s, @NotNull String val) {
      return s.replace(text, val);
    }
  }
  public static class RegexPlaceholder extends Placeholder {
    private final Pattern myPattern;

    public RegexPlaceholder(@NotNull String text, @NotNull @RegExp String regex, @NotNull String desc, @NotNull String example) {
      super(text, desc, example);
      myPattern = Pattern.compile(regex);
    }

    @NotNull
    @Override
    public String substitute(@NotNull String s, @NotNull String val) {
      return myPattern.matcher(s).replaceAll(val);
    }
  }
  public static class SubstitutionBuilder {
    private String myResult;

    public SubstitutionBuilder(@NotNull String template) {
      myResult = template;
    }

    public SubstitutionBuilder substitute(@NotNull Placeholder p, @NotNull String val) {
      myResult = p.substitute(myResult, val);
      return this;
    }

    @NotNull
    public String build() {
      return myResult;
    }
  }

  public static final Placeholder TABLE = new Placeholder("{table}", "table name", "table1");
  public static final Placeholder COLUMNS = new Placeholder("{columns}", "columns names", "col1_col2");
  public static final Placeholder REF_TABLE = new Placeholder("{ref_table}", "referenced table name", "rtable2");
  public static final Placeholder REF_COLUMNS = new Placeholder("{ref_columns}", "referenced columns names", "rcol1_rcol2");
  public static final Placeholder UNIQUE = new RegexPlaceholder("{unique?*:*}", "\\{unique\\?([^:}]*):([^:}]*)\\}", "uniqueness infix", "$1");

  public NameTemplate(@NotNull String template, @NotNull Placeholder... allowed) {
    this.allowed = allowed;
    this.template = template;
  }

  @NotNull
  public static String example(@NotNull String template, @NotNull Placeholder... allowed) {
    SubstitutionBuilder builder = new SubstitutionBuilder(template);
    for (Placeholder placeholder : allowed) {
      builder.substitute(placeholder, placeholder.example);
    }
    return builder.build();
  }

  @NotNull
  public abstract String getName(@NotNull DasObject object);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NameTemplate template1 = (NameTemplate)o;

    // Probably incorrect - comparing Object[] arrays with Arrays.equals
    if (!Arrays.equals(allowed, template1.allowed)) return false;
    if (!template.equals(template1.template)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(allowed);
    result = 31 * result + template.hashCode();
    return result;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    NameTemplate clone = (NameTemplate)super.clone();
    clone.allowed = allowed;
    clone.template = template;
    return clone;
  }

  public static class ForeignKeyNameTemplate extends NameTemplate {
    public final static String DEFAULT = "{table}_{ref_table}_{ref_columns}_fk";
    public ForeignKeyNameTemplate() {
      this(DEFAULT);
    }
    public ForeignKeyNameTemplate(@NotNull String template) {
      super(template, TABLE, COLUMNS, REF_TABLE, REF_COLUMNS);
    }
    public ForeignKeyNameTemplate(@NotNull SqlCodeStyleSettings settings) {
      this(settings.FOREIGN_KEY_NAME_TEMPLATE);
    }

    @NotNull
    public String getName(@NotNull String tableName, @NotNull Iterable<String> columnNames,
                          @NotNull String targetName, @NotNull Iterable<String> targetColumnNames) {
      return new SubstitutionBuilder(template)
        .substitute(TABLE, tableName)
        .substitute(COLUMNS, StringUtil.join(columnNames, "_"))
        .substitute(REF_TABLE, targetName)
        .substitute(REF_COLUMNS, StringUtil.join(targetColumnNames, "_"))
        .build()
        ;
    }

    @NotNull
    public String getName(@NotNull DasTable table, @NotNull Iterable<? extends DasTypedObject> columns,
                          @Nullable DasTable target, @NotNull Iterable<? extends DasTypedObject> targetColumns) {
      return getName(table.getName(), JBIterable.from(columns).transform(DasUtil.TO_NAME)
        , target == null ? "" : target.getName(), JBIterable.from(targetColumns).transform(DasUtil.TO_NAME));
    }

    @NotNull
    public String getName(@NotNull DasTable table, @NotNull Iterable<? extends DasTypedObject> columns) {
      return getName(table, columns, null, JBIterable.empty());
    }

    @NotNull
    @Override
    public String getName(@NotNull DasObject object) {
      DasForeignKey key = ObjectUtils.tryCast(object, DasForeignKey.class);
      return key == null ? object.getName() : getName(key.getTable(), key.getColumnsRef().resolveObjects(),
                                                      key.getRefTable(), key.getRefColumns().resolveObjects());
    }
  }

  public static class IndexNameTemplate extends NameTemplate {
    public final static String DEFAULT = "{table}_{columns}_{unique?u:}index";
    public IndexNameTemplate() {
      this(DEFAULT);
    }

    public IndexNameTemplate(@NotNull String template) {
      super(template, TABLE, COLUMNS, UNIQUE);
    }

    public IndexNameTemplate(@NotNull SqlCodeStyleSettings settings) {
      this(settings.INDEX_NAME_TEMPLATE);
    }
    @NotNull
    public String getName(@NotNull String tableName, @NotNull Iterable<String> columnNames, boolean unique) {
      return new SubstitutionBuilder(template)
        .substitute(TABLE, tableName)
        .substitute(COLUMNS, StringUtil.join(columnNames, "_"))
        .substitute(UNIQUE, unique ? "$1" : "$2")
        .build()
        ;
    }

    @NotNull
    public String getName(@NotNull DasTable table, @NotNull Iterable<? extends DasTypedObject> columns, boolean unique) {
      return getName(table.getName(), JBIterable.from(columns).transform(DasUtil.TO_NAME), unique);
    }

    @NotNull
    @Override
    public String getName(@NotNull DasObject object) {
      DasIndex index = ObjectUtils.tryCast(object, DasIndex.class);
      return index == null ? object.getName() : getName(index.getTable(), index.getColumnsRef().resolveObjects(), index.isUnique());
    }
  }

  public static class PrimaryKeyNameTemplate extends NameTemplate {
    public final static String DEFAULT = "{table}_{columns}_pk";
    public PrimaryKeyNameTemplate() {
      this(DEFAULT);
    }

    public PrimaryKeyNameTemplate(@NotNull String template) {
      super(template, TABLE, COLUMNS);
    }
    public PrimaryKeyNameTemplate(@NotNull SqlCodeStyleSettings settings) {
      this(settings.PRIMARY_KEY_NAME_TEMPLATE);
    }

    @NotNull
    public String getName(@NotNull String tableName, @NotNull Iterable<String> columnNames) {
      return new SubstitutionBuilder(template)
        .substitute(TABLE, tableName)
        .substitute(COLUMNS, StringUtil.join(columnNames, "_"))
        .build()
        ;
    }

    @NotNull
    public String getName(@NotNull DasTable table, @NotNull Iterable<? extends DasTypedObject> columns) {
      return getName(table.getName(), JBIterable.from(columns).transform(DasUtil.TO_NAME));
    }

    @NotNull
    @Override
    public String getName(@NotNull DasObject object) {
      DasTableKey key = ObjectUtils.tryCast(object, DasTableKey.class);
      return key == null ? object.getName() : getName(key.getTable(), key.getColumnsRef().resolveObjects());
    }
  }
}
