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
    	
    	//获取ListView的引用
    	lvStudents = (ListView)findViewById(R.id.lvStudents);
    	
    	//获取数据集
    	ArrayList<Student> students = biz.getStudents();
    	
    	//遍历数据集
    	if(students!=null && !students.isEmpty()){
    		
    		for(Student stu : students){//遍历 students 这个集合
    			//加载item界面
    			View item = inflater.inflate(R.layout.item, null);
    			
    			TextView tvId = (TextView)item.findViewById(R.id.tvId);
    			TextView tvName = (TextView)item.findViewById(R.id.tvName);
    			TextView tvSex = (TextView)item.findViewById(R.id.tvSex);
    			TextView tvAge = (TextView)item.findViewById(R.id.tvAge);
    			
    			//绑定数据
    			tvId.setText(stu.getId()+"");
    			tvName.setText(stu.getName());
    			tvSex.setText(stu.getSex());
    			tvAge.setText(stu.getAge()+"");
    			
    			//将item添加到listView
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
        inflater = LayoutInflater.from(this);//从xml文件扩充一个View对象, 加载布局
        setupView();
    }
}