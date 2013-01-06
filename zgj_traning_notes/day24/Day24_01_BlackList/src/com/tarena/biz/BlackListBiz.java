package com.tarena.biz;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tarena.dal.BlackListDao;

public class BlackListBiz {
	private BlackListDao dao;

	public BlackListBiz(Context context) {
		dao = new BlackListDao(context);
	}

	/**
	 * 查询黑名单中的所有电话号码
	 * 
	 * @return
	 */
	public ArrayList<String> getNumbers() {
		return dao.getNumbers();
	}

	/**
	 * 判断指定号码是否存在于黑名单中
	 * 
	 * @param number
	 * @return
	 */
	public boolean isEists(String number) {
		return dao.isEists(number);
	}

	/**
	 * 向黑名单中添加号码
	 * 
	 * @param number
	 * @return
	 */
	public long addNumber(String number) {
		return dao.addNumber(number);
	}

	/**
	 * 从黑名单中移除指定号码
	 * 
	 * @param number
	 * @return
	 */
	public int removeNumber(String number) {
		return dao.removeNumber(number);
	}
}
