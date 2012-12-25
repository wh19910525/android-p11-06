package com.tarena.day170201;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Day17_0201_ContentResolverActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ContentResolver cr = getContentResolver();
		Uri uri = Uri.parse("content://com.tarena.providers.stu/student");

		ContentValues values = new ContentValues();
		values.put("name", "王二麻子");
		values.put("sex", "男");
		values.put("age", "50");
		cr.insert(uri, values);

		values.clear();
		values.put("age", 52);
		cr.update(uri, values, "name=?", new String[] { "王二麻子" });

		cr.update(uri, values, "_id=3", null);
		cr.update(ContentUris.withAppendedId(uri, 2), values, null, null);

		cr.delete(ContentUris.withAppendedId(uri, 1), null, null);

		Cursor c = cr.query(uri, null, null, null, "name desc");
		if (c != null) {
			while (c.moveToNext()) {
				String[] cols = c.getColumnNames();
				for (String col : cols) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "===================");
			}
			c.close();
		}
	}
}