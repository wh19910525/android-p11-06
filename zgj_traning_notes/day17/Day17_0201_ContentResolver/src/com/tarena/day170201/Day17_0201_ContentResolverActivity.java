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
		Uri uri = Uri.parse("content://com.tarena.providers.stu/student");//随意 指定，但要和AndroidManifest.xml里的一致;

		ContentValues values = new ContentValues();
		values.put("name", "王二麻子");
		values.put("sex", "男");
		values.put("age", "50");
		cr.insert(uri, values);

		values.clear();
		values.put("age", 21);
		cr.update(uri, values, "name=?", new String[] { "王二麻子" });

		//更新表中id为3的age字段 更新为52
		cr.update(uri, values, "_id=3", null);
		//更新表中id为2的age字段 更新为52
		cr.update(ContentUris.withAppendedId(uri, 2), values, null, null);//withAppendedId 为路径 加上ID部分
		//删除表中 id为1的记录
		cr.delete(ContentUris.withAppendedId(uri, 1), null, null);
		
//		cr.delete(ContentUris.withAppendedId(uri, 2), null, null);
//		cr.delete(ContentUris.withAppendedId(uri, 4), null, null);
		Cursor c = cr.query(uri, null, null, null, "age desc");//获取 student表 中 所有记录, 最后 一个 参数 没用，可以用 null；
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