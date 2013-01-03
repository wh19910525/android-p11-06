package com.tarena.biz;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;

import com.tarena.dal.ContactDao;
import com.tarena.entity.Contact;
import com.tarena.entity.EmailInfo;
import com.tarena.entity.PhoneInfo;

public class ContactBiz {
	
	private ContactDao dao;

	public ContactBiz(Context context) {
		dao = new ContactDao(context);
	}

	public ArrayList<Contact> getContacts() {
		return dao.getContacts();
	}

	public Bitmap getPhoto(int id) {
		return dao.getPhoto(id);
	}

	public ArrayList<PhoneInfo> getPhones(int id) {
		return dao.getPhonse(id);
	}

	public ArrayList<EmailInfo> getEmails(int id) {
		return dao.getEmails(id);
	}
}
