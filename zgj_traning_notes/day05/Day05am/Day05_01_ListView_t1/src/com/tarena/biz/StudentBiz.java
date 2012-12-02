package com.tarena.biz;

import java.util.ArrayList;
import java.util.Random;

import com.tarena.entity.Student;

public class StudentBiz {
	private Random rand;//

	public StudentBiz() {
		rand = new Random(System.currentTimeMillis());//生成一个随机数
	}

	public ArrayList<Student> getStudents() {//
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 1; i <= 20; i++) {
			Student stu = new Student();
			stu.setId(i);
			stu.setName("张" + i);
			if (i % 2 == 0)
				stu.setSex("男");
			else
				stu.setSex("女");
			stu.setAge(18 + rand.nextInt(13));//

			students.add(stu);
		}
		return students;
	}
}
