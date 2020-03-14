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
package com.intellij.database.script;

import com.intellij.database.Dbms;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.SyntaxTraverser;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.Function;
import com.intellij.util.containers.JBIterable;
import com.intellij.util.containers.JBIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author gregsh
 */
public abstract class ScriptModel<E> implements Disposable {

  /**
   * See {@link SmartRange}, {@link StrictRange}, {@link PositionRange}, {@link ChosenRange}
   */
  public abstract ScriptModel<E> subModel(@Nullable TextRange range);

  public abstract JBIterable<? extends StatementIt<E>> statements();

  public abstract JBIterable<? extends ParamIt<E>> parameters();

  public abstract VirtualFile getVirtualFile();

  public abstract TextRange getTextRange();

  @NotNull
  public Dbms getDbms() {
    return Dbms.UNKNOWN;
  }

  public abstract <EE> ScriptModel<EE> rawTransform(Function<SyntaxTraverser<E>, SyntaxTraverser<EE>> transform);

  @Override
  public void dispose() {
  }

  @Override
  public String toString() {
    return "ScriptModel{range=" + getTextRange() + ", file=" + getVirtualFile() + "}";
  }

  public interface ModelIt<E> {
    String text();
    TextRange range();
    IElementType type();

    long rangeOffset();

    E object();
  }

  public interface StatementIt<E> extends ModelIt<E> {
    String text(PStorage storage, Condition<? super ParamIt<E>> paramCondition);
    Object resultType(Object target);
    JBIterable<? extends ParamIt<E>> parameters();
  }

  public interface ParamIt<E> extends ModelIt<E> {
    String displayName();
    String name();
    Iterable<String> description();
  }

  public abstract static class PStorage implements Iterable<Object> {
    @Nullable
    public abstract Object getValue(@NotNull Object p);
    @Nullable
    public abstract Object putValue(@NotNull Object p, Object v);

    @NotNull
    public static PStorage newStorage() {
      return new PStorage() {
        Map<Object, Object> map = new HashMap<>();
        @Override
        public Object getValue(@NotNull Object p) {
          return map.get(p);
        }

        @Override
        public Object putValue(@NotNull Object p, Object v) {
          return map.put(p, v);
        }

        @NotNull
        @Override
        public Iterator<Object> iterator() {
          return map.keySet().iterator();
        }
      };
    }
  }

  public abstract static class ModelItBase<E, Self extends ModelItBase<E, Self>> extends JBIterator<E> implements ModelIt<E> {

    protected SyntaxTraverser<E> traverser;

    @Override
    public long rangeOffset() {
      return 0L;
    }

    @Override
    public final E object() {
      return current();
    }

    @Override
    public final String text() {
      return traverser.api.textOf(current()).toString();
    }

    @Override
    public final TextRange range() {
      return traverser.api.rangeOf(current());
    }

    @Override
    public final IElementType type() {
      return traverser.api.typeOf(current());
    }

    public final JBIterable<Self> cursor() {
      //noinspection unchecked
      return JBIterator.cursor((Self)this);
    }
  }

  public static final class SmartRange extends TextRange {
    public SmartRange(@NotNull TextRange range) {
      super(range.getStartOffset(), range.getEndOffset(), true);
    }
  }

  public static final class StrictRange extends TextRange {
    public StrictRange(@NotNull TextRange range) {
      super(range.getStartOffset(), range.getEndOffset(), true);
    }
  }

  public static final class PositionRange extends TextRange {
    public PositionRange(int position) {
      super(position, position, true);
    }

    public PositionRange(TextRange range) {
      super(range.getStartOffset(), range.getEndOffset(), true);
    }
  }

  public static final class ChosenRange extends TextRange {
    public ChosenRange(TextRange range) {
      super(range.getStartOffset(), range.getEndOffset(), true);
    }
  }
}
