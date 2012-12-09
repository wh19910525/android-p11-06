package com.tarena.day0703.adapter;

import java.util.ArrayList;

import com.tarena.day0703.R;
import com.tarena.entity.AppInfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppAdapter extends BaseAdapter {
	
	private ArrayList<AppInfo> apps;
	private LayoutInflater inflater;

	public AppAdapter(Context context, ArrayList<AppInfo> apps) {
		if (apps != null)
			this.apps = apps;
		else
			this.apps = new ArrayList<AppInfo>();
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return apps.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return apps.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("info", "position=" + position);
		//����item����
		convertView = inflater.inflate(R.layout.item, null);
		ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

		//����position�Ӽ����л�ȡָ������
		AppInfo app = apps.get(position);

		//������
		ivIcon.setImageResource(app.getIconRes());
		tvTitle.setText(app.getTitle());
		
		//���ذ������ݵ�item����
		return convertView;
	}
}
