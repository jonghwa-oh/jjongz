package com.intellij.sql.formatter.settings;

import com.intellij.application.options.CodeStyle;
import com.intellij.database.Dbms;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.sql.dialects.SqlLanguageDialect;
import com.intellij.sql.psi.SqlLanguage;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.containers.JBIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Static functions for obtaining correct code style settings stuff. 
 * 
 * @author Leonid Bushuev
 */
public abstract class SqlCodeStyles {

  private static final Map<Language, Class<? extends SqlCodeStyleSettings>> ourDialectSettingsClasses = new ConcurrentHashMap<>();

  private static SqlLanguageDialect ourGenericDialect = null;
  private static SqlLanguageDialect ourPreviewDialectForGeneric = null;
  
  /**
   * The default SQL dialect, now it's SqlLanguage.
   */
  @NotNull
  private static final Language ourDefaultDialect = SqlLanguage.INSTANCE;  

  static {
    ourDialectSettingsClasses.put(ourDefaultDialect, SqlCodeStyleSettings.class);
  }
  
  public static void registerDialectSettingsClass(@NotNull Language dialect, @NotNull Class<? extends SqlCodeStyleSettings> settingsClass) {
    ourDialectSettingsClasses.put(dialect, settingsClass);
  }
  
  public static void registerGenericDialect(@NotNull SqlLanguageDialect dialect, @NotNull SqlLanguageDialect previewDialect) {
    ourGenericDialect = dialect;
    ourPreviewDialectForGeneric = previewDialect;
  }

  public static JBIterable<SqlLanguageDialect> listSqlDialects() {
    return JBIterable.from(ourDialectSettingsClasses.keySet()).filter(SqlLanguageDialect.class);
  }

  public static SqlLanguageDialect getGenericDialect() {
    return ourGenericDialect;
  }

  public static SqlLanguageDialect getPreviewDialectFor(@Nullable Language language) {
    if (language == null || language == SqlLanguage.INSTANCE || language == getGenericDialect()) return ourPreviewDialectForGeneric;
    if (language instanceof SqlLanguageDialect) return (SqlLanguageDialect) language;
    return ourPreviewDialectForGeneric;
  }

  /**
   * For the given original dialect it returns the corresponded dialect for which code style settings exist.
   * Warning! — this function doesn't consider the {@link SqlCodeStyleSettings#USE_GENERIC_STYLE} option.
   * @param dialect original dialect.
   * @return the corresponded settings dialect.
   * @see #getSettingsClass
   */
  @NotNull
  public static Language getSettingsLanguage(@NotNull Language dialect) {
    if (ourDialectSettingsClasses.containsKey(dialect)) return dialect;
    
    if (dialect instanceof SqlLanguageDialect) {
      SqlLanguageDialect d = (SqlLanguageDialect) dialect;
      Dbms dbms = d.getDbms();
      if (dbms.isPostgres())  return SqlLanguageDialect.EP.forDbms(Dbms.POSTGRES);
      if (dbms.isOracle())  return SqlLanguageDialect.EP.forDbms(Dbms.ORACLE);
      if (dbms.isTransactSql()) return SqlLanguageDialect.EP.forDbms(Dbms.MSSQL);
      if (dbms.isMysql()) return SqlLanguageDialect.EP.forDbms(Dbms.MYSQL);
    }
    
    return ourDefaultDialect;
  }

  /**
   * Returns the settings class for the given dialect.
   * Warning! — this function doesn't consider the {@link SqlCodeStyleSettings#USE_GENERIC_STYLE} option.
   * @param dialect the original (or settings) dialect.
   * @return the settings class.
   */
  @NotNull
  public static Class<? extends SqlCodeStyleSettings> getSettingsClass(@NotNull Language dialect) {
    Language settingLanguage = getSettingsLanguage(dialect);
    Class<? extends SqlCodeStyleSettings> settingsClass = ourDialectSettingsClasses.get(settingLanguage);
    assert settingsClass != null : "The settings class for the dialect " + dialect.getID() + " is not registered";
    return settingsClass;
  }

  /**
   * Looks for the appropriate SQL settings for the given file,
   * considering dialect and the inheritance option.
   */
  @NotNull
  public static SqlCodeStyleSettings getSqlSettings(@NotNull PsiFile file) {
    Language dialect = file.getLanguage();
    CodeStyleSettings settingsContainer = CodeStyle.getSettings(file);
    return getSqlSettings(settingsContainer, dialect);
  }

