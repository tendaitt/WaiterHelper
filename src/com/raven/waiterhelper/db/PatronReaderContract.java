/**
 * PatronReaderContract contains inner class that defines
 * the db table and its columns
 */
package com.raven.waiterhelper.db;

import android.provider.BaseColumns;

/**
 * @author  Tendai T.T. Mudyiwa
 * @version October 24 2013
 *
 */
public final class PatronReaderContract {

	//To prevent someone from accidentally instantiating the contract
	//give it an empty constructor

	public PatronReaderContract() {}

	/*Inner class defines table contents */
	public static abstract class PatronEntry implements BaseColumns{
		
		public static final String TABLE_NAME = "patron";
		public static final String _ID = "id";
		public static final String COLUMN_TABLE_ID = "table_number";
		public static final String COLUMN_SEAT_ID = "seat_number";
		public static final String COLUMN_MEAL = "meal";
	}

}
