package com.intellij.database;

import com.intellij.diagnostic.PluginException;
import com.intellij.openapi.extensions.AbstractExtensionPointBean;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.util.KeyedExtensionCollector;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.KeyedLazyInstance;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.xmlb.annotations.Attribute;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gregsh
 */
public class DbmsExtension<T> extends KeyedExtensionCollector<T, Dbms> {
  public DbmsExtension(@NotNull String epName) {
    super(epName);
  }

  @NotNull
  @Override
  protected String keyToString(@NotNull Dbms key) {
    return key.getName();
  }

  @NotNull
  public List<T> allForDbms(@NotNull Dbms dbms) {
    return forKey(dbms);
  }

  public T forDbms(@NotNull Dbms dbms) {
    return findSingle(dbms);
  }

  @NotNull
  public List<Bean<T>> allExtensions() {
    ExtensionPoint<Bean<T>> point = Extensions.getRootArea().getExtensionPointIfRegistered(getName());
    return point == null ? Collections.emptyList() : point.getExtensionList();
  }

  @NotNull
  @Override
  protected List<T> buildExtensions(@NotNull String stringKey, @NotNull Dbms key) {
    List<T> result = super.buildExtensions(stringKey, key);
    if (result.isEmpty() && key != Dbms.UNKNOWN && !forKey(Dbms.UNKNOWN).isEmpty()) {
      ExtensionPoint<Bean<T>> point = Extensions.getRootArea().getExtensionPointIfRegistered(getName());
      if (point != null) {
        result = new ArrayList<>();
        for (Bean<T> bean : point.getExtensions()) {
          if (!StringUtil.equals(bean.dbmsStr, Dbms.UNKNOWN.getName())) {
            continue;
          }

          try {
            result.add(bean.doGetInstance(bean.findExtensionClass(bean.implementationClass), key));
          }
          catch (ReflectiveOperationException e) {
            throw new PluginException(e, bean.getPluginId());
          }
        }
      }
    }
    return result;
  }

  public static class Bean<T> extends AbstractExtensionPointBean implements KeyedLazyInstance<T> {
    @Attribute("dbms")
    public String dbmsStr;

    @Attribute("implementationClass")
    public String implementationClass;

    private final NotNullLazyValue<T> myHandler = NotNullLazyValue.createValue(() -> {
      try {
        return doGetInstance(findExtensionClass(implementationClass), getDbms());
      }
      catch (ReflectiveOperationException e) {
        throw new PluginException(e, getPluginId());
      }
    });

    @NotNull
    T doGetInstance(@NotNull Class<T> tClass, @NotNull Dbms dbms) throws ReflectiveOperationException {
      try {
        return instantiateByDbms(tClass, dbms);
      }
      catch (NoSuchMethodException ignored) {
        return ReflectionUtil.newInstance(tClass);
      }
    }

    @NotNull
    T instantiateByDbms(@NotNull Class<T> tClass, @NotNull Dbms dbms) throws ReflectiveOperationException {
      Constructor<T> constructor = tClass.getConstructor(Dbms.class);
      constructor.setAccessible(true);
      return constructor.newInstance(dbms);
    }

    @NotNull
    public Dbms getDbms() {
      Dbms dbms = Dbms.byName(dbmsStr);
      if (dbms == null) {
        throw new IllegalArgumentException("Dbms not found: " + dbmsStr);
      }
      return dbms;
    }

    @NotNull
    @Override
    public T getInstance() {
      return myHandler.getValue();
    }

    @Override
    public String getKey() {
      return dbmsStr;
    }

    @Override
    public String toString() {
      return dbmsStr;
    }
  }

  public static class InstanceBean<T> extends Bean<T> {
    @NotNull
    @Override
    T doGetInstance(@NotNull Class<T> aClass, @NotNull Dbms dbms) throws ReflectiveOperationException {
      //noinspection unchecked
      return (T)aClass.getField("INSTANCE").get(null);
    }
  }
}