package com.tarena.day0307;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class Day03_07_TimePickerActivity extends Activity {
	

	private TimePicker tp;

	private void setupView() {
		tp = (TimePicker) findViewById(R.id.timePicker1);
		tp.setCurrentHour(14);
		tp.setCurrentMinute(12);
		tp.setIs24HourView(false);

	}

	private void addListener() {
		tp.setOnTimeChangedListener(new OnTimeChangedListener() {//用 匿名类 设置 监听器

			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				Toast.makeText(Day03_07_TimePickerActivity.this,
						format(hourOfDay) + ":" + format(minute), 3000).show();
			}
		});
	}

	private String format(int numb) {
		return numb < 10 ? "0" + numb : "" + numb;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		addListener();
	}
}