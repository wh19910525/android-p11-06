package com.tarena.day2505;

import com.tarena.biz.ProcessBiz;
import com.tarena.entity.AppInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppAdapter extends BaseAdapter {
	private String[] pkgList;
	private LayoutInflater inflater;
	private ProcessBiz biz;

	public AppAdapter(Context context, String[] pkgList) {
		this.pkgList = pkgList;
		this.inflater = LayoutInflater.from(context);
		this.biz = new ProcessBiz(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (pkgList != null)
			return pkgList.length;
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pkgList[position];
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
			convertView = inflater.inflate(R.layout.item_apps, null);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView
					.findViewById(R.id.ivIcon_App);
			holder.tvLable = (TextView) convertView
					.findViewById(R.id.tvLable_App);
			holder.tvPkgName = (TextView) convertView
					.findViewById(R.id.tvPkgName_App);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		AppInfo app = biz.getAppInfo(pkgList[position]);

		holder.ivIcon.setImageDrawable(app.getIcon());
		holder.tvLable.setText(app.getLable());
		holder.tvPkgName.setText(app.getPkgName());

		return convertView;
	}

	class ViewHolder {
		private ImageView ivIcon;
		private TextView tvLable;
		private TextView tvPkgName;

	}

}
