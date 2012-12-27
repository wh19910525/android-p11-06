package com.tarena.day1904;

import java.util.ArrayList;

import android.app.Application;

public class MyApplication extends Application {
	
	private ArrayList<String> list;

	public void addItem(String item) {
		list.add(item);
	}

	public void removeItem(int position) {
		if (position >= 0 && position < list.size())
			list.remove(position);
	}

	public void removeItem(String item) {
		list.remove(item);
	}

	public ArrayList<String> getList() {
		return list;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("3");
		list.add("4");
	}
}
