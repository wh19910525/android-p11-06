package com.tarena.day0602.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends BaseAdapter {
	private ArrayList<String> data;
	private LayoutInflater inflater;
	private int layoutRes;
	private int textViewIdRes;

	public MyArrayAdapter(Context context, int layoutRes, int textViewIdRes,
			ArrayList<String> data) {
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

	public void add(String item){
		data.add(item);
		notifyDataSetChanged();
	}
	
	public void remove(String item){
		data.remove(item);
		notifyDataSetChanged();
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
		TextView tv = null;
		if(convertView==null){
			convertView = inflater.inflate(layoutRes, null);
			if(textViewIdRes==0)
				tv = (TextView)convertView;
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
		
		tv.setText(data.get(position));
		
		return convertView;
	}

}
