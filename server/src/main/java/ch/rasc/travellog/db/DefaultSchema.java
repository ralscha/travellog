/*
 * This file is generated by jOOQ.
 */
package ch.rasc.travellog.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ch.rasc.travellog.db.tables.AppSession;
import ch.rasc.travellog.db.tables.AppUser;
import ch.rasc.travellog.db.tables.Log;
import ch.rasc.travellog.db.tables.LogPhoto;
import ch.rasc.travellog.db.tables.Travel;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = { "http://www.jooq.org", "jOOQ version:3.12.3" },
    comments = "This class is generated by jOOQ")
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

  private static final long serialVersionUID = 189674324;

  /**
   * The reference instance of <code></code>
   */
  public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

  /**
   * The table <code>app_session</code>.
   */
  public final AppSession APP_SESSION = ch.rasc.travellog.db.tables.AppSession.APP_SESSION;

  /**
   * The table <code>app_user</code>.
   */
  public final AppUser APP_USER = ch.rasc.travellog.db.tables.AppUser.APP_USER;

  /**
   * The table <code>log</code>.
   */
  public final Log LOG = ch.rasc.travellog.db.tables.Log.LOG;

  /**
   * The table <code>log_photo</code>.
   */
  public final LogPhoto LOG_PHOTO = ch.rasc.travellog.db.tables.LogPhoto.LOG_PHOTO;

  /**
   * The table <code>travel</code>.
   */
  public final Travel TRAVEL = ch.rasc.travellog.db.tables.Travel.TRAVEL;

  /**
   * No further instances allowed
   */
  private DefaultSchema() {
    super("", null);
  }

  @Override
  public Catalog getCatalog() {
    return DefaultCatalog.DEFAULT_CATALOG;
  }

  @Override
  public final List<Table<?>> getTables() {
    List result = new ArrayList();
    result.addAll(getTables0());
    return result;
  }

  private final List<Table<?>> getTables0() {
    return Arrays.<Table<?>>asList(AppSession.APP_SESSION, AppUser.APP_USER, Log.LOG,
        LogPhoto.LOG_PHOTO, Travel.TRAVEL);
  }
}
