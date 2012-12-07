package com.tarena.day0602.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends BaseAdapter {
	
	private ArrayList<String> data;//
	private LayoutInflater inflater;
	private int layoutRes;
	private int textViewIdRes;

	public MyArrayAdapter(Context context, int layoutRes, int textViewIdRes, ArrayList<String> data) {
		if (data != null)
			this.data = data;
		else
			this.data = new ArrayList<String>();

		this.inflater = LayoutInflater.from(context);
		this.layoutRes = layoutRes;
		this.textViewIdRes = textViewIdRes;
	}

	public MyArrayAdapter(Context context, int layoutRes, ArrayList<String> data) {
		if (data != null)
			this.data = data;
		else
			this.data = new ArrayList<String>();

		this.inflater = LayoutInflater.from(context);
		this.layoutRes = layoutRes;
	}

	public MyArrayAdapter(Context context, int layoutRes, int textViewIdRes) {
		this.data = new ArrayList<String>();
		this.inflater = LayoutInflater.from(context);
		this.layoutRes = layoutRes;
		this.textViewIdRes = textViewIdRes;
	}

	public MyArrayAdapter(Context context, int layoutRes) {
		this.data = new ArrayList<String>();
		this.inflater = LayoutInflater.from(context);
		this.layoutRes = layoutRes;
	}

	public void add(String item){
		data.add(item);
		//更新界面
		notifyDataSetChanged();
	}
	
	public void remove(String item){
		data.remove(item);
		//更新界面
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//加载和重用item界面
		TextView tv = null;
		if(convertView==null){	
			convertView = inflater.inflate(layoutRes, null);
			if(textViewIdRes==0)
				tv = (TextView)convertView;//把这个View 强转换为TestView，因为 这个View里没有TestView 
			else{
				tv = (TextView)convertView.findViewById(textViewIdRes);
				convertView.setTag(tv);
			}
		}else{
			if(textViewIdRes==0)
				tv = (TextView)convertView;
			else{
				tv = (TextView)convertView.getTag();
			}
		}
		
		// 根据position 获取item数据, 并且 绑定数据
		tv.setText(data.get(position));
		
		//返回item界面
		return convertView;
	}
}
