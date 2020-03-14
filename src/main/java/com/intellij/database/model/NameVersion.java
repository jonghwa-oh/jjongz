package com.intellij.database.model;

import com.intellij.database.util.DasUtil;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.Nullable;

/**
 * @author gregsh
 */
public class NameVersion {

  public static final NameVersion UNKNOWN = new NameVersion(null, null);

  public final String name;
  public final String version;

  public NameVersion(@Nullable String name, @Nullable String version) {
    this.name = ObjectUtils.notNull(name, DasUtil.NO_NAME);
    this.version = ObjectUtils.notNull(version, DasUtil.NO_NAME);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NameVersion nameVersion = (NameVersion)o;

    if (!name.equals(nameVersion.name)) return false;
    if (!version.equals(nameVersion.version)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + version.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "NameVersion{" +
           "name='" + name + '\'' + ", version='" + version + '\'' +
           '}';
  }
}
