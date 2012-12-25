package com.tarena.day1604;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Day16_04_dbActivity extends Activity {
	
	private EditText etName, etSex, etAge;
	private SQLiteDatabase db;

	private void setupView() {
		etName = (EditText) findViewById(R.id.etName);
		etAge = (EditText) findViewById(R.id.etAge);
		etSex = (EditText) findViewById(R.id.etSex);
	}

	public void doClick(View v) {
		String name = etName.getText().toString();
		String sex = etSex.getText().toString();
		int age = Integer.parseInt(etAge.getText().toString());
		// ���뵽���ݿ���
		// String sql = "insert into stutbl(name,sex,age) values('" + name + "','" + sex + "'," + age + ")";
		// db.execSQL(sql);

		ContentValues values = new ContentValues();//ContentValue������java��HashMap�࣬�����Լ�ֵ�Եķ�ʽ�������ݡ�
		values.put("name", name);
		values.put("sex", sex);
		values.put("age", age);

		db.insert("stutbl", null, values);//�м�Ĳ���ͨ����Ϊnull,��ָ�ڱ�����ֵ��û�е�ʱ�����Ĭ��ֵ

		values.clear();//��� ����
		values.put("sex", "����");
		values.put("age", 102);

		db.update("stutbl", values, "sex=?", new String[] { "��" });
		/*
		 * update stutbl set sex='����',age=102 where sex='��'
		 */

		db.delete("stutbl", "sex=?", new String[] { "Ů" });//ɾ�� sex="Ů"

		// Cursor c = db.rawQuery("select * from stutbl", null);
		Cursor c = db.query("stutbl", new String[] { "_id", "name", "sex", "age" }, "age>?", 
				new String[] { "100" }, null, null, null);
		String[] cols = c.getColumnNames();//��ȡ ���е��ֶ�
		while (c.moveToNext()) {
			for (String col : cols) {
				Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
			}
			Log.i("info", "=========================");
		}
		c.close();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// �������ݿ⼰��ṹ
		MyDBOpenHelper helper = new MyDBOpenHelper(this, "stu.db");
		db = helper.getDataBase();
		
		// ��ʼ������
		setupView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.close();
	}
}