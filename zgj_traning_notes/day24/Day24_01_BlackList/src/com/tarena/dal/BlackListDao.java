package com.tarena.dal;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BlackListDao {
	private DBOpenHelper helper;

	public BlackListDao(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * ��ѯ�������е����е绰����
	 * 
	 * @return
	 */
	public ArrayList<String> getNumbers() {
		ArrayList<String> numbers = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select number from blacklist", null);
		if (c != null) {
			numbers = new ArrayList<String>();
			while (c.moveToNext()) {
				numbers.add(c.getString(0));
			}
			c.close();
		}
		db.close();
		return numbers;
	}

	/**
	 * �ж�ָ�������Ƿ�����ں�������
	 * 
	 * @param number
	 * @return
	 */
	public boolean isEists(String number) {
		boolean exists = false;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from blacklist where number=?",
				new String[] { number });
		if (c != null && c.moveToFirst()) {
			exists = true;
			c.close();
		}
		db.close();
		return exists;
	}

	/**
	 * �����������Ӻ���
	 * 
	 * @param number
	 * @return
	 */
	public long addNumber(String number) {
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("number", number);
		rowId = db.insert("blacklist", null, values);
		db.close();
		return rowId;
	}

	/**
	 * �Ӻ��������Ƴ�ָ������
	 * 
	 * @param number
	 * @return
	 */
	public int removeNumber(String number) {
		int count = 0;
		SQLiteDatabase db = helper.getWritableDatabase();
		count = db.delete("blacklist", "number=?", new String[]{number});
		db.close();
		return count;
	}
}
