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
		dp.init(2015, 10, 16, new OnDateChangedListener() {//初始化 时间，并且 监听

			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();//创建 一个 日历 对象,并且 初始化为 当前 系统 时间；
				c.set(year, monthOfYear, dayOfMonth);//设置 日历 对象 为 当前 改变的时间
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