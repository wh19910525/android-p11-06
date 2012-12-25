package com.tarena.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public DBOpenHelper(Context context, String dbName) {
		super(context, dbName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table musictbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "album text not null,"
				+ "singer text," + "author text," + "composer text,"
				+ "duration integer," + "album_path text," + "music_path text"
				+ ")");

		db.execSQL("create table usertbl("
				+ "_id integer primary key autoincrement,"
				+ "username text not null," + "userpass text not null)");
		db.execSQL("insert into usertbl(username,userpass) values('ww','123')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
