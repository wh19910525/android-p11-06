package com.tarena.biz;

import java.util.ArrayList;
import java.util.HashMap;

public class Biz {
	
	public ArrayList<HashMap<String, Object>> getStudents() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<=20;i++){
			HashMap<String, Object> stu = new HashMap<String, Object>();//必须要 每次 创建
			stu.put("id", i);
			stu.put("name", "张"+i);
			stu.put("sex", "男");
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
			course.put("name", "科目"+i);
			course.put("teacher", "李"+i);
			
			list.add(course);
		}
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> getScores() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i=1;i<=20;i++){
			HashMap<String, Object> score = new HashMap<String, Object>();
			score.put("id", i);
			score.put("stuName", "张"+i);
			score.put("courseName", "科目"+i);
			score.put("score",99);
			
			list.add(score);
		}
		return list;
	}
}
