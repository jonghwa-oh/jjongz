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
package com.intellij.database.util;

import com.intellij.database.model.*;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.Function;
import com.intellij.util.Functions;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.JBIterable;
import com.intellij.util.containers.JBTreeTraverser;
import com.intellij.util.text.CaseInsensitiveStringHashingStrategy;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author gregsh
 */
public class DasUtil {
  private DasUtil() {
  }

  public static final Function<DasObject, String> TO_NAME = o -> o == null ? null : o.getName();
  public static final Function<DasObject, ObjectKind> TO_KIND = o -> o == null ? null : o.getKind();
  public static final Function<DasObject, DasObject> TO_PARENT = t -> {
    DasObject o = t.getDasParent();
    ObjectKind kind = getKind(o);
    return kind == ObjectKind.NONE || kind == ObjectKind.ROOT ? null : o;
  };

  public static final String NO_NAME = new String("");
  public static final Set<DasColumn.Attribute> NO_ATTRS = Collections.emptySet();
  public static final Casing CASING_MIXED = Casing.create(Case.MIXED, Case.EXACT);
  public static final Casing CASING_EXACT = Casing.create(Case.EXACT, Case.EXACT);
  public static final CasingProvider NO_CASING_PROVIDER = (kind, context) -> CASING_MIXED;

  @NotNull
  public static <V> Map<String, V> newCaseAwareMap(boolean sensitive) {
    TObjectHashingStrategy<String> strategy = sensitive ? ContainerUtil.canonicalStrategy() :
                                              CaseInsensitiveStringHashingStrategy.INSTANCE;
    return new THashMap<>(strategy);
  }

  @NotNull
  public static Set<String> newCaseAwareSet(boolean sensitive) {
    TObjectHashingStrategy<String> strategy = sensitive ? ContainerUtil.canonicalStrategy() :
                                              CaseInsensitiveStringHashingStrategy.INSTANCE;
    return new THashSet<>(strategy);
  }

  private static final JBTreeTraverser<DasObject> DAS_TRAVERSER = JBTreeTraverser.from(o -> o.getDasChildren(null));

  @NotNull
  public static JBTreeTraverser<DasObject> dasTraverser() {
    return DAS_TRAVERSER;
  }

  @NotNull
  public static ObjectKind getKind(@Nullable DasObject o) {
    return o == null ? ObjectKind.NONE : o.getKind();
  }

  @NotNull
  public static <C extends DasObject> Condition<C> byKind(@Nullable ObjectKind kind) {
    return kind == null || kind == ObjectKind.NONE ? Conditions.alwaysTrue() : object -> getKind(object) == kind;
  }

  @NotNull
  public static <C extends DasObject> Condition<C> byName(@Nullable String name) {
    return byName(name, NO_CASING_PROVIDER);
  }

  @NotNull
  public static <C extends DasObject> Condition<C> byName(@Nullable String name, @NotNull CasingProvider casing) {
    return name == null ? Conditions.alwaysFalse() : object -> nameEqual(object, name, casing);
  }

  @NotNull
  public static <C> Condition<C> byClass(@NotNull Class clazz) {
    return Conditions.instanceOf(clazz);
  }

  @NotNull
  public static DasModel emptyModel() {
    return EmptyModel.INSTANCE;
  }

  public static boolean nameEqual(@Nullable DasObject obj, @Nullable String name, @NotNull CasingProvider casing) {
    return nameEqual(obj, name, casing.getCasing(obj == null ? ObjectKind.NONE : obj.getKind(), obj));
  }

  public static boolean nameEqual(@Nullable DasObject obj, @Nullable String name, @NotNull Casing casing) {
    return obj != null && equal(obj.getName(), name, casing);
  }

  public static boolean isCaseSensitive(@NotNull Casing casing) {
    return casing.plain == Case.EXACT;
  }

  public static boolean equal(@Nullable String name1, @Nullable String name2, @NotNull Casing casing) {
    return Comparing.equal(name1, name2, isCaseSensitive(casing));
  }

  @NotNull
  public static <T> MultiRef<T> emptyMultiRef() {
    //noinspection unchecked
    return EmptyMultiRef.INSTANCE;
  }

  @NotNull
  public static <S, T> MultiRef<? extends T> transform(@NotNull MultiRef<S> ref, @NotNull Function<? super S, ? extends T> fun) {
    return new MappedMultiRef<>(ref, fun);
  }

  @Nullable
  public static DasObject getParentOfKind(@Nullable DasObject object, @Nullable ObjectKind kind, boolean strict) {
    if (kind == null) return null;
    for (DasObject o = strict && object != null ? object.getDasParent() : object; o != null; o = o.getDasParent()) {
      if (o.getKind() == kind) return o;
    }
    return null;
  }

  @Nullable
  public static <T> T getParentOfClass(@Nullable DasObject object, @Nullable Class<? extends T> clazz, boolean strict) {
    if (clazz == null) return null;
    for (DasObject o = strict && object != null ? object.getDasParent() : object; o != null; o = o.getDasParent()) {
      if (clazz.isInstance(o)) {
        //noinspection unchecked
        return (T)o;
      }
    }
    return null;
  }

