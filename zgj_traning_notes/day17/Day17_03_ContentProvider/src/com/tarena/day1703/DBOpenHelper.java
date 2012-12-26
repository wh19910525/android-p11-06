package com.tarena.day1703;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	static final String TBL_STU = "stutbl";
	static final String TBL_USER = "usertbl";

	public DBOpenHelper(Context context) {
		super(context, "stu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table " + TBL_STU + "("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "age integer," + "sex text)");
		
		db.execSQL("create table " + TBL_USER + "("
				+ "_id integer primary key autoincrement,"
				+ "username text not null," + "userpass text not null" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
