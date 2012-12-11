package com.tarena.biz;

import java.util.ArrayList;
import java.util.HashMap;

public class Biz {
	
	public ArrayList<HashMap<String, Object>> getStudents() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<=20;i++){
			HashMap<String, Object> stu = new HashMap<String, Object>();//����Ҫ ÿ�� ����
			stu.put("id", i);
			stu.put("name", "��"+i);
			stu.put("sex", "��");
			stu.put("age",19);
			
			list.add(stu);
		}
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> getCourses() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<=5;i++){
			HashMap<String, Object> course = new HashMap<String, Object>();
			course.put("id", i);
			course.put("name", "��Ŀ"+i);
			course.put("teacher", "��"+i);
			
			list.add(course);
		}
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> getScores() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<=20;i++){
			HashMap<String, Object> score = new HashMap<String, Object>();
			score.put("id", i);
			score.put("stuName", "��"+i);
			score.put("courseName", "��Ŀ"+i);
			score.put("score",99);
			
			list.add(score);
		}
		return list;
	}
}