  /**
   * Looks for the appropriate SQL settings for the given project and dialect.
   * The inheritance option is also considered.
   */
  @NotNull
  public static SqlCodeStyleSettings getSqlSettings(@Nullable Project project, @NotNull Language dialect) {
    return getSqlSettings(getSettings(project), dialect);
  }

  /**
   * Returns the SQL settings from the given settings container for the specified dialect,
   * or the generic settings (from this container) if the dialect's option {@link SqlCodeStyleSettings#USE_GENERIC_STYLE} is checked.
   */
  @NotNull
  public static SqlCodeStyleSettings getSqlSettings(@NotNull CodeStyleSettings settingsContainer, @NotNull Language dialect) {
    SqlCodeStyleSettings settings = pickSqlSettings(settingsContainer, dialect);
    if(settings.USE_GENERIC_STYLE) settings = getGenericSqlSettings(settingsContainer);
    return settings;
  }

  /**
   * Returns the SQL settings from the given settings container for the specified dialect.
   * The option {@link SqlCodeStyleSettings#USE_GENERIC_STYLE} is NOT considered.
   */
  @NotNull
  public static SqlCodeStyleSettings pickSqlSettings(@NotNull CodeStyleSettings settingsContainer, @NotNull Language dialect) {
    if (!(dialect instanceof SqlLanguageDialect || dialect instanceof SqlLanguage))
      return getGenericSqlSettings(settingsContainer); // fix EA-142449: sometimes this method is called with PlainTextLanguage
    Language settingLanguage = getSettingsLanguage(dialect);
    Class<? extends SqlCodeStyleSettings> settingsClass = ourDialectSettingsClasses.get(settingLanguage);
    if (settingsClass == null)
      throw new IllegalArgumentException("Dialect " + dialect.getID() + " and settings language " + settingLanguage.getID() + " have no corresponding settings class");

    try {
      return settingsContainer.getCustomSettings(settingsClass);
    }
    catch (RuntimeException rte) {
      throw new RuntimeException("Failed to get setting " + settingsClass.getSimpleName() + " (dialect: " + dialect.getID() + ")", rte);
    }
  }

  @NotNull
  public static SqlCodeStyleSettings getGenericSqlSettings(@Nullable Project project) {
    CodeStyleSettings settingsContainer = getSettings(project);
    return getGenericSqlSettings(settingsContainer);
  }

  @NotNull
  public static SqlCodeStyleSettings getGenericSqlSettings(@NotNull CodeStyleSettings settingsContainer) {
    return settingsContainer.getCustomSettings(SqlCodeStyleSettings.class);
  }

  @NotNull
  public static CodeStyleSettings getSettings(@Nullable Project project) {
    return project != null ? CodeStyle.getSettings(project) : CodeStyle.getDefaultSettings();
  }

  /**
   * Returns all registered settings dialects, when the default dialect is first.
   * @return
   */
  public static JBIterable<? extends Language> getSettingsDialects() {
    return JBIterable
      .of(ourDefaultDialect)
      .append(JBIterable
                .from(ourDialectSettingsClasses.keySet())
                .filter(SqlLanguageDialect.class)
                .filter(d -> d != ourDefaultDialect));
  }


  /**
   * Copies settings. This function is subject for deleting when the EP on read/write settings done;
   * please use {@link SqlCodeStyleSettingsUtil#copyCodeStyleSettings} instead.
   */
  static void copySettings(@NotNull SqlCodeStyleSettings source, @NotNull SqlCodeStyleSettings target) {
    if (source == target) return;
    
    // common part
    CommonCodeStyleSettings sourceCommonSettings = source.getCorrespondedCommonSettings();
    CommonCodeStyleSettings targetCommonSettings = target.getCorrespondedCommonSettings();
    if (sourceCommonSettings != targetCommonSettings) {
      targetCommonSettings.copyFrom(sourceCommonSettings);
    }

    // custom part
    Field[] fieldsToCopy =
      JBIterable.of(SqlCodeStyleSettings.class.getDeclaredFields())
      .filter(f -> Modifier.isPublic(f.getModifiers()) 
                   && !Modifier.isStatic(f.getModifiers())
                   && Character.isUpperCase(f.getName().charAt(0)) 
                   && !f.isAnnotationPresent(Deprecated.class)
                   && !target.isSettingHidden(f.getName()))
      .toArray(new Field[0]);
    ReflectionUtil.copyFields(fieldsToCopy, source, target);
    
    // the version
    target.myVersion = source.myVersion;
    target.myInitialized = source.myInitialized;
  } 
  
}
