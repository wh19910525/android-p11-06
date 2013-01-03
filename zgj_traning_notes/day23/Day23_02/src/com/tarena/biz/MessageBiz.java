package com.tarena.biz;

import java.util.ArrayList;

import android.content.Context;

import com.tarena.dal.MessageDao;
import com.tarena.entity.MessageInfo;
import com.tarena.entity.ThreadInfo;

public class MessageBiz {
	private MessageDao dao;

	public MessageBiz(Context context) {
		dao = new MessageDao(context);
	}

	public ArrayList<ThreadInfo> getThreads() {
		return dao.getThreads();
	}

	public ArrayList<MessageInfo> getMessages(int thread_id) {
		return dao.getMessages(thread_id);
	}
}
