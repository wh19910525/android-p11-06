package com.tarena.dal;

import com.tarena.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	private DBOpenHelper helper;

	public UserDao(Context context) {
		helper = new DBOpenHelper(context, "music.db");
	}

	public boolean isExists(User user) {
		boolean exists = false;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery(
				"select * from usertbl where username=? and userpass=?",
				new String[] { user.getName(), user.getPassword() });
		if (c != null && c.moveToFirst()) {
			exists = true;
			c.close();
		}
		db.close();
		return exists;
	}
}
