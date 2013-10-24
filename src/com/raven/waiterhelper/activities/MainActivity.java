/**
 * MainActivity implement the functionality of 
 * the WaiterHelper homepage
 */
package com.raven.waiterhelper.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.raven.waiterhelper.db.PatronDbSQLHelper;
import com.raven.waiterhelper.db.PatronFileReader;
import com.raven.waiterhelper.db.PatronReaderContract.PatronEntry;
import com.raven.waitresshelper.R;

/**
 * This class has code for the main activity for the WaiterHelperApplication
 * 
 * @author Tendai T.T. Mudyiwa
 * @version Oct 10 2013
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void enterPatron(View view){
		Intent recordIntent = new Intent(MainActivity.this, RecordActivity.class);
		MainActivity.this.startActivity(recordIntent);
	}


	public void storePatron(View view){
		Intent storeIntent = new Intent(MainActivity.this, StoreActivity.class);
		MainActivity.this.startActivity(storeIntent);
	}

	public void viewPatron(View view){
		Intent viewIntent = new Intent(MainActivity.this, ViewActivity.class);
		MainActivity.this.startActivity(viewIntent);
	}
	
	public void loadPatron(View view){
		String[] fileList = fileList();
		PatronFileReader fileReader = new PatronFileReader(fileList[0], getApplicationContext());
		String fileOutput = fileReader.retrieveFile();
		Intent loadIntent = new Intent(MainActivity.this, LoadActivity.class);
		loadIntent.putExtra("patronList", fileOutput);
		startActivity(loadIntent);
	}
	
	public void exitWaiterHelper(View view){
		PatronDbSQLHelper dbHelper = new PatronDbSQLHelper(getApplicationContext(), "", null, 0);
		SQLiteDatabase patronDb = dbHelper.getReadableDatabase();
		patronDb.delete(PatronEntry.TABLE_NAME, null, null);
		patronDb.close();
		finish();
		System.exit(0);}
}
