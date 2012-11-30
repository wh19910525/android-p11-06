package com.tarena.biz;

import java.util.ArrayList;
import java.util.Random;

import com.tarena.entity.Student;

public class StudentBiz {
	private Random rand;

	public StudentBiz() {
		rand = new Random(System.currentTimeMillis());
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 1; i <= 20; i++) {
			Student stu = new Student();
			stu.setId(i);
			stu.setName("ÕÅ" + i);
			if (i % 2 == 0)
				stu.setSex("ÄĞ");
			else
				stu.setSex("Å®");
			stu.setAge(18 + rand.nextInt(13));

			students.add(stu);
		}

		return students;
	}
}
