package com.tarena.day0305;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Toast;

public class Day03_05_DatePickerActivity extends Activity {
	private DatePicker dp;

	private void setupView() {
		dp = (DatePicker) findViewById(R.id.datePicker1);
		dp.init(2012, 10, 16, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();
				c.set(year, monthOfYear, dayOfMonth);
				Toast.makeText(Day03_05_DatePickerActivity.this,
						c.getTime().toLocaleString(), 3000).show();
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}