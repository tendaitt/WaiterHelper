/*DBToPatronConverter is a helper class that
 * converts database entries to instances of
 * the Patron Class 
 */
package com.raven.waiterhelper.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.raven.waiterhelper.db.PatronReaderContract.PatronEntry;

/**
 * @author Tendai T.T. Mudyiwa
 * @version October 20 2013
 */
public class DBToPatronConverter {

	private PatronDbSQLHelper patronDbHelper;
	private SQLiteDatabase patronDb;
	private ArrayList<Patron> patronList;
	
	/**
	 * Constructor for the DBToPatronConverter
	 */
	public DBToPatronConverter(Context context){
		this.patronDbHelper = new PatronDbSQLHelper(context,"", null, 0);
		this.patronDb = patronDbHelper.getReadableDatabase();
		this.patronList = new ArrayList<Patron>();
	}
	
	/**
	 * Method that converts database entries to patron objects
	 * @return	returns a list of Patrons
	 */
	public ArrayList<Patron> convertToPatron(){
		//Columns required
		String[] projection = {
				PatronEntry._ID,
				PatronEntry.COLUMN_TABLE_ID,
				PatronEntry.COLUMN_SEAT_ID,
				PatronEntry.COLUMN_MEAL
		};
		
		String sortOrder = 
				PatronEntry._ID + " DESC";

		Cursor c = patronDb.query(
				PatronEntry.TABLE_NAME,
				projection, 
				null, null, null, null, sortOrder);

		c.moveToFirst();
		
		int i = 0;
		while(i<c.getCount()){
			Patron patron = new Patron(c.getString(c.getColumnIndexOrThrow(PatronEntry.COLUMN_TABLE_ID)),c.getString(c.getColumnIndexOrThrow(PatronEntry.COLUMN_SEAT_ID)), c.getString(c.getColumnIndexOrThrow(PatronEntry.COLUMN_MEAL)));
			patronList.add(patron);
			c.moveToNext();
			i++;
		}
		
		return patronList;
	}
	
	
	@Override
	public String toString(){
		String stringList = "";
		
		for(int i = 0; i<patronList.size(); i++){
			
			stringList.concat(patronList.get(i).toString());
		}
	
		return stringList;
	}
	

	
	
}
