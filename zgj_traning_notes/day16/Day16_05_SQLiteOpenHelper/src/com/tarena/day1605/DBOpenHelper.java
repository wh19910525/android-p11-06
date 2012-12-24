package com.tarena.day1605;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "stu.db", null, 1);
	}

	public DBOpenHelper(Context context, String dbName, int version) {
		super(context, dbName, null, version);
	}

	public DBOpenHelper(Context context, String dbName) {
		super(context, dbName, null, 1);
	}

	public DBOpenHelper(Context context, int version) {
		super(context, "stu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "age integer not null" + ")");
		db.execSQL("insert into stutbl(name,age) values('ÕÅÈý',19)");
		Log.i("info", "DBOpenHelper.onCreate()");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("info", "DBOpenHelper.onUpgrade()");
	}

}
