package com.intellij.database.model;

import com.intellij.openapi.util.text.NaturalComparator;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * @author gregsh
 */
public interface DasObject {
  Comparator<DasObject> NATURAL_COMPARATOR = Comparator.comparing(DasObject::getName, NaturalComparator.INSTANCE);

  @NotNull
  ObjectKind getKind();

  /**
   * Returns name or DasUtil.NO_NAME
   */
  @NotNull
  String getName();

  @Nullable
  default String getComment() {
    return null;
  }

  @Nullable
  default DasObject getDasParent() {
    return null;
  }

  @NotNull
  default JBIterable<? extends DasObject> getDasChildren(@Nullable ObjectKind kind) {
    return JBIterable.empty();
  }

  /**
   * @deprecated use {@link #getDasParent()} instead.
   */
  @Deprecated
  @Nullable
  default DasObject getDbParent() {
    return getDasParent();
  }


  /**
   * @deprecated use {@link #getDasChildren(ObjectKind)} instead.
   */
  @Deprecated
  @NotNull
  default <C extends DasObject> JBIterable<C> getDbChildren(@NotNull Class<C> clazz, @NotNull ObjectKind kind) {
    return getDasChildren(kind).filter(clazz);
  }
}
