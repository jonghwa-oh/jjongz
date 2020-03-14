/*
 * Copyright 2000-2017 JetBrains s.r.o.
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

/**
 * The trigger sequence order.
 *
 * @author Leonid Bushuev from JetBrains
 */
public enum TrigTurn {
  INSTEAD_OF,
  ALSO,
  BEFORE_STMT,
  BEFORE_ROW,
  AFTER_ROW,
  AFTER_STMT
}
