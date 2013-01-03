package com.tarena.day2205;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cm.tarena.entity.CallLogInfo;
import cm.tarena.entity.CallsInfo;

import android.content.Context;
import android.provider.CallLog.Calls;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CallsAdapter extends BaseAdapter {
	private ArrayList<CallsInfo> infos;
	private LayoutInflater inflater;

	public void setInfos(ArrayList<CallsInfo> infos) {
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<CallsInfo>();
	}

	public CallsAdapter(Context context, ArrayList<CallsInfo> infos) {
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
			convertView = inflater.inflate(R.layout.item_dtails, null);
			holder = new ViewHolder();
			holder.tvNumber = (TextView) convertView
					.findViewById(R.id.tvNumber_Details);
			holder.tvDate = (TextView) convertView
					.findViewById(R.id.tvDate_Details);
			holder.tvDuration = (TextView) convertView
					.findViewById(R.id.tvDuration_Details);
			holder.tvType = (TextView) convertView
					.findViewById(R.id.tvType_Details);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CallsInfo info = infos.get(position);

		holder.tvNumber.setText(info.getNumber());
		holder.tvDate.setText(format(info.getDate()));
		holder.tvDuration.setText(format(info.getDuration()));
		
		String type = info.getType() == Calls.INCOMING_TYPE ? "来电" : "去电";
		holder.tvType.setText(type);

		return convertView;
	}

	private SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");

	private String format(long date) {
		return formatter.format(new Date(date));
	}

	private String format(int duration) {
		return formatter1.format(new Date(duration * 1000));
	}

	class ViewHolder {
		private TextView tvNumber;
		private TextView tvDate;
		private TextView tvDuration;
		private TextView tvType;
	}

}
