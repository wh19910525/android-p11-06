package com.tarena.biz;

import java.util.ArrayList;

import android.content.Context;

import cm.tarena.entity.CallLogInfo;
import cm.tarena.entity.CallsInfo;

import com.tarena.dal.CallsDao;

public class CallsBiz {
	private CallsDao dao;

	public CallsBiz(Context context) {
		dao = new CallsDao(context);
	}

	public ArrayList<CallLogInfo> getCallLogs() {
		return dao.getCallLogs();
	}

	public ArrayList<CallsInfo> getCallsInfo(String number) {
		return dao.getCallsInfo(number);
	}
}
