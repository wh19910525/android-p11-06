package com.tarena.dal;

import java.util.ArrayList;

import com.tarena.entity.MessageInfo;
import com.tarena.entity.ThreadInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class MessageDao {
	
	private ContentResolver cr;

	public MessageDao(Context context) {
		cr = context.getContentResolver();
	}

	/**
	 * 查询 所有的会话信息
	 * 
	 * @return
	 */
	public ArrayList<ThreadInfo> getThreads() {
		ArrayList<ThreadInfo> threads = null;
		String[] projection = { "thread_id", "address", "body", "date", "count(*)as cnt" };
		Cursor c = cr.query(Uri.parse("content://sms"), projection, 
				"type=1 or type=2) group by(thread_id", null, null);
		//以上  这一句 查询语句 翻译为 sqlite是: select thread_id,address,body,date,type from sms where (type=1 or type=2) group by(thread_id)
		if (c != null) {
			threads = new ArrayList<ThreadInfo>();
			while (c.moveToNext()) {
				ThreadInfo info = new ThreadInfo();
				info.setThreadId(c.getInt(c.getColumnIndex("thread_id")));
				info.setNumber(c.getString(c.getColumnIndex("address")));
				info.setDate(c.getLong(c.getColumnIndex("date")));
				info.setBody(c.getString(c.getColumnIndex("body")));
				info.setCount(c.getInt(c.getColumnIndex("cnt")));
				threads.add(info);
			}
			c.close();
		}
		return threads;
	}

	/**
	 * 根据会话id 查询会话内的短信详情
	 * 
	 * @param thread_id
	 * @return
	 */
	public ArrayList<MessageInfo> getMessages(int thread_id) {
		ArrayList<MessageInfo> messages = null;
		String[] projection = { "address", "date", "type", "body" };
		Cursor c = cr.query(Uri.parse("content://sms"), projection,
				"thread_id=" + thread_id, null, "date");
		if (c != null) {
			messages = new ArrayList<MessageInfo>();
			while (c.moveToNext()) {
				MessageInfo info = new MessageInfo();
				info.setNumber(c.getString(c.getColumnIndex("address")));
				info.setBody(c.getString(c.getColumnIndex("body")));
				info.setDate(c.getLong(c.getColumnIndex("date")));
				info.setType(c.getInt(c.getColumnIndex("type")));
				messages.add(info);
			}
		}
		return messages;
	}
}
