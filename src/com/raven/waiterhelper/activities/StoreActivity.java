/**
 * Store Activity implements the storing 
 * functionality for the WaiterHelper
 */
package com.raven.waiterhelper.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.waitresshelper.R;
import com.raven.waiterhelper.db.DBToPatronConverter;
import com.raven.waiterhelper.db.PatronFileReader;

public class StoreActivity extends Activity {

	private Button saveFileButton;
	private EditText fileNameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		// Show the Up button in the action bar.
		setupActionBar();
		saveFileButton = (Button) findViewById(R.id.saveFile);
		fileNameEditText = (EditText) findViewById(R.id.fileName);
		
		saveFileButton.setOnClickListener(saveFileListener);
		
		
	}

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
		getMenuInflater().inflate(R.menu.store, menu);
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

	private OnClickListener saveFileListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			DBToPatronConverter patronConverter = new DBToPatronConverter(getApplicationContext());
			String fileName = fileNameEditText.getText().toString();
			String file = patronConverter.convertToPatron().toString();
			
			PatronFileReader fileReader = new PatronFileReader(fileName, getApplicationContext(), file);
			fileReader.storeFile();
			
			Intent goHome = new Intent(StoreActivity.this, MainActivity.class);
			startActivity(goHome);
			
		}
	};
}
