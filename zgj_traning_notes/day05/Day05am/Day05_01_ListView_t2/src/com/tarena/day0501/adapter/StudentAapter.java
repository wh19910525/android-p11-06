package com.tarena.day0501.adapter;

import java.util.ArrayList;

import com.tarena.day0501.R;
import com.tarena.entity.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StudentAapter extends BaseAdapter {
	
	private ArrayList<Student> students;//
	private LayoutInflater inflater;
	
	public StudentAapter(Context context, ArrayList<Student> students){
		if(students!=null)
			this.students = students;//
		else
			this.students = new ArrayList<Student>();
		
		this.inflater = LayoutInflater.from(context);//
	}
	
	public int getCount() {//
		// TODO Auto-generated method stub
		return students.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//����item����
		View item = inflater.inflate(R.layout.item, null);//
		
		TextView tvId = (TextView)item.findViewById(R.id.tvId);
		TextView tvName = (TextView)item.findViewById(R.id.tvName);
		TextView tvSex = (TextView)item.findViewById(R.id.tvSex);
		TextView tvAge = (TextView)item.findViewById(R.id.tvAge);
		
		//����position�Ӽ����л�ȡָ������
		Student stu = students.get(position);//
		
		//������
		tvId.setText(stu.getId()+"");
		tvName.setText(stu.getName());
		tvSex.setText(stu.getSex());
		tvAge.setText(stu.getAge()+"");
		
		//���ذ������ݵ�item����
		return item;
	}

}
