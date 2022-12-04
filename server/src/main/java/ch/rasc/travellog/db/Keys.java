/*
 * This file is generated by jOOQ.
 */
package ch.rasc.travellog.db;

import jakarta.annotation.processing.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import ch.rasc.travellog.db.tables.AppSession;
import ch.rasc.travellog.db.tables.AppUser;
import ch.rasc.travellog.db.tables.Log;
import ch.rasc.travellog.db.tables.LogPhoto;
import ch.rasc.travellog.db.tables.Travel;
import ch.rasc.travellog.db.tables.records.AppSessionRecord;
import ch.rasc.travellog.db.tables.records.AppUserRecord;
import ch.rasc.travellog.db.tables.records.LogPhotoRecord;
import ch.rasc.travellog.db.tables.records.LogRecord;
import ch.rasc.travellog.db.tables.records.TravelRecord;

/**
 * A class modelling foreign key relationships and constraints of tables of the
 * <code></code> schema.
 */
@Generated(value = { "http://www.jooq.org", "jOOQ version:3.12.3" },
    comments = "This class is generated by jOOQ")
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

  // -------------------------------------------------------------------------
  // IDENTITY definitions
  // -------------------------------------------------------------------------

  public static final Identity<AppUserRecord, Long> IDENTITY_APP_USER = Identities0.IDENTITY_APP_USER;
  public static final Identity<LogRecord, Long> IDENTITY_LOG = Identities0.IDENTITY_LOG;
  public static final Identity<LogPhotoRecord, Long> IDENTITY_LOG_PHOTO = Identities0.IDENTITY_LOG_PHOTO;
  public static final Identity<TravelRecord, Long> IDENTITY_TRAVEL = Identities0.IDENTITY_TRAVEL;

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------

  public static final UniqueKey<AppSessionRecord> KEY_APP_SESSION_PRIMARY = UniqueKeys0.KEY_APP_SESSION_PRIMARY;
  public static final UniqueKey<AppUserRecord> KEY_APP_USER_PRIMARY = UniqueKeys0.KEY_APP_USER_PRIMARY;
  public static final UniqueKey<AppUserRecord> KEY_APP_USER_EMAIL = UniqueKeys0.KEY_APP_USER_EMAIL;
  public static final UniqueKey<LogRecord> KEY_LOG_PRIMARY = UniqueKeys0.KEY_LOG_PRIMARY;
  public static final UniqueKey<LogPhotoRecord> KEY_LOG_PHOTO_PRIMARY = UniqueKeys0.KEY_LOG_PHOTO_PRIMARY;
  public static final UniqueKey<TravelRecord> KEY_TRAVEL_PRIMARY = UniqueKeys0.KEY_TRAVEL_PRIMARY;

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------

  public static final ForeignKey<AppSessionRecord, AppUserRecord> APP_SESSION_IBFK_1 = ForeignKeys0.APP_SESSION_IBFK_1;
  public static final ForeignKey<LogRecord, TravelRecord> LOG_IBFK_1 = ForeignKeys0.LOG_IBFK_1;
  public static final ForeignKey<LogPhotoRecord, LogRecord> LOG_PHOTO_IBFK_1 = ForeignKeys0.LOG_PHOTO_IBFK_1;
  public static final ForeignKey<TravelRecord, AppUserRecord> TRAVEL_IBFK_1 = ForeignKeys0.TRAVEL_IBFK_1;

  // -------------------------------------------------------------------------
  // [#1459] distribute members to avoid static initialisers > 64kb
  // -------------------------------------------------------------------------

  private static class Identities0 {
    public static Identity<AppUserRecord, Long> IDENTITY_APP_USER = Internal
        .createIdentity(AppUser.APP_USER, AppUser.APP_USER.ID);
    public static Identity<LogRecord, Long> IDENTITY_LOG = Internal
        .createIdentity(Log.LOG, Log.LOG.ID);
    public static Identity<LogPhotoRecord, Long> IDENTITY_LOG_PHOTO = Internal
        .createIdentity(LogPhoto.LOG_PHOTO, LogPhoto.LOG_PHOTO.ID);
    public static Identity<TravelRecord, Long> IDENTITY_TRAVEL = Internal
        .createIdentity(Travel.TRAVEL, Travel.TRAVEL.ID);
  }

  private static class UniqueKeys0 {
    public static final UniqueKey<AppSessionRecord> KEY_APP_SESSION_PRIMARY = Internal
        .createUniqueKey(AppSession.APP_SESSION, "KEY_app_session_PRIMARY",
            AppSession.APP_SESSION.ID);
    public static final UniqueKey<AppUserRecord> KEY_APP_USER_PRIMARY = Internal
        .createUniqueKey(AppUser.APP_USER, "KEY_app_user_PRIMARY", AppUser.APP_USER.ID);
    public static final UniqueKey<AppUserRecord> KEY_APP_USER_EMAIL = Internal
        .createUniqueKey(AppUser.APP_USER, "KEY_app_user_email", AppUser.APP_USER.EMAIL);
    public static final UniqueKey<LogRecord> KEY_LOG_PRIMARY = Internal
        .createUniqueKey(Log.LOG, "KEY_log_PRIMARY", Log.LOG.ID);
    public static final UniqueKey<LogPhotoRecord> KEY_LOG_PHOTO_PRIMARY = Internal
        .createUniqueKey(LogPhoto.LOG_PHOTO, "KEY_log_photo_PRIMARY",
            LogPhoto.LOG_PHOTO.ID);
    public static final UniqueKey<TravelRecord> KEY_TRAVEL_PRIMARY = Internal
        .createUniqueKey(Travel.TRAVEL, "KEY_travel_PRIMARY", Travel.TRAVEL.ID);
  }

  private static class ForeignKeys0 {
    public static final ForeignKey<AppSessionRecord, AppUserRecord> APP_SESSION_IBFK_1 = Internal
        .createForeignKey(ch.rasc.travellog.db.Keys.KEY_APP_USER_PRIMARY,
            AppSession.APP_SESSION, "app_session_ibfk_1",
            AppSession.APP_SESSION.APP_USER_ID);
    public static final ForeignKey<LogRecord, TravelRecord> LOG_IBFK_1 = Internal
        .createForeignKey(ch.rasc.travellog.db.Keys.KEY_TRAVEL_PRIMARY, Log.LOG,
            "log_ibfk_1", Log.LOG.TRAVEL_ID);
    public static final ForeignKey<LogPhotoRecord, LogRecord> LOG_PHOTO_IBFK_1 = Internal
        .createForeignKey(ch.rasc.travellog.db.Keys.KEY_LOG_PRIMARY, LogPhoto.LOG_PHOTO,
            "log_photo_ibfk_1", LogPhoto.LOG_PHOTO.LOG_ID);
    public static final ForeignKey<TravelRecord, AppUserRecord> TRAVEL_IBFK_1 = Internal
        .createForeignKey(ch.rasc.travellog.db.Keys.KEY_APP_USER_PRIMARY, Travel.TRAVEL,
            "travel_ibfk_1", Travel.TRAVEL.APP_USER_ID);
  }
}
