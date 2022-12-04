/*
 * This file is generated by jOOQ.
 */
package ch.rasc.travellog.db.tables;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import ch.rasc.travellog.db.DefaultSchema;
import ch.rasc.travellog.db.Indexes;
import ch.rasc.travellog.db.Keys;
import ch.rasc.travellog.db.tables.records.AppSessionRecord;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = { "http://www.jooq.org", "jOOQ version:3.12.3" },
    comments = "This class is generated by jOOQ")
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppSession extends TableImpl<AppSessionRecord> {

  private static final long serialVersionUID = 2138119984;

  /**
   * The reference instance of <code>app_session</code>
   */
  public static final AppSession APP_SESSION = new AppSession();

  /**
   * The class holding records for this type
   */
  @Override
  public Class<AppSessionRecord> getRecordType() {
    return AppSessionRecord.class;
  }

  /**
   * The column <code>app_session.id</code>.
   */
  public final TableField<AppSessionRecord, String> ID = createField(DSL.name("id"),
      org.jooq.impl.SQLDataType.CHAR(35).nullable(false), this, "");

  /**
   * The column <code>app_session.app_user_id</code>.
   */
  public final TableField<AppSessionRecord, Long> APP_USER_ID = createField(
      DSL.name("app_user_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this,
      "");

  /**
   * The column <code>app_session.last_access</code>.
   */
  public final TableField<AppSessionRecord, LocalDateTime> LAST_ACCESS = createField(
      DSL.name("last_access"),
      org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false)
          .defaultValue(org.jooq.impl.DSL.field("current_timestamp()",
              org.jooq.impl.SQLDataType.LOCALDATETIME)),
      this, "");

  /**
   * The column <code>app_session.ip</code>.
   */
  public final TableField<AppSessionRecord, String> IP = createField(DSL.name("ip"),
      org.jooq.impl.SQLDataType.VARCHAR(39).defaultValue(
          org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.VARCHAR)),
      this, "");

  /**
   * The column <code>app_session.user_agent</code>.
   */
  public final TableField<AppSessionRecord, String> USER_AGENT = createField(
      DSL.name("user_agent"), org.jooq.impl.SQLDataType.VARCHAR(255).defaultValue(
          org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.VARCHAR)),
      this, "");

  /**
   * Create a <code>app_session</code> table reference
   */
  public AppSession() {
    this(DSL.name("app_session"), null);
  }

  /**
   * Create an aliased <code>app_session</code> table reference
   */
  public AppSession(String alias) {
    this(DSL.name(alias), APP_SESSION);
  }

  /**
   * Create an aliased <code>app_session</code> table reference
   */
  public AppSession(Name alias) {
    this(alias, APP_SESSION);
  }

  private AppSession(Name alias, Table<AppSessionRecord> aliased) {
    this(alias, aliased, null);
  }

  private AppSession(Name alias, Table<AppSessionRecord> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""));
  }

  public <O extends Record> AppSession(Table<O> child,
      ForeignKey<O, AppSessionRecord> key) {
    super(child, key, APP_SESSION);
  }

  @Override
  public Schema getSchema() {
    return DefaultSchema.DEFAULT_SCHEMA;
  }

  @Override
  public List<Index> getIndexes() {
    return Arrays.<Index>asList(Indexes.APP_SESSION_APP_USER_ID,
        Indexes.APP_SESSION_PRIMARY);
  }

  @Override
  public UniqueKey<AppSessionRecord> getPrimaryKey() {
    return Keys.KEY_APP_SESSION_PRIMARY;
  }

  @Override
  public List<UniqueKey<AppSessionRecord>> getKeys() {
    return Arrays.<UniqueKey<AppSessionRecord>>asList(Keys.KEY_APP_SESSION_PRIMARY);
  }

  @Override
  public List<ForeignKey<AppSessionRecord, ?>> getReferences() {
    return Arrays.<ForeignKey<AppSessionRecord, ?>>asList(Keys.APP_SESSION_IBFK_1);
  }

  public AppUser appUser() {
    return new AppUser(this, Keys.APP_SESSION_IBFK_1);
  }

  @Override
  public AppSession as(String alias) {
    return new AppSession(DSL.name(alias), this);
  }

  @Override
  public AppSession as(Name alias) {
    return new AppSession(alias, this);
  }

  /**
   * Rename this table
   */
  @Override
  public AppSession rename(String name) {
    return new AppSession(DSL.name(name), null);
  }

  /**
   * Rename this table
   */
  @Override
  public AppSession rename(Name name) {
    return new AppSession(name, null);
  }

  // -------------------------------------------------------------------------
  // Row5 type methods
  // -------------------------------------------------------------------------

  @Override
  public Row5<String, Long, LocalDateTime, String, String> fieldsRow() {
    return (Row5) super.fieldsRow();
  }
}
