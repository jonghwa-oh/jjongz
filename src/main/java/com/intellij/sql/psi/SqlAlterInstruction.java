package com.intellij.sql.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.util.ArrayFactory;
import org.jetbrains.annotations.Nullable;

/**
 * @author Gregory.Shrago
 */
public interface SqlAlterInstruction extends SqlElement {

  SqlAlterInstruction[] EMPTY_ARRAY = new SqlAlterInstruction[0];
  ArrayFactory<SqlAlterInstruction> ARRAY_FACTORY = count -> count == 0 ? EMPTY_ARRAY : new SqlAlterInstruction[count];

  @Nullable
  IElementType getInstructionType();
}