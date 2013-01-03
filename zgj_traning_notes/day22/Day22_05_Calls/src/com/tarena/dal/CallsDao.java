package com.tarena.dal;

import java.util.ArrayList;

import cm.tarena.entity.CallLogInfo;
import cm.tarena.entity.CallsInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog.Calls;

public class CallsDao {
	private ContentResolver cr;

	public CallsDao(Context context) {
		cr = context.getContentResolver();
	}

	public ArrayList<CallLogInfo> getCallLogs() {
		ArrayList<CallLogInfo> infos = null;
		String[] projection = { Calls.NUMBER, "count(*) as cnt" };
		Cursor c = cr.query(Calls.CONTENT_URI, projection,
				"1=1) group by (number", null, null);
		if (c != null) {
			infos = new ArrayList<CallLogInfo>();
			while (c.moveToNext()) {
				CallLogInfo info = new CallLogInfo();
				info.setNumber(c.getString(c.getColumnIndex(Calls.NUMBER)));
				info.setCount(c.getInt(c.getColumnIndex("cnt")));
				infos.add(info);
			}
			c.close();
		}
		return infos;
	}

	public ArrayList<CallsInfo> getCallsInfo(String number) {
		ArrayList<CallsInfo> infos = null;
		String[] projection = { Calls.DATE, Calls.DURATION, Calls.TYPE };
		Cursor c = cr.query(Calls.CONTENT_URI, projection, Calls.NUMBER + "=?",
				new String[] { number }, null);
		if (c != null) {
			infos = new ArrayList<CallsInfo>();
			while (c.moveToNext()) {
				CallsInfo info = new CallsInfo();
				info.setNumber(number);
				info.setDate(c.getLong(c.getColumnIndex(Calls.DATE)));
				info.setDuration(c.getInt(c.getColumnIndex(Calls.DURATION)));
				info.setType(c.getInt(c.getColumnIndex(Calls.TYPE)));

				infos.add(info);
			}
			c.close();
		}
		return infos;
	}
}
