package com.tarena.day0401;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvInfo;

	private void setupView() {
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		/**����Bundle ����õ�֮ǰ���õ�ֵ*/
		Bundle data = getIntent().getExtras();
		String userName = data.getString("userName");
		int test = data.getInt("testKey");

		tvInfo.setText(userName + "," + test);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		setupView();
	}
}
