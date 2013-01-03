package com.tarena.day2203;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

public class Day22_03_ContactsActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ContentResolver cr = getContentResolver();
		// 向原始联系人表中插入空行，获取新行的id
		ContentValues values = new ContentValues();
		Uri uri = cr.insert(RawContacts.CONTENT_URI, values);
		Log.i("wanghai",uri.toString());
		long raw_contact_id = ContentUris.parseId(uri);

		// 使用原始联系人id，向data表中插入联系人数据
		values.clear();
		values.put(StructuredName.RAW_CONTACT_ID, raw_contact_id);
		values.put(StructuredName.DISPLAY_NAME, "张三风");
		values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		cr.insert(Data.CONTENT_URI, values);

		values.clear();
		values.put(Data.DATA1, "01065585859");
		values.put(Data.DATA2, 1);
		values.put(Data.RAW_CONTACT_ID, raw_contact_id);
		values.put(Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
		cr.insert(Data.CONTENT_URI, values);

		values.clear();
		values.put(Phone.NUMBER, "13841755665");
		values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		values.put(Phone.RAW_CONTACT_ID, raw_contact_id);
		values.put(Phone.TYPE, Phone.TYPE_MOBILE);
		cr.insert(Data.CONTENT_URI, values);
		// cr.insert(Phone.CONTENT_URI, values);

		values.clear();
		values.put(Email.RAW_CONTACT_ID, raw_contact_id);
		values.put(Email.MIMETYPE, Email.CONTENT_ITEM_TYPE);
		values.put(Email.DATA, "zhangsan@tarena.com.cn");
		values.put(Email.TYPE, Email.TYPE_WORK);
		cr.insert(Data.CONTENT_URI, values);

	}
}