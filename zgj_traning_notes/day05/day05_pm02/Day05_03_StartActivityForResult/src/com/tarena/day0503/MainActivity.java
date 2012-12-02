package com.tarena.day0503;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public void doClick(View v) {
		Intent intent = new Intent(this, SecondActivity.class);
		// startActivity(intent);
		startActivityForResult(intent, 0);//
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			String value = data.getStringExtra("key");
			Toast.makeText(this, "返回值:" + value, 3000).show();
			break;

		case RESULT_CANCELED:
			Toast.makeText(this, "操作撤销，无返回值", 3000).show();
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}