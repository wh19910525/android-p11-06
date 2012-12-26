package com.tarena.day170301;

import com.tarena.entity.ProviderInfo;
import com.tarena.entity.ProviderInfo.StudentInfo;
import com.tarena.entity.ProviderInfo.UserInfo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Day17_0301_ContentResolverActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Uri uri = ProviderInfo.StudentInfo.CONTENT_URI;
		ContentResolver cr = getContentResolver();//

		ContentValues values = new ContentValues();
		values.put(ProviderInfo.StudentInfo.NAME, "张三");
		values.put(ProviderInfo.StudentInfo.SEX, "男");
		values.put(ProviderInfo.StudentInfo.AGE, 19);
		cr.insert(uri, values);

		values.clear();
		values.put(ProviderInfo.StudentInfo.NAME, "张小三");
		values.put(ProviderInfo.StudentInfo.SEX, "男");
		values.put(ProviderInfo.StudentInfo.AGE, 29);
		cr.insert(uri, values);

		values.clear();
		values.put(ProviderInfo.StudentInfo.NAME, "张老三");
		values.put(ProviderInfo.StudentInfo.SEX, "男");
		values.put(ProviderInfo.StudentInfo.AGE, 9);
		cr.insert(uri, values);

		values.clear();
		values.put(StudentInfo.AGE, 35);
		cr.update(ContentUris.withAppendedId(uri, 1), values, null, null);

		cr.delete(ContentUris.withAppendedId(uri, 3), null, null);

		Cursor c = cr.query(uri, null, null, null, null);//
		if (c != null) {
			String[] cols = c.getColumnNames();
			while (c.moveToNext()) {
				for (String col : cols) {
					Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "--------------------------");
			}
			c.close();
		}

		uri = ProviderInfo.UserInfo.CONTEN_URI;
		values.clear();
		values.put(UserInfo.NAME, "admin");
		values.put(UserInfo.PASSWORD, "123456");
		cr.insert(uri, values);

		values.clear();
		values.put(UserInfo.PASSWORD, "888888");
		cr.update(ContentUris.withAppendedId(uri, 1), values, null, null);
		// cr.update(uri, values, UserInfo._ID + "=" + 1, null);

		c = cr.query(uri, null, null, null, null);
		if (c != null) {
			String[] cols = c.getColumnNames();
			while (c.moveToNext()) {
				for (String col : cols) {
					Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "--------------------------");
			}
			c.close();
		}
	}
}