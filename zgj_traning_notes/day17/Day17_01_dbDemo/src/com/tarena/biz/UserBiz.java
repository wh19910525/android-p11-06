package com.tarena.biz;

import android.content.Context;

import com.tarena.dal.UserDao;
import com.tarena.entity.User;

public class UserBiz {
	private UserDao dao;
	public UserBiz(Context context){
		dao = new UserDao(context);
	}
	
	public boolean isExists(User user) {
		return dao.isExists(user);
	}
}