  @Nullable
  public static DasNamespace getNamespace(@Nullable DasObject object) {
    return getParentOfClass(object, DasNamespace.class, true);
  }

  @NotNull
  public static JBIterable<DasObject> dasParents(@Nullable DasObject object) {
    if (object == null) return JBIterable.empty();
    return JBIterable.generate(object, TO_PARENT);
  }

  @Nullable
  public static <C extends DasObject> C findChild(@Nullable DasObject parent, Class<C> clazz, ObjectKind kind, String name) {
    return parent == null ? null : parent.getDasChildren(kind).filter(byName(name)).filter(clazz).first();
  }

  @Nullable
  public static DasObject getCatalogObject(@Nullable DasObject object) {
    return getParentOfKind(object, ObjectKind.DATABASE, false);
  }

  @Nullable
  public static DasObject getSchemaObject(@Nullable DasObject object) {
    return getParentOfKind(object, ObjectKind.SCHEMA, false);
  }

  @NotNull
  public static String getName(@Nullable DasObject object) {
    return object == null ? NO_NAME : object.getName();
  }

  @NotNull
  public static String getCatalog(@Nullable DasObject object) {
    return getName(getCatalogObject(object));
  }

  @NotNull
  public static String getSchema(@Nullable DasObject object) {
    return getName(getSchemaObject(object));
  }

  public static <T extends DasObject> MultiRef<T> asRef(final Iterable<? extends T> objects) {
    return asRef(objects, TO_NAME, Functions.identity());
  }

