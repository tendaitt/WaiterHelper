/**
 * MainActivity implement the functionality of 
 * the WaiterHelper homepage
 */
package com.example.waiterhelper;

import com.example.waiterhelper.PatronReaderContract.PatronEntry;
import com.example.waitresshelper.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

		Button enterPatron = (Button)findViewById(R.id.enterPatron);
		Button viewPatrons = (Button)findViewById(R.id.viewButton);
		Button storePatrons = (Button)findViewById(R.id.storeButton);
		Button loadPatrons = (Button)findViewById(R.id.loadButton);
		Button exitApplication = (Button)findViewById(R.id.exitButton);

		enterPatron.setOnClickListener(enterPatronListener);
		viewPatrons.setOnClickListener(viewPatronListener);
		storePatrons.setOnClickListener(storePatronListener);
		loadPatrons.setOnClickListener(loadPatronListener);
		exitApplication.setOnClickListener(exitApplicationListener);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnClickListener enterPatronListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent recordIntent = new Intent(MainActivity.this, RecordActivity.class);
			MainActivity.this.startActivity(recordIntent);
		}
	};

	private OnClickListener storePatronListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent storeIntent = new Intent(MainActivity.this, StoreActivity.class);
			MainActivity.this.startActivity(storeIntent);
		}
	};
	private OnClickListener viewPatronListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent viewIntent = new Intent(MainActivity.this, ViewActivity.class);
			MainActivity.this.startActivity(viewIntent);
		}
	};
	private OnClickListener loadPatronListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String[] fileList = fileList();
			PatronFileReader fileReader = new PatronFileReader(fileList[0], getApplicationContext());
			String fileOutput = fileReader.retrieveFile();
			Intent loadIntent = new Intent(MainActivity.this, LoadActivity.class);
			loadIntent.putExtra("patronList", fileOutput);
			startActivity(loadIntent);
		}
	};
	
	private OnClickListener exitApplicationListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			//check if any list that has been created is stored.
			PatronDbSQLHelper dbHelper = new PatronDbSQLHelper(getApplicationContext(), "", null, 0);
			SQLiteDatabase patronDb = dbHelper.getReadableDatabase();
			patronDb.delete(PatronEntry.TABLE_NAME, null, null);
	        patronDb.close();
			finish();
			System.exit(0);
			
		}
	};
}
