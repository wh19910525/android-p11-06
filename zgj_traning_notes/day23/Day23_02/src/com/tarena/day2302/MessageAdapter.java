package com.tarena.day2302;

import java.util.ArrayList;

import com.tarena.entity.MessageInfo;
import com.tarena.entity.ThreadInfo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {
	private ArrayList<MessageInfo> messages;
	private LayoutInflater inflater;

	public void setThreads(ArrayList<MessageInfo> messages) {
		if (messages != null)
			this.messages = messages;
		else
			this.messages = new ArrayList<MessageInfo>();
	}

	public MessageAdapter(Context context, ArrayList<MessageInfo> messages) {
		this.setThreads(messages);
		this.inflater = LayoutInflater.from(context);
	}

	public void changeData(ArrayList<MessageInfo> threads) {
		this.setThreads(threads);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messages.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return messages.get(position);
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
			convertView = inflater.inflate(R.layout.item_details, null);
			holder = new ViewHolder();
			holder.tvBody = (TextView) convertView
					.findViewById(R.id.tvContent_Details);
			holder.tvDate = (TextView) convertView
					.findViewById(R.id.tvDate_Details);
			holder.tvNumber = (TextView) convertView
					.findViewById(R.id.tvNumber_Details);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		MessageInfo info = messages.get(position);

		switch (info.getType()) {
		case 1:// 收件箱
			holder.tvNumber.setText(info.getNumber() + ":");
			holder.tvNumber.setTextColor(Color.GRAY);
			break;

		case 2:// 发件箱
			holder.tvNumber.setText("ME:");
			holder.tvNumber.setTextColor(Color.BLUE);
			break;
		}

		holder.tvBody.setText(info.getBody());
		holder.tvDate.setText("" + info.getDate());

		return convertView;
	}

	class ViewHolder {
		private TextView tvNumber;
		private TextView tvBody;
		private TextView tvDate;
	}
}
