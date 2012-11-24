package com.tarena.day0308;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class Day03_08_TimePickerDialogActivity extends Activity {
	private TimePickerDialog dialog;

	private void setupView() {
		OnTimeSetListener callBack = new OnTimeSetListener() {

			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				Toast.makeText(Day03_08_TimePickerDialogActivity.this,
						format(hourOfDay) + ":" + format(minute), 3000).show();
			}
		};
		dialog = new TimePickerDialog(this, callBack, 14, 20, true);
	}

	private String format(int numb) {
		return numb < 10 ? "0" + numb : "" + numb;
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