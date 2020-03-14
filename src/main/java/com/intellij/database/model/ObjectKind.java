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
package com.intellij.database.model;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

/**
 * Kind o database object.
 * @author Leonid Bushuev from JetBrains
 */
public class ObjectKind implements Comparable<ObjectKind> {
  private static final AtomicInteger ourOrderNumCounter = new AtomicInteger(0);
  public static final Map<String, ObjectKind> ourKinds = ContainerUtil.newConcurrentMap();

  public static final ObjectKind NONE                 = new ObjectKind("NONE");
  public static final ObjectKind ROOT                 = new ObjectKind("ROOT");
  public static final ObjectKind DATABASE             = new ObjectKind("DATABASE");
  public static final ObjectKind SCHEMA               = new ObjectKind("SCHEMA");
  public static final ObjectKind SEQUENCE             = new ObjectKind("SEQUENCE");
  public static final ObjectKind CLUSTER              = new ObjectKind("CLUSTER");
  public static final ObjectKind OBJECT_TYPE          = new ObjectKind("OBJECT TYPE");
  public static final ObjectKind COLLECTION_TYPE      = new ObjectKind("COLLECTION TYPE");
  public static final ObjectKind TABLE_TYPE           = new ObjectKind("TABLE TYPE");
  public static final ObjectKind ALIAS_TYPE           = new ObjectKind("ALIAS TYPE");
  public static final ObjectKind TABLE                = new ObjectKind("TABLE");
  public static final ObjectKind MAT_LOG              = new ObjectKind("MATERIALIZED LOG");
  public static final ObjectKind MAT_VIEW             = new ObjectKind("MATERIALIZED VIEW");
  public static final ObjectKind VIEW                 = new ObjectKind("VIEW");
  public static final ObjectKind PACKAGE              = new ObjectKind("PACKAGE");
  public static final ObjectKind BODY                 = new ObjectKind("BODY");
  public static final ObjectKind ROUTINE              = new ObjectKind("ROUTINE");
  public static final ObjectKind METHOD               = new ObjectKind("METHOD");
  public static final ObjectKind OPERATOR             = new ObjectKind("OPERATOR");
  public static final ObjectKind OBJECT_ATTRIBUTE     = new ObjectKind("OBJECT ATTRIBUTE");
  public static final ObjectKind COLUMN               = new ObjectKind("COLUMN");
  public static final ObjectKind INDEX                = new ObjectKind("INDEX");
  public static final ObjectKind KEY                  = new ObjectKind("KEY");
  public static final ObjectKind FOREIGN_KEY          = new ObjectKind("FOREIGN KEY");
  public static final ObjectKind CHECK                = new ObjectKind("CHECK");
  public static final ObjectKind DEFAULT              = new ObjectKind("DEFAULT");
  public static final ObjectKind RULE                 = new ObjectKind("RULE");
  public static final ObjectKind TRIGGER              = new ObjectKind("TRIGGER");
  public static final ObjectKind ARGUMENT             = new ObjectKind("ARGUMENT");
  public static final ObjectKind VARIABLE             = new ObjectKind("VARIABLE");
  public static final ObjectKind COMMENT              = new ObjectKind("COMMENT");
  public static final ObjectKind SYNONYM              = new ObjectKind("SYNONYM");
  public static final ObjectKind DB_LINK              = new ObjectKind("DBLINK");
  public static final ObjectKind VIRTUAL_TABLE        = new ObjectKind("VIRTUAL TABLE");
  public static final ObjectKind COLLATION            = new ObjectKind("COLLATION");
  public static final ObjectKind SCRIPT               = new ObjectKind("SCRIPT");
  public static final ObjectKind TABLESPACE           = new ObjectKind("TABLESPACE");
  public static final ObjectKind DATA_FILE            = new ObjectKind("DATA FILE");
  public static final ObjectKind ROLE                 = new ObjectKind("ROLE");
  public static final ObjectKind USER                 = new ObjectKind("USER");
  public static final ObjectKind CONNECTION           = new ObjectKind("CONNECTION");
  public static final ObjectKind FOREIGN_DATA_WRAPPER = new ObjectKind("FOREIGN DATA WRAPPER");
  public static final ObjectKind SERVER               = new ObjectKind("SERVER");
  public static final ObjectKind USER_MAPPING         = new ObjectKind("USER MAPPING");
  public static final ObjectKind FOREIGN_TABLE        = new ObjectKind("FOREIGN TABLE");
  public static final ObjectKind EXTERNAL_SCHEMA      = new ObjectKind("EXTERNAL SCHEMA");
  public static final ObjectKind SCHEDULED_EVENT      = new ObjectKind("SCHEDULED EVENT");
  public static final ObjectKind ACCESS_METHOD        = new ObjectKind("ACCESS METHOD");
  public static final ObjectKind AGGREGATE            = new ObjectKind("AGGREGATE");
  public static final ObjectKind EXCEPTION            = new ObjectKind("EXCEPTION");
  public static final ObjectKind EXTENSION            = new ObjectKind("EXTENSION");
  public static final ObjectKind PROJECTION           = new ObjectKind("PROJECTION");
  public static final ObjectKind MACRO                = new ObjectKind("MACRO");
  public static final ObjectKind PARTITION            = new ObjectKind("PARTITION");
  public static final ObjectKind WAREHOUSE            = new ObjectKind("WAREHOUSE");
  public static final ObjectKind FORMAT               = new ObjectKind("FORMAT");
  public static final ObjectKind INDEX_EXTENSION      = new ObjectKind("INDEX EXTENSION");
  public static final ObjectKind INDEX_SEARCH_METHOD  = new ObjectKind("SEARCH METHOD");
  public static final ObjectKind CONSTANT             = new ObjectKind("CONSTANT");
  public static final ObjectKind PERIOD               = new ObjectKind("PERIOD");

