package com.tarena.dal;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;

import com.tarena.entity.Contact;
import com.tarena.entity.EmailInfo;
import com.tarena.entity.PhoneInfo;

public class ContactDao {
	
	private ContentResolver cr;

	public ContactDao(Context context) {
		cr = context.getContentResolver();
	}

	/**
	 * 查询联系人的基本信息
	 * 
	 * @return
	 */
	public ArrayList<Contact> getContacts() {
		
		ArrayList<Contact> contacts = null;
		Cursor c = cr.query(Contacts.CONTENT_URI, new String[] { "_id", "display_name" }, null, null, null);
		if (c != null) {
			contacts = new ArrayList<Contact>();
			while (c.moveToNext()) {
				Contact contact = new Contact();
				contact.setId(c.getInt(c.getColumnIndex("_id")));
				contact.setName(c.getString(c.getColumnIndex("display_name")));

				contacts.add(contact);
			}
			c.close();
		}
		return contacts;
	}

	/**
	 * 根据联系人id 查询头像
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getPhoto(int id) {
		Bitmap bm = null;
		Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
		InputStream is = Contacts.openContactPhotoInputStream(cr, uri);
		bm = BitmapFactory.decodeStream(is);
		return bm;
	}

	/**
	 * 根据联系人id 查询邮箱信息
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<EmailInfo> getEmails(int id) {
		ArrayList<EmailInfo> emails = null;
		Cursor c = cr.query(Email.CONTENT_URI, new String[] { Email.DATA, Email.TYPE }, 
				Email.CONTACT_ID + "=" + id, null, null);
		if (c != null) {
			emails = new ArrayList<EmailInfo>();
			while (c.moveToNext()) {
				EmailInfo info = new EmailInfo();
				info.setEmail(c.getString(c.getColumnIndex(Email.DATA)));
				info.setType(c.getInt(c.getColumnIndex(Email.TYPE)));

				emails.add(info);
			}
		}
		return emails;
	}

	/**
	 * 根据联系人id 查询电话信息
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<PhoneInfo> getPhonse(int id) {
		ArrayList<PhoneInfo> phones = null;
		Cursor c = cr.query(Phone.CONTENT_URI, new String[] { Phone.NUMBER, Phone.TYPE }, 
				Phone.CONTACT_ID + "=" + id, null, null);
		if (c != null) {
			phones = new ArrayList<PhoneInfo>();
			while (c.moveToNext()) {
				PhoneInfo info = new PhoneInfo();
				info.setNumber(c.getString(c.getColumnIndex(Phone.NUMBER)));
				info.setType(c.getInt(c.getColumnIndex(Phone.TYPE)));

				phones.add(info);
			}
		}
		return phones;
	}
}
