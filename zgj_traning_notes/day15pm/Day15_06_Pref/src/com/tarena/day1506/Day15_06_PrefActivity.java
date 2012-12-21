package com.tarena.day1506;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class Day15_06_PrefActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 获取Preferences对象
		// SharedPreferences pref = getPreferences(MODE_PRIVATE);
		// SharedPreferences pref = getSharedPreferences("myPref",
		// MODE_PRIVATE);
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		// 获取Editor
		Editor editor = pref.edit();
		// 编辑偏好设置
		editor.putString("name", "张三").putInt("age", 18)
				.putBoolean("sex", true)
				.putLong("birthday", System.currentTimeMillis())
				.putFloat("weight", 59.5f).commit();

		editor.putString("name", "李四").remove("weight").commit();
		
		Log.i("info", "name:" + pref.getString("name", null));
		Log.i("info", "sex:" + pref.getBoolean("sex", false));
		Log.i("info", "age:" + pref.getInt("age", 0));
		Log.i("info", "weight:" + pref.getFloat("weight", 0));
		Log.i("info", "birthday:" + pref.getLong("birthday", 0));

	}
}