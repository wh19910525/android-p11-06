package com.tarena.day1507;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvInfo;

	private void setupView() {
		tvInfo = (TextView) findViewById(R.id.etInfo);
		String name = PreferenceManager.getDefaultSharedPreferences(this)
				.getString("userName", null);
		if (name != null) {
			tvInfo.setText(name + "£¬»¶Ó­ÄúµÇÂ¼");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		setupView();
	}
}
