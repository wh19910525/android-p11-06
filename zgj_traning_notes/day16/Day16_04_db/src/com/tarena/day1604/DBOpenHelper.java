package com.tarena.day1604;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DBOpenHelper {
	
	private SQLiteDatabase db;

	public DBOpenHelper(Context context, String dbName) {
		boolean exists = context.getDatabasePath(dbName).exists();
		db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);//创建数据库
		if (!exists) {
			// 初次使用软件时,生成数据库表
			onCreate(db);
		}
	}

	public SQLiteDatabase getDataBase() {
		return db;
	}

	protected abstract void onCreate(SQLiteDatabase db);
}
