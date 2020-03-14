package com.intellij.sql.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.intellij.sql.dialects.SqlLanguageDialect;
import com.intellij.util.NotNullFunction;
import com.intellij.util.containers.ConcurrentFactoryMap;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Gregory.Shrago
 */
public class SqlTokenType extends IElementType {

  public SqlTokenType(@NotNull @NonNls String debugName) {
    super(debugName, SqlLanguage.INSTANCE);
  }

  public SqlTokenType(@NotNull @NonNls String debugName, SqlLanguageDialect dialect) {
    super(debugName, dialect);
  }

  protected SqlTokenType(@NotNull @NonNls String debugName, Language language, boolean register) {
    super(debugName, language, register);
  }

  public static class Synthetic extends SqlTokenType {

    public final static NotNullFunction<String, Synthetic> FACTORY = Synthetic::new;

    public Synthetic(@NotNull @NonNls String debugName) {
      super(debugName);
    }
  }

  public static class SqlInjectionMark extends Synthetic {
    private static final Map<String, SqlInjectionMark> ourInjectionTokens = ConcurrentFactoryMap.create(t -> new SqlInjectionMark(t), ContainerUtil::createConcurrentWeakValueMap);
    private final String myInjection;

    public static SqlInjectionMark get(@NotNull String injection) {
      return ourInjectionTokens.get(injection);
    }

    private SqlInjectionMark(@NotNull String injection) {
      super("inject_" + injection);
      myInjection = injection;
    }

    @NotNull
    public String getInjection() {
      return myInjection;
    }
  }
}
