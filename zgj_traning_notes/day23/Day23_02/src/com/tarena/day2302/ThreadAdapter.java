package com.tarena.day2302;

import java.util.ArrayList;

import com.tarena.entity.ThreadInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ThreadAdapter extends BaseAdapter {
	
	private ArrayList<ThreadInfo> threads;
	private LayoutInflater inflater;

	public void setThreads(ArrayList<ThreadInfo> threads) {
		if (threads != null)
			this.threads = threads;
		else
			this.threads = new ArrayList<ThreadInfo>();
	}

	public ThreadAdapter(Context context, ArrayList<ThreadInfo> threads) {
		this.setThreads(threads);
		this.inflater = LayoutInflater.from(context);
	}

	public void changeData(ArrayList<ThreadInfo> threads) {
		this.setThreads(threads);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return threads.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return threads.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return threads.get(position).getThreadId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_threads, null);
			holder = new ViewHolder();
			holder.tvBody = (TextView) convertView
					.findViewById(R.id.tvContent_Threads);
			holder.tvCount = (TextView) convertView
					.findViewById(R.id.tvCount_Threads);
			holder.tvDate = (TextView) convertView
					.findViewById(R.id.tvDate_Threads);
			holder.tvNumber = (TextView) convertView
					.findViewById(R.id.tvNumber_Threads);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ThreadInfo info = threads.get(position);

		holder.tvNumber.setText(info.getNumber());
		holder.tvCount.setText("" + info.getCount());
		holder.tvBody.setText(info.getBody());
		holder.tvDate.setText("" + info.getDate());

		return convertView;
	}

	class ViewHolder {
		private TextView tvNumber;
		private TextView tvBody;
		private TextView tvDate;
		private TextView tvCount;
	}
}
