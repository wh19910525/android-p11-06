package com.tarena.day2502;

import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.Launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LauncherAdapter extends BaseAdapter {
	
	private List<Launcher> launchers;
	private LayoutInflater inflater;

	public LauncherAdapter(Context context, List<Launcher> launchers) {
		if (launchers != null)
			this.launchers = launchers;
		else
			this.launchers = new ArrayList<Launcher>();

		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return launchers.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return launchers.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
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

		Launcher launcher = launchers.get(position);

		holder.ivIcon.setImageDrawable(launcher.getIcon());
		holder.tvTitle.setText(launcher.getTitle());

		return convertView;
	}

	class ViewHolder {
		private ImageView ivIcon;
		private TextView tvTitle;
	}

}
