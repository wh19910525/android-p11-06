package com.tarena.day2205;

import java.util.ArrayList;

import cm.tarena.entity.CallLogInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CallLogAdapter extends BaseAdapter {
	private ArrayList<CallLogInfo> infos;
	private LayoutInflater inflater;

	public void setInfos(ArrayList<CallLogInfo> infos) {
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<CallLogInfo>();
	}

	public CallLogAdapter(Context context, ArrayList<CallLogInfo> infos) {
		this.setInfos(infos);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
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
			holder.tvNumber = (TextView) convertView
					.findViewById(R.id.tvNumber);
			holder.tvCount = (TextView) convertView.findViewById(R.id.tvCount);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CallLogInfo info = infos.get(position);

		holder.tvNumber.setText(info.getNumber());
		holder.tvCount.setText("" + info.getCount());
		
		return convertView;
	}

	class ViewHolder {
		private TextView tvNumber;
		private TextView tvCount;
	}

}
