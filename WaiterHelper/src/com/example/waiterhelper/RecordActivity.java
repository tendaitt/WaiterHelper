package com.example.waiterhelper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.waiterhelper.PatronReaderContract.PatronEntry;
import com.example.waitresshelper.R;

public class RecordActivity extends Activity {

	private String tableNumber;
	private String seatNumber;
	private String meal;
	private EditText tableID;
	private EditText seatID; 
	private Spinner menuSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);

		Button addNewButton = (Button) findViewById(R.id.addNew);
		Button done	= (Button) findViewById(R.id.done_button);

		tableID = (EditText) findViewById(R.id.table_IDText);
		seatID = (EditText) findViewById(R.id.seat_IDText);	
		done.setOnClickListener(doneClickListener);
		addNewButton.setOnClickListener(addNewClickListener);

		// Show the Up button in the action bar.
		setupActionBar();
		menuSpinner = (Spinner) findViewById(R.id.menuSpinner);

		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.menu_array, android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		menuSpinner.setAdapter(adapter);
	}

	private OnClickListener doneClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent goHomeIntent = new Intent (RecordActivity.this, MainActivity.class);
			startActivity(goHomeIntent);
			updateDB();
			reset();

		}		
	};

	private OnClickListener addNewClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			updateDB();
			reset();
		}
	};
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Reset the variables to default values
	 */
	private void reset() {
		tableID.setText("");
		seatID.setText("");
	}


	private void updateDB() {
		//get the values for everything
		tableNumber = tableID.getText().toString();
		seatNumber = seatID.getText().toString();
		meal = menuSpinner.getSelectedItem().toString();

		//update database
		PatronDbSQLHelper dbHelper = new PatronDbSQLHelper(this.getApplicationContext(), meal, null, 0);
		SQLiteDatabase patronDB = dbHelper.getWritableDatabase();
		//map of values
		ContentValues values = new ContentValues();
		values.put(PatronEntry.COLUMN_TABLE_ID, tableNumber);
		values.put(PatronEntry.COLUMN_MEAL, meal);
		values.put(PatronEntry.COLUMN_SEAT_ID, seatNumber);

		//Insert the new row
		@SuppressWarnings("unused")
		long newRowId;
		newRowId = patronDB.insert(PatronEntry.TABLE_NAME, null, values);


	}
}
