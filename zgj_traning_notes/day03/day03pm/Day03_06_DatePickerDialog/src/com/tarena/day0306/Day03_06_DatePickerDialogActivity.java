package com.tarena.day0306;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class Day03_06_DatePickerDialogActivity extends Activity {
	private DatePickerDialog dialog;

	private void setupView() {
		OnDateSetListener callBack = new OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				Toast.makeText(
						Day03_06_DatePickerDialogActivity.this,
						year + "Äê" + (monthOfYear + 1) + "ÔÂ" + dayOfMonth + "ÈÕ",
						3000).show();
			}
		};
		Calendar c = Calendar.getInstance();
		dialog = new DatePickerDialog(this, callBack, c.get(Calendar.YEAR),
				c.get(Calendar.MONTH), c.get(Calendar.DATE));
	}

	public void doClick(View v) {
		dialog.show();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}