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
		// 插入到数据库中
		// String sql = "insert into stutbl(name,sex,age) values('" + name + "','" + sex + "'," + age + ")";
		// db.execSQL(sql);

		ContentValues values = new ContentValues();//ContentValue类似于java中HashMap类，用于以键值对的方式保存数据。
		values.put("name", name);
		values.put("sex", sex);
		values.put("age", age);

		db.insert("stutbl", null, values);//中间的参数通常设为null,是指在表名和值都没有的时候设的默认值

		values.clear();//清除 内容
		values.put("sex", "中性");
		values.put("age", 102);

		db.update("stutbl", values, "sex=?", new String[] { "男" });
		/*
		 * update stutbl set sex='中性',age=102 where sex='男'
		 */

		db.delete("stutbl", "sex=?", new String[] { "女" });//删除 sex="女"

		// Cursor c = db.rawQuery("select * from stutbl", null);
		Cursor c = db.query("stutbl", new String[] { "_id", "name", "sex", "age" }, "age>?", 
				new String[] { "100" }, null, null, null);
		String[] cols = c.getColumnNames();//获取 所有的字段
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
		
		// 创建数据库及表结构
		MyDBOpenHelper helper = new MyDBOpenHelper(this, "stu.db");
		db = helper.getDataBase();
		
		// 初始化界面
		setupView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.close();
	}
}