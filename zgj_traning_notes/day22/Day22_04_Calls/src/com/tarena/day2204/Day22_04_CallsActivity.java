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

		// Calls.CONTENT_URI ָ��  /data/data/com.android.providers.contacts/databases/contacts2.db ��� calls ��
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
				Log.i("info", "���룺"+c.getString(c.getColumnIndex(Calls.NUMBER)));
				Log.i("info", "ʱ��: "+c.getLong(c.getColumnIndex(Calls.DATE)));
				Log.i("info", "ͨ��ʱ����"+c.getInt(c.getColumnIndex(Calls.DURATION)));
				Log.i("info", "ͨ������: " + c.getInt(c.getColumnIndex(Calls.TYPE)));
				Log.i("info", "===================");
			}
			c.close();
		}
	}
}