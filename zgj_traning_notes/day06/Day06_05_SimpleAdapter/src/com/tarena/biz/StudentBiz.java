package com.tarena.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.tarena.day0605.R;

public class StudentBiz {
	private Random rand;

	public StudentBiz() {
		rand = new Random(System.currentTimeMillis());
	}

	public ArrayList<HashMap<String, Object>> getStudents() {
		ArrayList<HashMap<String, Object>> students = new ArrayList<HashMap<String, Object>>();
		for (int i = 1; i <= 20; i++) {
			HashMap<String, Object> stu = new HashMap<String, Object>();
			stu.put("icon", R.drawable.ic_launcher);
			stu.put("id", i);
			stu.put("name", "ÕÅ" + i);
			if (i % 2 == 0)
				stu.put("sex", "ÄĞ");
			else
				stu.put("sex", "Å®");
			stu.put("age", 18 + rand.nextInt(12));//

			students.add(stu);
		}

		return students;
	}
}
