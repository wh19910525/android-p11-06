package com.tarena.day1603;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class Day16_03_dbActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
		db.execSQL("create table if not exists usertbl("
				+ "_id integer primary key autoincrement,"
				+ "username text not null," + "userpass text not null" + ")");

		db.execSQL("insert into usertbl(username,userpass) values('admin','123456')");
		db.execSQL("insert into usertbl(username,userpass) values('zhangsan','111111')");
		db.execSQL("insert into usertbl(username,userpass) values('lisi','222222')");
		db.execSQL("insert into usertbl(username,userpass) values('wangwu','333333')");

		db.execSQL("update usertbl set userpass='888888' where _id>1");

		db.execSQL("delete from usertbl where username='zhangsan'");

		Cursor c = db.rawQuery("select * from usertbl where username like ?", new String[] { "%a%" });// ≤√¥“‚Àº£ø
		
		while (c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex("_id"));
			String name = c.getString(c.getColumnIndex("username"));
			String pass = c.getString(c.getColumnIndex("userpass"));
			Log.i("info", id + "," + name + "," + pass);
		}
		c.close();
		db.close();
	}
}