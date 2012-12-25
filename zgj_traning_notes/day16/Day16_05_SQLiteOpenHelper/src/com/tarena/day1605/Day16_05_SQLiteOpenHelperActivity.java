package com.tarena.day1605;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class Day16_05_SQLiteOpenHelperActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		DBOpenHelper helper = new DBOpenHelper(this, "student.db", 2);
		SQLiteDatabase db = helper.getReadableDatabase();//返回 可读的数据库
		Cursor c = db.rawQuery("select * from stutbl", null);//
		if (c != null) {
			String[] cols = c.getColumnNames();
			while (c.moveToNext()) {
				for (String col : cols) {
					Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
				}
			}
		 	c.close();
		}
		db.close();

	}
}