/*
 * This file is generated by jOOQ.
 */
package ch.rasc.travellog.db.tables;


import ch.rasc.travellog.db.DefaultSchema;
import ch.rasc.travellog.db.Indexes;
import ch.rasc.travellog.db.Keys;
import ch.rasc.travellog.db.tables.records.AppUserRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row12;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppUser extends TableImpl<AppUserRecord> {

    private static final long serialVersionUID = 460530803;

    /**
     * The reference instance of <code>app_user</code>
     */
    public static final AppUser APP_USER = new AppUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppUserRecord> getRecordType() {
        return AppUserRecord.class;
    }

    /**
     * The column <code>app_user.id</code>.
     */
    public final TableField<AppUserRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>app_user.email</code>.
     */
    public final TableField<AppUserRecord, String> EMAIL = createField(DSL.name("email"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>app_user.email_new</code>.
     */
    public final TableField<AppUserRecord, String> EMAIL_NEW = createField(DSL.name("email_new"), org.jooq.impl.SQLDataType.VARCHAR(255).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>app_user.password_hash</code>.
     */
    public final TableField<AppUserRecord, String> PASSWORD_HASH = createField(DSL.name("password_hash"), org.jooq.impl.SQLDataType.VARCHAR(255).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>app_user.authority</code>.
     */
    public final TableField<AppUserRecord, String> AUTHORITY = createField(DSL.name("authority"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>app_user.enabled</code>.
     */
    public final TableField<AppUserRecord, Boolean> ENABLED = createField(DSL.name("enabled"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>app_user.expired</code>.
     */
    public final TableField<AppUserRecord, LocalDateTime> EXPIRED = createField(DSL.name("expired"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>app_user.last_access</code>.
     */
    public final TableField<AppUserRecord, LocalDateTime> LAST_ACCESS = createField(DSL.name("last_access"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>app_user.confirmation_token</code>.
     */
    public final TableField<AppUserRecord, String> CONFIRMATION_TOKEN = createField(DSL.name("confirmation_token"), org.jooq.impl.SQLDataType.CHAR(35).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.CHAR)), this, "");

    /**
     * The column <code>app_user.confirmation_token_created</code>.
     */
    public final TableField<AppUserRecord, LocalDateTime> CONFIRMATION_TOKEN_CREATED = createField(DSL.name("confirmation_token_created"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>app_user.password_reset_token</code>.
     */
    public final TableField<AppUserRecord, String> PASSWORD_RESET_TOKEN = createField(DSL.name("password_reset_token"), org.jooq.impl.SQLDataType.CHAR(35).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.CHAR)), this, "");

    /**
     * The column <code>app_user.password_reset_token_created</code>.
     */
    public final TableField<AppUserRecord, LocalDateTime> PASSWORD_RESET_TOKEN_CREATED = createField(DSL.name("password_reset_token_created"), org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>app_user</code> table reference
     */
    public AppUser() {
        this(DSL.name("app_user"), null);
    }

    /**
     * Create an aliased <code>app_user</code> table reference
     */
    public AppUser(String alias) {
        this(DSL.name(alias), APP_USER);
    }

    /**
     * Create an aliased <code>app_user</code> table reference
     */
    public AppUser(Name alias) {
        this(alias, APP_USER);
    }

    private AppUser(Name alias, Table<AppUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private AppUser(Name alias, Table<AppUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AppUser(Table<O> child, ForeignKey<O, AppUserRecord> key) {
        super(child, key, APP_USER);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.APP_USER_EMAIL, Indexes.APP_USER_PRIMARY);
    }

    @Override
    public Identity<AppUserRecord, Long> getIdentity() {
        return Keys.IDENTITY_APP_USER;
    }

    @Override
    public UniqueKey<AppUserRecord> getPrimaryKey() {
        return Keys.KEY_APP_USER_PRIMARY;
    }

    @Override
    public List<UniqueKey<AppUserRecord>> getKeys() {
        return Arrays.<UniqueKey<AppUserRecord>>asList(Keys.KEY_APP_USER_PRIMARY, Keys.KEY_APP_USER_EMAIL);
    }

    @Override
    public AppUser as(String alias) {
        return new AppUser(DSL.name(alias), this);
    }

    @Override
    public AppUser as(Name alias) {
        return new AppUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(String name) {
        return new AppUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(Name name) {
        return new AppUser(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, String, String, String, String, Boolean, LocalDateTime, LocalDateTime, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}
