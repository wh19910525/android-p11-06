package com.tarena.day0601.adapter;

import com.tarena.day0601.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeekAdapter extends BaseAdapter {
	private String[] days;
	private LayoutInflater inflater;

//	public void setDays(String[] days){
//		if(day!=null)
////			...
//	}
	
	public WeekAdapter(Context context, String[] days) {
		this.days = days;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(days!=null)
			return days.length;
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(days!=null)
			return days[position];
		return null;
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
		TextView tvDay = null;
		if(convertView==null){
			convertView = inflater.inflate(R.layout.item_week, null);
			tvDay = (TextView)convertView.findViewById(R.id.tvDay);
			convertView.setTag(tvDay);
		}else{
			tvDay = (TextView)convertView.getTag();
		}
		//获取指定位置的数据
		String day = days[position];
		
		//数据写入到item界面
		tvDay.setText(day);
		
		//返回item界面
		return convertView;
	}

}
