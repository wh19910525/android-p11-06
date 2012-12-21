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

		// ��ȡPreferences����
		// SharedPreferences pref = getPreferences(MODE_PRIVATE);
		// SharedPreferences pref = getSharedPreferences("myPref",
		// MODE_PRIVATE);
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		// ��ȡEditor
		Editor editor = pref.edit();
		// �༭ƫ������
		editor.putString("name", "����").putInt("age", 18)
				.putBoolean("sex", true)
				.putLong("birthday", System.currentTimeMillis())
				.putFloat("weight", 59.5f).commit();

		editor.putString("name", "����").remove("weight").commit();
		
		Log.i("info", "name:" + pref.getString("name", null));
		Log.i("info", "sex:" + pref.getBoolean("sex", false));
		Log.i("info", "age:" + pref.getInt("age", 0));
		Log.i("info", "weight:" + pref.getFloat("weight", 0));
		Log.i("info", "birthday:" + pref.getLong("birthday", 0));

	}
}