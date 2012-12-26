package com.tarena.day170201;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Day17_0201_ContentResolverActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ContentResolver cr = getContentResolver();//
		Uri uri = Uri.parse("content://com.tarena.providers.stu/student");//���� ָ������Ҫ��AndroidManifest.xml���һ��;

		ContentValues values = new ContentValues();
		values.put("name", "��������");
		values.put("sex", "��");
		values.put("age", "50");
		cr.insert(uri, values);

		values.clear();
		values.put("age", 21);
		cr.update(uri, values, "name=?", new String[] { "��������" });

		//���±���idΪ3��age�ֶ� ����Ϊ52
		cr.update(uri, values, "_id=3", null);
		//���±���idΪ2��age�ֶ� ����Ϊ52
		cr.update(ContentUris.withAppendedId(uri, 2), values, null, null);//withAppendedId Ϊ·�� ����ID����
		//ɾ������ idΪ1�ļ�¼
		cr.delete(ContentUris.withAppendedId(uri, 1), null, null);
		
//		cr.delete(ContentUris.withAppendedId(uri, 2), null, null);
//		cr.delete(ContentUris.withAppendedId(uri, 4), null, null);
		Cursor c = cr.query(uri, null, null, null, "age desc");//��ȡ student�� �� ���м�¼, ��� һ�� ���� û�ã������� null��
		if (c != null) {
			while (c.moveToNext()) {
				String[] cols = c.getColumnNames();
				for (String col : cols) {
					Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "===================");
			}
			c.close();
		}
	}
}