  public static final ObjectKind UNKNOWN_OBJECT       = new ObjectKind("UNKNOWN OBJECT" , Integer.MAX_VALUE);

  private static final int ourLastDatabaseKind = ourOrderNumCounter.get();

  private final String myName;
  private final int myOrderNum;

  private final String myCode;

  public ObjectKind(@NotNull String name) {
    this(name, ourOrderNumCounter.getAndIncrement());
  }

  private ObjectKind(@NotNull String name, int orderNum) {
    assert name.length() > 0;
    assert orderNum >= 0;
    myName = name;
    myCode = StringUtil.toLowerCase(myName).replace(' ', '-');
    myOrderNum = orderNum;
    ourKinds.putIfAbsent(myCode, this);
  }

  public String name() {
    return myName;
  }

  /**
   * Returns the formal code, that can be used to write it into a file.
   * This code also XML-friendly, starts with a letter and can contain
   * letters, digits and dashes.
   * @return the formal code.
   */
  public String code() {
    return myCode;
  }

  /**
   * Returns the order number influences on order of this kind of elements in the script.
   * @return non-negative integer order number.
   */
  public int getOrder() {
    return myOrderNum;
  }

  @Override
  public String toString() {
    return code();
  }

  @Override
  public int compareTo(@NotNull ObjectKind that) {
    if (this == that) return 0;
    if (this.myOrderNum < that.myOrderNum) return -1;
    if (this.myOrderNum > that.myOrderNum) return +1;
    throw new IllegalStateException(format("Uncomparable object kinds: %s and %s", this.code(), that.code()));
  }

  @NotNull
  public static JBIterable<ObjectKind> getDatabaseKinds() {
    return JBIterable.from(ourKinds.values()).filter(ObjectKind::isDatabaseKind);
  }

  public static boolean isDatabaseKind(@NotNull ObjectKind k) {
    return k.myOrderNum < ourLastDatabaseKind;
  }
}
