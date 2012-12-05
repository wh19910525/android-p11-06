package com.tarena.day0603;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Day06_03_ResActivity extends Activity {
	private EditText etHeight, etWeight;

	/**
	 * 界面初始化方法
	 */
	private void setupView() {
		etHeight = (EditText) findViewById(R.id.etHeight);
		etWeight = (EditText) findViewById(R.id.etWeight);

		// String[] week = getResources().getStringArray(R.array.week);
		// for (String day : week) {
		// Log.i("info", day);
		// }
		// Log.i("info", "==========================");
		// int[] days = getResources().getIntArray(R.array.days);
		// for (int day : days) {
		// Log.i("info", "11月" + day + "日");
		// }
	}

	/**
	 * 输入框的非空验证
	 */
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 按钮的事件处理方法
	 * 
	 * @param v
	 */
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnCalc:
			// 非空验证
			if (isEmpty(etHeight)) {
				etHeight.setError(getResources().getString(
						R.string.error_et_height));
				return;
			}
			if (isEmpty(etWeight)) {
				etWeight.setError(getResources().getString(
						R.string.error_et_weight));
				return;
			}
			// 获取输入
			double h = Double.parseDouble(etHeight.getText().toString());
			double w = Double.parseDouble(etWeight.getText().toString());

			// 计算，并输出提示
			double bmi = w * 10000 / h / h;
			if (bmi < 18)
				Toast.makeText(this, R.string.message_lv1, 3000).show();
			else if (bmi <= 25)
				Toast.makeText(this, R.string.message_lv2, 3000).show();
			else if (bmi <= 30)
				Toast.makeText(this, R.string.message_lv3, 3000).show();
			else if (bmi <= 35)
				Toast.makeText(this, R.string.message_lv4, 3000).show();
			else if (bmi <= 40)
				Toast.makeText(this, R.string.message_lv5, 3000).show();
			else if (bmi <= 45)
				Toast.makeText(this, R.string.message_lv6, 3000).show();
			else
				Toast.makeText(this, R.string.message_lv7, 3000).show();
		case R.id.btnReset:
			etHeight.setText("");
			etWeight.setText("");
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // TODO Auto-generated method stub
	// MenuInflater inflater = new MenuInflater(this);
	// inflater.inflate(R.menu.opts, menu);
	// return super.onCreateOptionsMenu(menu);
	// }

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case R.id.menu_opts_about:
//			Toast.makeText(this, "关于我们", 3000).show();
//			break;
//		case R.id.menu_sub_loop:
//			Toast.makeText(this, "循环播放", 3000).show();
//			break;
//		case R.id.menu_sub_random:
//			Toast.makeText(this, "随机播放", 3000).show();
//			break;
//		case R.id.menu_opts_exit:
//			finish();
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}