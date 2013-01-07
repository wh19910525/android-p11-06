package com.tarena.day2502;

import com.tarena.entity.AppInfo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppAdapter extends BaseAdapter {
	
	private List<AppInfo> apps;
	private LayoutInflater inflater;

	public AppAdapter(Context context, List<AppInfo> apps) {
		setApps(apps);
		this.inflater = LayoutInflater.from(context);
	}

	private void setApps(List<AppInfo> apps) {
		if (apps != null)
			this.apps = apps;
		else
			this.apps = new ArrayList<AppInfo>();
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		AppInfo app = apps.get(position);

		holder.ivIcon.setImageDrawable(app.getIcon());
		holder.tvTitle.setText(app.getTitle());

		return convertView;
	}

	public void changData(List<AppInfo> apps) {
		this.setApps(apps);
		this.notifyDataSetChanged();
	}

	class ViewHolder {
		private ImageView ivIcon;
		private TextView tvTitle;
	}

}
