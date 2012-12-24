package com.tarena.day1602;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class PrefScreenActivity extends Activity {
	
	private SharedPreferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("info", "showAlbum=" + pref.getBoolean("pref_show_album", true));
		Log.i("info", "showSinger" + pref.getBoolean("pref_show_Singer", true));
		Log.i("info", "showDuration=" + pref.getBoolean("pref_show_Duration", true));
		String sort_col = pref.getString("pref_sort_col", "1");
		switch (Integer.parseInt(sort_col)) {
		case 1:
			sort_col = "按歌名排序";
			break;
		case 2:
			sort_col = "按歌手排序";
			break;
		case 3:
			sort_col = "按时长排序";
			break;
		case 4:
			sort_col = "按专辑排序";
			break;
		}
		Log.i("info", "排序方式：" + sort_col);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, "设置");
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
}