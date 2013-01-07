package com.tarena.day2505;

import java.util.ArrayList;
import java.util.List;

import com.tarena.biz.ProcessBiz;
import com.tarena.entity.ProcessInfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProcessAdapter extends BaseAdapter {
	private List<ProcessInfo> processes;
	private LayoutInflater inflater;
	private ProcessBiz biz;

	public void changeData(List<ProcessInfo> process) {
		this.setProcesses(process);
		this.notifyDataSetChanged();
	}

	public void setProcesses(List<ProcessInfo> processes) {
		if (processes != null)
			this.processes = processes;
		else
			this.processes = new ArrayList<ProcessInfo>();

	}

	public ProcessAdapter(Context context, List<ProcessInfo> processes) {
		this.setProcesses(processes);
		this.inflater = LayoutInflater.from(context);
		this.biz = new ProcessBiz(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return processes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return processes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return processes.get(position).getProcessId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_process, null);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView
					.findViewById(R.id.ivIcon_Process);
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.tvProcessName_Process);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ProcessInfo process = processes.get(position);

		Drawable icon = biz.getIcon(process.getPkgList()[0]);
		if (icon != null)
			holder.ivIcon.setImageDrawable(icon);
		else
			holder.ivIcon.setImageResource(R.drawable.ic_launcher);

		holder.tvTitle.setText(process.getProcessName());

		return convertView;
	}

	class ViewHolder {
		private ImageView ivIcon;
		private TextView tvTitle;
	}
}
