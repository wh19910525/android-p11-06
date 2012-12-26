package com.tarena.dal;

import com.tarena.dal.MusicProviderInfo.UserInfo;
import com.tarena.entity.User;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	private ContentResolver cr;

	public UserDao(Context context) {
		cr = context.getContentResolver();//���ContentResolver����
	}

	public boolean isExists(User user) {
		boolean exists = false;
		Cursor c = cr.query(UserInfo.CONTENT_URI, null, null, null, null);
		if (c != null && c.moveToFirst()) {
			exists = true;
			c.close();
		}
		return exists;
	}
}
