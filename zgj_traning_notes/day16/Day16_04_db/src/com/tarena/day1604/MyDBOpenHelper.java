package com.tarena.day1604;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDBOpenHelper extends DBOpenHelper {
	
	public MyDBOpenHelper(Context context, String dbName) {
		super(context, dbName);
	}

	@Override
	protected void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "sex text not null,"
				+ "age integer not null" + ")");
		db.execSQL("insert into stutbl (name,sex,age) values('ÕÅÈý','ÄÐ',18)");
	}

}
