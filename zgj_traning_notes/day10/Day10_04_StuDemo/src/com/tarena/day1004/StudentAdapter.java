package com.tarena.day1004;

import java.util.ArrayList;

import com.tarena.entity.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StudentAdapter extends BaseAdapter {
	private ArrayList<Student> students;
	private LayoutInflater inflater;

	public StudentAdapter(Context context, ArrayList<Student> students) {
		if (students != null)
			this.students = students;
		else
			this.students = new ArrayList<Student>();

		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return students.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return students.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return students.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvSex = (TextView) convertView.findViewById(R.id.tvSex);
			holder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Student stu = students.get(position);

		holder.tvId.setText("" + stu.getId());
		holder.tvAge.setText("" + stu.getAge());
		holder.tvName.setText(stu.getName());
		holder.tvSex.setText(stu.getSex());

		return convertView;
	}

	class ViewHolder {
		private TextView tvId;
		private TextView tvName;
		private TextView tvSex;
		private TextView tvAge;
	}
}
