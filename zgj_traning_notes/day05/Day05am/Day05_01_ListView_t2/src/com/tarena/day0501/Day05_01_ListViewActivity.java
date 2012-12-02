package com.tarena.day0501;

import java.util.ArrayList;

import com.tarena.biz.StudentBiz;
import com.tarena.day0501.adapter.StudentAapter;
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
    private StudentAapter adapter;//
    
    private void setupView(){

    	//��ȡListView������
    	lvStudents = (ListView)findViewById(R.id.lvStudents);

    	//��ȡ���ݼ�
    	ArrayList<Student> students = biz.getStudents();
		
    	//ʵ����adapter
    	adapter = new StudentAapter(this, students);//ע�� ���������������� һ�� Ϊ adapter�ṩ����  �� ��һ���ṩ item��
		
    	//����listView��adapter
    	lvStudents.setAdapter(adapter);//
    }
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        biz = new StudentBiz();
        inflater = LayoutInflater.from(this);
        setupView();
    }
}