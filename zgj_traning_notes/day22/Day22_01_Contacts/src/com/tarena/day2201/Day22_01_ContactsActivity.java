package com.tarena.day2201;

import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.util.Log;

public class Day22_01_ContactsActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ContactsContract.RawContacts
		// ContactsContract.Contacts
		// ContactsContract.Data
		// ContactsContract.CommonDataKinds.Phone
		// ContactsContract.CommonDataKinds.Email
		// ContactsContract.CommonDataKinds.Photo
		// ContactsContract.CommonDataKinds.StructuredName
		// ContactsContract.CommonDataKinds.Im

		// Contacts.CONTENT_URI 指向  /data/data/com.android.providers.contacts/databases/contacts2.db 里的 raw_contacts 表
		Log.i("info", "Contacts.CONTENT_URI:" + Contacts.CONTENT_URI.toString());
		// Contacts._ID
		// Contacts.DISPLAY_NAME
		// Contacts.openContactPhotoInputStream(cr, contactUri)
////////////////////////////////////////
		
		// Data.CONTENT_URI
		Log.i("info", "Data.CONTENT_URI:" + Data.CONTENT_URI.toString());
		// Data._ID
		// Data.DATA1
		// Data.DATA15
		// Data.MIMETYPE
		// Data.RAW_CONTACT_ID
////////////////////////////////////////
		// Phone.CONTENT_URI
		Log.i("info", "Phone.CONTENT_URI:" + Phone.CONTENT_URI.toString());
		// Phone._ID
		// Phone.NUMBER
		// Phone.MIMETYPE
		// Phone.TYPE
		// Phone.TYPE_HOME
		// Data.DATA2 == 1 ==== Phone.Type==Phone.TYPE_HOME

////////////////////////////////////////
		// Email.CONTENT_URI
		Log.i("info", "Email.CONTENT_URI:" + Email.CONTENT_URI.toString());
		// Email._ID
		// Email.MIMETYPE
		// Email.DATA
		// Email.TYPE
		// Email.TYPE_HOME
		// Email.TYPE_WORK

		// StructuredName._ID
		// StructuredName.CONTACT_ID
		// StructuredName.MIMETYPE
		// StructuredName.DISPLAY_NAME

		ContentResolver cr = getContentResolver();
		String[] projection = { Contacts._ID, Contacts.DISPLAY_NAME };
		Cursor c = cr.query(Contacts.CONTENT_URI, projection, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				// 获取联系人id
				int id = c.getInt(c.getColumnIndex(Contacts._ID));
				// 输出姓名
				Log.i("info", "姓名:" + c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME)));
				
				// 查询指定联系人的电话信息
				// Cursor c1 = cr.query(Data.CONTENT_URI,
				// new String[] { Data.DATA1 }, Data.CONTACT_ID
				// + "=? and " + Data.MIMETYPE + "=?",
				// new String[] { id + "",
				// "vnd.android.cursor.item/phone_v2" }, null);

				Cursor c1 = cr.query(Phone.CONTENT_URI, new String[] { Phone.NUMBER }, 
						Phone.CONTACT_ID + "=" + id, null, null);
				if (c1 != null) {
					while (c1.moveToNext()) {
						Log.i("info", "电话号码:" + c1.getString(0));
					}
					c1.close();
				}

				// 查询指定联系人的邮箱信息
				// c1 = cr.query(Data.CONTENT_URI, new String[] { Data.DATA1 },
				// Data.CONTACT_ID + "=? and " + Data.MIMETYPE + "=?",
				// new String[] { id + "",
				// "vnd.android.cursor.item/email_v2" }, null);

				c1 = cr.query(Email.CONTENT_URI, new String[] { Email.DATA },
						Email.CONTACT_ID + "=" + id, null, null);
				if (c1 != null) {
					while (c1.moveToNext()) {
						Log.i("info", "邮箱信息:" + c1.getString(0));
					}
					c1.close();
				}

				// 头像
				InputStream in = Contacts.openContactPhotoInputStream(cr,
						ContentUris.withAppendedId(Contacts.CONTENT_URI, id));
				Bitmap bm = BitmapFactory.decodeStream(in);
				Log.i("info", "头像:" + bm);

				Log.i("info", "==========================");
			}
			c.close();
		}
	}
}