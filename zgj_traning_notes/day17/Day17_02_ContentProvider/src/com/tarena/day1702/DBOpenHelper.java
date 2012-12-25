package com.tarena.day1702;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "stu.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table stutbl("
				+ "_id integer primary key autoincrement,"
				+ "name text not null," + "age integer," + "sex text)");
		db.execSQL("insert into stutbl(name,sex,age) values('张三','男',19)");
		db.execSQL("insert into stutbl(name,sex,age) values('李四','女',18)");
		db.execSQL("insert into stutbl(name,sex,age) values('王五','男',20)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
