package com.tarena.day0501;

import java.util.ArrayList;

import com.tarena.biz.StudentBiz;
import com.tarena.entity.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Day05_01_ListViewActivity extends Activity {
	
    private ListView lvStudents;
    private StudentBiz biz;
    private LayoutInflater inflater;
    
    private void setupView(){
    	
    	//��ȡListView������
    	lvStudents = (ListView)findViewById(R.id.lvStudents);
    	
    	//��ȡ���ݼ�
    	ArrayList<Student> students = biz.getStudents();
    	
    	//�������ݼ�
    	if(students!=null && !students.isEmpty()){
    		
    		for(Student stu : students){//���� students �������
    			//����item����
    			View item = inflater.inflate(R.layout.item, null);
    			
    			TextView tvId = (TextView)item.findViewById(R.id.tvId);
    			TextView tvName = (TextView)item.findViewById(R.id.tvName);
    			TextView tvSex = (TextView)item.findViewById(R.id.tvSex);
    			TextView tvAge = (TextView)item.findViewById(R.id.tvAge);
    			
    			//������
    			tvId.setText(stu.getId()+"");
    			tvName.setText(stu.getName());
    			tvSex.setText(stu.getSex());
    			tvAge.setText(stu.getAge()+"");
    			
    			//��item��ӵ�listView
    			lvStudents.addView(item);
    		}
    	}	
    }
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        biz = new StudentBiz();//
        inflater = LayoutInflater.from(this);//��xml�ļ�����һ��View����, ���ز���
        setupView();
    }
}