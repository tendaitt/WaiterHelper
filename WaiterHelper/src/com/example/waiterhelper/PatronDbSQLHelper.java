/**
 * 
 */
package com.example.waiterhelper;

import com.example.waiterhelper.PatronReaderContract.PatronEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/** The PatronDbSQLHelper class creates the database and table
 * @author Tendai T.T. Mudyiwa
 * @version 1.0
 */
public class PatronDbSQLHelper extends SQLiteOpenHelper{
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + PatronEntry.TABLE_NAME + " (" +
			PatronEntry._ID + " INTEGER PRIMARY KEY," +
			PatronEntry.COLUMN_TABLE_ID + TEXT_TYPE + COMMA_SEP +
			PatronEntry.COLUMN_SEAT_ID + TEXT_TYPE + COMMA_SEP +
			PatronEntry.COLUMN_MEAL + TEXT_TYPE + ")";
	
	private static final String SQL_DELETE_ENTRIES = 
			"DROP TABLE IF EXISTS " + PatronEntry.TABLE_NAME;
	
	
	private static final String DATABASE_NAME = "Patron.db";
	private static final int DATABASE_VERSION = 1;
	
	/**
	 * @param context
	 * @param name - The name of the database
	 * @param factory
	 * @param version
	 */
	public PatronDbSQLHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	
	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
	}

	
}
