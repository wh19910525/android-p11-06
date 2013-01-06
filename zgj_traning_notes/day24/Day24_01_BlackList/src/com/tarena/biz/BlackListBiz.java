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
	 * ��ѯ�������е����е绰����
	 * 
	 * @return
	 */
	public ArrayList<String> getNumbers() {
		return dao.getNumbers();
	}

	/**
	 * �ж�ָ�������Ƿ�����ں�������
	 * 
	 * @param number
	 * @return
	 */
	public boolean isEists(String number) {
		return dao.isEists(number);
	}

	/**
	 * �����������Ӻ���
	 * 
	 * @param number
	 * @return
	 */
	public long addNumber(String number) {
		return dao.addNumber(number);
	}

	/**
	 * �Ӻ��������Ƴ�ָ������
	 * 
	 * @param number
	 * @return
	 */
	public int removeNumber(String number) {
		return dao.removeNumber(number);
	}
}
