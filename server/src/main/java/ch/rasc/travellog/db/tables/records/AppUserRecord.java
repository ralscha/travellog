/*
 * This file is generated by jOOQ.
 */
package ch.rasc.travellog.db.tables.records;


import ch.rasc.travellog.db.tables.AppUser;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppUserRecord extends UpdatableRecordImpl<AppUserRecord> implements Record12<Long, String, String, String, String, Boolean, LocalDateTime, LocalDateTime, String, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>app_user.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>app_user.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>app_user.email</code>.
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>app_user.email</code>.
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>app_user.email_new</code>.
     */
    public void setEmailNew(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>app_user.email_new</code>.
     */
    public String getEmailNew() {
        return (String) get(2);
    }

    /**
     * Setter for <code>app_user.password_hash</code>.
     */
    public void setPasswordHash(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>app_user.password_hash</code>.
     */
    public String getPasswordHash() {
        return (String) get(3);
    }

    /**
     * Setter for <code>app_user.authority</code>.
     */
    public void setAuthority(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>app_user.authority</code>.
     */
    public String getAuthority() {
        return (String) get(4);
    }

    /**
     * Setter for <code>app_user.enabled</code>.
     */
    public void setEnabled(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>app_user.enabled</code>.
     */
    public Boolean getEnabled() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>app_user.expired</code>.
     */
    public void setExpired(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>app_user.expired</code>.
     */
    public LocalDateTime getExpired() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>app_user.last_access</code>.
     */
    public void setLastAccess(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>app_user.last_access</code>.
     */
    public LocalDateTime getLastAccess() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>app_user.confirmation_token</code>.
     */
    public void setConfirmationToken(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>app_user.confirmation_token</code>.
     */
    public String getConfirmationToken() {
        return (String) get(8);
    }

    /**
     * Setter for <code>app_user.confirmation_token_created</code>.
     */
    public void setConfirmationTokenCreated(LocalDateTime value) {
        set(9, value);
    }

    /**
     * Getter for <code>app_user.confirmation_token_created</code>.
     */
    public LocalDateTime getConfirmationTokenCreated() {
        return (LocalDateTime) get(9);
    }

    /**
     * Setter for <code>app_user.password_reset_token</code>.
     */
    public void setPasswordResetToken(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>app_user.password_reset_token</code>.
     */
    public String getPasswordResetToken() {
        return (String) get(10);
    }

    /**
     * Setter for <code>app_user.password_reset_token_created</code>.
     */
    public void setPasswordResetTokenCreated(LocalDateTime value) {
        set(11, value);
    }

    /**
     * Getter for <code>app_user.password_reset_token_created</code>.
     */
    public LocalDateTime getPasswordResetTokenCreated() {
        return (LocalDateTime) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, String, String, String, String, Boolean, LocalDateTime, LocalDateTime, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Long, String, String, String, String, Boolean, LocalDateTime, LocalDateTime, String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return AppUser.APP_USER.ID;
    }

    @Override
    public Field<String> field2() {
        return AppUser.APP_USER.EMAIL;
    }

    @Override
    public Field<String> field3() {
        return AppUser.APP_USER.EMAIL_NEW;
    }

    @Override
    public Field<String> field4() {
        return AppUser.APP_USER.PASSWORD_HASH;
    }

    @Override
    public Field<String> field5() {
        return AppUser.APP_USER.AUTHORITY;
    }

    @Override
    public Field<Boolean> field6() {
        return AppUser.APP_USER.ENABLED;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return AppUser.APP_USER.EXPIRED;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return AppUser.APP_USER.LAST_ACCESS;
    }

    @Override
    public Field<String> field9() {
        return AppUser.APP_USER.CONFIRMATION_TOKEN;
    }

    @Override
    public Field<LocalDateTime> field10() {
        return AppUser.APP_USER.CONFIRMATION_TOKEN_CREATED;
    }

    @Override
    public Field<String> field11() {
        return AppUser.APP_USER.PASSWORD_RESET_TOKEN;
    }

    @Override
    public Field<LocalDateTime> field12() {
        return AppUser.APP_USER.PASSWORD_RESET_TOKEN_CREATED;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEmail();
    }

    @Override
    public String component3() {
        return getEmailNew();
    }

    @Override
    public String component4() {
        return getPasswordHash();
    }

    @Override
    public String component5() {
        return getAuthority();
    }

    @Override
    public Boolean component6() {
        return getEnabled();
    }

    @Override
    public LocalDateTime component7() {
        return getExpired();
    }

    @Override
    public LocalDateTime component8() {
        return getLastAccess();
    }

    @Override
    public String component9() {
        return getConfirmationToken();
    }

    @Override
    public LocalDateTime component10() {
        return getConfirmationTokenCreated();
    }

    @Override
    public String component11() {
        return getPasswordResetToken();
    }

    @Override
    public LocalDateTime component12() {
        return getPasswordResetTokenCreated();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEmail();
    }

    @Override
    public String value3() {
        return getEmailNew();
    }

    @Override
    public String value4() {
        return getPasswordHash();
    }

    @Override
    public String value5() {
        return getAuthority();
    }

    @Override
    public Boolean value6() {
        return getEnabled();
    }

    @Override
    public LocalDateTime value7() {
        return getExpired();
    }

    @Override
    public LocalDateTime value8() {
        return getLastAccess();
    }

    @Override
    public String value9() {
        return getConfirmationToken();
    }

    @Override
    public LocalDateTime value10() {
        return getConfirmationTokenCreated();
    }

    @Override
    public String value11() {
        return getPasswordResetToken();
    }

    @Override
    public LocalDateTime value12() {
        return getPasswordResetTokenCreated();
    }

    @Override
    public AppUserRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AppUserRecord value2(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public AppUserRecord value3(String value) {
        setEmailNew(value);
        return this;
    }

    @Override
    public AppUserRecord value4(String value) {
        setPasswordHash(value);
        return this;
    }

    @Override
    public AppUserRecord value5(String value) {
        setAuthority(value);
        return this;
    }

    @Override
    public AppUserRecord value6(Boolean value) {
        setEnabled(value);
        return this;
    }

    @Override
    public AppUserRecord value7(LocalDateTime value) {
        setExpired(value);
        return this;
    }

    @Override
    public AppUserRecord value8(LocalDateTime value) {
        setLastAccess(value);
        return this;
    }

    @Override
    public AppUserRecord value9(String value) {
        setConfirmationToken(value);
        return this;
    }

    @Override
    public AppUserRecord value10(LocalDateTime value) {
        setConfirmationTokenCreated(value);
        return this;
    }

    @Override
    public AppUserRecord value11(String value) {
        setPasswordResetToken(value);
        return this;
    }

    @Override
    public AppUserRecord value12(LocalDateTime value) {
        setPasswordResetTokenCreated(value);
        return this;
    }

    @Override
    public AppUserRecord values(Long value1, String value2, String value3, String value4, String value5, Boolean value6, LocalDateTime value7, LocalDateTime value8, String value9, LocalDateTime value10, String value11, LocalDateTime value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AppUserRecord
     */
    public AppUserRecord() {
        super(AppUser.APP_USER);
    }

    /**
     * Create a detached, initialised AppUserRecord
     */
    public AppUserRecord(Long id, String email, String emailNew, String passwordHash, String authority, Boolean enabled, LocalDateTime expired, LocalDateTime lastAccess, String confirmationToken, LocalDateTime confirmationTokenCreated, String passwordResetToken, LocalDateTime passwordResetTokenCreated) {
        super(AppUser.APP_USER);

        setId(id);
        setEmail(email);
        setEmailNew(emailNew);
        setPasswordHash(passwordHash);
        setAuthority(authority);
        setEnabled(enabled);
        setExpired(expired);
        setLastAccess(lastAccess);
        setConfirmationToken(confirmationToken);
        setConfirmationTokenCreated(confirmationTokenCreated);
        setPasswordResetToken(passwordResetToken);
        setPasswordResetTokenCreated(passwordResetTokenCreated);
    }
}
