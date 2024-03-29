/*
 * This file is generated by jOOQ.
 */
package ch.rasc.travellog.db;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import ch.rasc.travellog.db.tables.AppSession;
import ch.rasc.travellog.db.tables.Log;
import ch.rasc.travellog.db.tables.LogPhoto;
import ch.rasc.travellog.db.tables.Travel;

/**
 * A class modelling indexes of tables in the default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

	// -------------------------------------------------------------------------
	// INDEX definitions
	// -------------------------------------------------------------------------

	public static final Index APP_SESSION_APP_USER_ID = Internal.createIndex(
			DSL.name("app_user_id"), AppSession.APP_SESSION,
			new OrderField[] { AppSession.APP_SESSION.APP_USER_ID }, false);
	public static final Index TRAVEL_APP_USER_ID = Internal.createIndex(
			DSL.name("app_user_id"), Travel.TRAVEL,
			new OrderField[] { Travel.TRAVEL.APP_USER_ID }, false);
	public static final Index LOG_PHOTO_LOG_ID = Internal.createIndex(DSL.name("log_id"),
			LogPhoto.LOG_PHOTO, new OrderField[] { LogPhoto.LOG_PHOTO.LOG_ID }, false);
	public static final Index LOG_TRAVEL_ID = Internal.createIndex(DSL.name("travel_id"),
			Log.LOG, new OrderField[] { Log.LOG.TRAVEL_ID }, false);
}
