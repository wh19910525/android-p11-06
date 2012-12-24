package com.tarena.day1604;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DBOpenHelper {
	
	private SQLiteDatabase db;

	public DBOpenHelper(Context context, String dbName) {
		boolean exists = context.getDatabasePath(dbName).exists();
		db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);//�������ݿ�
		if (!exists) {
			// ����ʹ�����ʱ,�������ݿ��
			onCreate(db);
		}
	}

	public SQLiteDatabase getDataBase() {
		return db;
	}

	protected abstract void onCreate(SQLiteDatabase db);
}
