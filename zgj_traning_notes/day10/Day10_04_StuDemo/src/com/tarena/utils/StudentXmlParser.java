package com.tarena.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.tarena.entity.Student;

public class StudentXmlParser {
	public static ArrayList<Student> parse(Reader reader) throws IOException, XmlPullParserException {

		ArrayList<Student> students = null;
		Student stu = null;

		// 创建解析器
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser parser = factory.newPullParser();

		// 设置输入源
		parser.setInput(reader);

		// 获取当前事件
		int eventType = parser.getEventType();

		// 当 事件不是文档解析结束时 循环解析
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// 判断事件类型 执行相应操作
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				students = new ArrayList<Student>();
				break;
			case XmlPullParser.START_TAG:
				String tagName = parser.getName();//这里如何理解？
				if ("student".equals(tagName)) {
					stu = new Student();
					stu.setId(Integer.parseInt(parser.getAttributeValue(null, "id")));
				} else if ("name".equals(tagName)) {
					stu.setName(parser.nextText());
				} else if ("sex".equals(tagName)) {
					stu.setSex(parser.nextText());
				} else if ("age".equals(tagName)) {
					stu.setAge(Integer.parseInt(parser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("student".equals(parser.getName())) {
					students.add(stu);
				}
				break;
			}
			// 获取下一事件
			eventType = parser.next();
		}
		return students;
	}
}
