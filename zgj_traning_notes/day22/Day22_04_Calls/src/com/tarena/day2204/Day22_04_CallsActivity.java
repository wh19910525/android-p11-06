package com.tarena.day2204;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.util.Log;

public class Day22_04_CallsActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Calls.CONTENT_URI 指向  /data/data/com.android.providers.contacts/databases/contacts2.db 里的 calls 表
		// Calls._ID
		// Calls.NUMBER
		// Calls.DATE
		// Calls.DURATION
		// Calls.TYPE
		// Calls.NEW
		// Calls.CACHED_NAME

		// Calls.OUTGOING_TYPE
		// Calls.INCOMING_TYPE

		String[] projection = { Calls._ID, Calls.NUMBER, Calls.DATE,
				Calls.DURATION, Calls.TYPE };
		Cursor c = getContentResolver().query(Calls.CONTENT_URI, projection,
				null, null, null);
		if(c!=null){
			while(c.moveToNext()){
				Log.i("info", "id:" + c.getInt(c.getColumnIndex(Calls._ID)));
				Log.i("info", "号码："+c.getString(c.getColumnIndex(Calls.NUMBER)));
				Log.i("info", "时间: "+c.getLong(c.getColumnIndex(Calls.DATE)));
				Log.i("info", "通话时长："+c.getInt(c.getColumnIndex(Calls.DURATION)));
				Log.i("info", "通话类型: " + c.getInt(c.getColumnIndex(Calls.TYPE)));
				Log.i("info", "===================");
			}
			c.close();
		}
	}
}