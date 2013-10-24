/**
 * LoadActivity implements the load 
 * activity for the WaiterHelper
 */
package com.raven.waiterhelper.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.waitresshelper.R;

public class LoadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		
		String patronList = getIntent().getExtras().getString("patronList");
		((TextView) findViewById(R.id.loadView)).setText(patronList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load, menu);
		return true;
	}

}