  public static <S, T extends DasObject> MultiRef<T> asRef(final Iterable<S> objects, final Function<? super S, String> namer, final Function<? super S, ? extends T> resolver) {
    final JBIterable<S> fi = JBIterable.from(objects);
    return new MultiRef<T>() {
      @Override
      public It<T> iterate() {
        final Iterator<S> it = objects.iterator();
        return new It<T>() {
          S cur;

          @Nullable
          @Override
          public T resolve() {
            return resolver.fun(cur);
          }

          @Override
          public boolean hasNext() {
            return it.hasNext();
          }

          @Override
          public String next() {
            return namer.fun(cur = it.next());
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }

      @Override
      public Iterable<String> names() {
        return fi.transform(namer);
      }

      @Override
      public Iterable<? extends T> resolveObjects() {
        return fi.filterMap(resolver);
      }

      @Override
      public int size() {
        return fi.size();
      }
    };
  }

  @Nullable
  public static DasObject resolveFinalTarget(@Nullable DasSynonym synonym) {
    int k = 9;
    DasObject o = synonym;
    while (o instanceof DasSynonym && k-- > 0) {
      o = ((DasSynonym)o).resolveTarget();
    }
    return k == 0 ? null : o;
  }

  // todo remove the following?
  public static boolean isPrimary(@Nullable DasColumn column) {
    return hasAttribute(column, DasColumn.Attribute.PRIMARY_KEY);
  }

  public static boolean isForeign(@Nullable DasColumn column) {
    return hasAttribute(column, DasColumn.Attribute.FOREIGN_KEY);
  }

  public static boolean isAuto(@Nullable DasColumn column) {
    return isAutoGenerated(column) || isComputed(column);
  }

  public static boolean isAutoGenerated(@Nullable DasColumn column) {
    return hasAttribute(column, DasColumn.Attribute.AUTO_GENERATED);
  }

  public static boolean isComputed(@Nullable DasColumn column) {
    return hasAttribute(column, DasColumn.Attribute.COMPUTED);
  }

  public static boolean isIndexColumn(@Nullable DasColumn column) {
    return hasAttribute(column, DasColumn.Attribute.INDEX);
  }

  private static boolean hasAttribute(@Nullable DasColumn column, @NotNull DasColumn.Attribute attribute) {
    DasTable table = column == null ? null : column.getTable();
    return table != null && table.getColumnAttrs(column).contains(attribute);
  }

  public static boolean isAncestor(@Nullable DasObject ancestor, @Nullable DasObject element, boolean strict) {
    if (ancestor == null || element == null) return false;
    if (ancestor == element) return !strict;
    for (DasObject object : dasParents(element)) {
      if (object == ancestor) return true;
    }
    return false;
  }

  @Deprecated
  @NotNull
  public static JBIterable<? extends DasNamespace> getSchemas(@NotNull DatabaseSystem dataSource) {
    return getSchemas((DasDataSource)dataSource);
  }

  @Deprecated
  public static JBIterable<? extends DasTable> getTables(@NotNull DatabaseSystem dataSource) {
    return getTables((DasDataSource)dataSource);
  }

  @NotNull
  public static JBIterable<? extends DasNamespace> getSchemas(@NotNull DasDataSource dataSource) {
    return dataSource.getModel().traverser().expandAndFilter(byClass(DasNamespace.class)).filter(byKind(ObjectKind.SCHEMA)).filter(DasNamespace.class);
  }

  public static JBIterable<? extends DasTable> getTables(@NotNull DasDataSource dataSource) {
    return getSchemaElements(dataSource, DasTable.class);
  }

  public static <T> JBIterable<? extends T> getSchemaElements(@NotNull DasDataSource dataSource, Class<T> clazz) {
    return dataSource.getModel().traverser().expandAndSkip(byClass(DasNamespace.class)).filter(clazz);
  }

  public static JBIterable<? extends DasColumn> getColumns(@NotNull DasObject table) {
    return table.getDasChildren(ObjectKind.COLUMN).filter(DasColumn.class);
  }

  public static JBIterable<? extends DasForeignKey> getForeignKeys(@NotNull DasTable table) {
    return table.getDasChildren(ObjectKind.FOREIGN_KEY).filter(DasForeignKey.class);
  }

  public static JBIterable<? extends DasIndex> getIndices(@NotNull DasTable table) {
    return table.getDasChildren(ObjectKind.INDEX).filter(DasIndex.class);
  }

  public static JBIterable<? extends DasTableKey> getTableKeys(@NotNull DasTable table) {
    return table.getDasChildren(ObjectKind.KEY).filter(DasTableKey.class);
  }

  @Nullable
  public static DasTableKey getPrimaryKey(@NotNull DasTable table) {
    for (DasTableKey key : getTableKeys(table)) {
      if (key.isPrimary()) return key;
    }
    return null;
  }

  public static boolean containsName(@NotNull String name, @NotNull MultiRef<?> ref) {
    return ContainerUtil.find(ref.names(), name) != null; // todo case insensitive?
  }

  @NotNull
  public static JBIterable<? extends DasArgument> getParameters(@NotNull DasRoutine routine) {
    return JBIterable.from(routine.getArguments()).filter(o -> !o.getArgumentDirection().isReturnOrResult());
  }

  @NotNull
  public static JBIterable<DasObject> getTraversableChildren(@NotNull DasObject object, @NotNull DasModel model) {
    return getTraversableChildren(object, model == emptyModel() ? dasTraverser() : model.traverser());
  }

  @NotNull
  public static JBIterable<DasObject> getTraversableChildren(@NotNull DasObject object, @NotNull JBTreeTraverser<DasObject> traverser) {
    return traverser.withRoot(object).expandAndSkip(Conditions.is(object)).traverse();
  }

  private static class EmptyMultiRef implements MultiRef, MultiRef.It {

    static final EmptyMultiRef INSTANCE = new EmptyMultiRef();

    private EmptyMultiRef() {
    }

    @Override
    public It iterate() {
      return this;
    }

    @Override
    public Iterable<String> names() {
      return Collections.emptyList();
    }

    @Override
    public Iterable resolveObjects() {
      return JBIterable.empty();
    }

    @Override
    public int size() {
      return 0;
    }

    @Nullable
    @Override
    public Object resolve() {
      throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Object next() {
      throw new NoSuchElementException();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private static class MappedMultiRef<S, T> implements MultiRef<T> {

    final MultiRef<? extends S> original;
    final Function<? super S, T> fun;

    private MappedMultiRef(MultiRef<? extends S> original, Function<? super S, T> fun) {
      this.original = original;
      this.fun = fun;
    }

    @Override
    public It<T> iterate() {
      final It<? extends S> it = original.iterate();
      return new It<T>() {
        @Nullable
        @Override
        public T resolve() {
          return fun.fun(it.resolve());
        }

        @Override
        public boolean hasNext() {
          return it.hasNext();
        }

        @Override
        public String next() {
          return it.next();
        }

        @Override
        public void remove() {
          it.remove();
        }
      };
    }

    @Override
    public Iterable<String> names() {
      return original.names();
    }

    @Override
    public Iterable<T> resolveObjects() {
      return JBIterable.from(original.resolveObjects()).filterMap(fun);
    }

    @Override
    public int size() {
      return original.size();
    }

  }

  private static class EmptyModel extends JBTreeTraverser<DasObject> implements DasModel {

    private static final EmptyModel INSTANCE = new EmptyModel("empty");
    final String debugName;

    private EmptyModel(String debugName) {
      super(Functions.<DasObject, Iterable<DasObject>>constant(JBIterable.empty()));
      this.debugName = debugName;
    }

    @NotNull
    @Override
    public MetaModel getMetaModel() {
      return MetaModel.EMPTY;
    }

    @NotNull
    @Override
    public JBIterable<? extends DasObject> getModelRoots() {
      return JBIterable.empty();
    }

    @Nullable
    @Override
    public DasNamespace getCurrentRootNamespace() {
      return null;
    }

    @NotNull
    @Override
    public Casing getCasing(@NotNull ObjectKind kind, @Nullable DasObject context) {
      return CASING_MIXED;
    }

    @NotNull
    @Override
    public JBTreeTraverser<DasObject> traverser() {
      return this;
    }

    @NotNull
    @Override
    protected JBTreeTraverser<DasObject> newInstance(@NotNull Meta<DasObject> meta) {
      return this;
    }

    @Override
    public boolean contains(@Nullable DasObject o) {
      return false;
    }

    @Override
    public String toString() {
      return debugName;
    }
  }
}
