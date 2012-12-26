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
		//execSQL 执行的 sql语句无返回值，所以 只能 执行 增 删 改 语句,而不能执行 查询语句;
		db.execSQL("create table if not exists usertbl("
				+ "_id integer primary key autoincrement,"//主键id 自动创建
				+ "username text not null," + "userpass text not null" + ")");//每次 执行insert 本 字段 不能为空

		db.execSQL("insert into usertbl(username,userpass) values('admin','123456')");
		db.execSQL("insert into usertbl(username,userpass) values('zhangsan','111111')");
		db.execSQL("insert into usertbl(username,userpass) values('lisi','222222')");
		db.execSQL("insert into usertbl(username,userpass) values('wangwu','333333')");

		db.execSQL("update usertbl set userpass='888888' where _id>1");

		db.execSQL("delete from usertbl where username='zhangsan'");

		//如果执行查询语句 只能 用 游标Cursor，这样可以返回值;
		Cursor c = db.rawQuery("select * from usertbl where username like ?", new String[] { "%a%" });//查询usertbl表的所有字段，条件是 username里包含a字符;
		// * 代表 所有 字段 ，？代表任意一个字符，%a%代表  ？取 a值;
		///moveTonext 第一次 指向 第一条，第二次 指向 第二条，以此类推;
		Log.i("wanghai", c.getCount() + "");
		while (c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex("_id"));//getColumnIndex 获取 指定字段的 索引，这一字段的id值
			String name = c.getString(c.getColumnIndex("username"));//获取username这一字段的 值
			String pass = c.getString(c.getColumnIndex("userpass"));
			
			Log.i("-id", c.getColumnIndex("_id") + "");
			Log.i("username", c.getColumnIndex("username") + "");
			Log.i("userpass", c.getColumnIndex("userpass") + "");
			
			Log.i("info", id + "," + name + "," + pass);
		}
		c.close();
		db.close();
	}
}