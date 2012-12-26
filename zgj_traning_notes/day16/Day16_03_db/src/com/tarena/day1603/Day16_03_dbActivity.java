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
		//execSQL ִ�е� sql����޷���ֵ������ ֻ�� ִ�� �� ɾ �� ���,������ִ�� ��ѯ���;
		db.execSQL("create table if not exists usertbl("
				+ "_id integer primary key autoincrement,"//����id �Զ�����
				+ "username text not null," + "userpass text not null" + ")");//ÿ�� ִ��insert �� �ֶ� ����Ϊ��

		db.execSQL("insert into usertbl(username,userpass) values('admin','123456')");
		db.execSQL("insert into usertbl(username,userpass) values('zhangsan','111111')");
		db.execSQL("insert into usertbl(username,userpass) values('lisi','222222')");
		db.execSQL("insert into usertbl(username,userpass) values('wangwu','333333')");

		db.execSQL("update usertbl set userpass='888888' where _id>1");

		db.execSQL("delete from usertbl where username='zhangsan'");

		//���ִ�в�ѯ��� ֻ�� �� �α�Cursor���������Է���ֵ;
		Cursor c = db.rawQuery("select * from usertbl where username like ?", new String[] { "%a%" });//��ѯusertbl��������ֶΣ������� username�����a�ַ�;
		// * ���� ���� �ֶ� ������������һ���ַ���%a%����  ��ȡ aֵ;
		///moveTonext ��һ�� ָ�� ��һ�����ڶ��� ָ�� �ڶ������Դ�����;
		Log.i("wanghai", c.getCount() + "");
		while (c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex("_id"));//getColumnIndex ��ȡ ָ���ֶε� ��������һ�ֶε�idֵ
			String name = c.getString(c.getColumnIndex("username"));//��ȡusername��һ�ֶε� ֵ
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