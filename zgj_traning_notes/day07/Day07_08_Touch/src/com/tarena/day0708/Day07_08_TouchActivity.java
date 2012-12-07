package com.tarena.day0708;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class Day07_08_TouchActivity extends Activity {
	private TextView tvTtile;

	private void setupView() {
		tvTtile = (TextView) findViewById(R.id.tvTest);
	}

	private void addListener() {
		tvTtile.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("info", "onTouch()---action=" + event.getAction() + ",["
						+ event.getX() + "," + event.getY() + "]");
				// event.getAction();
				// event.getX();
				// event.getY();

				return true;
			}
		});
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