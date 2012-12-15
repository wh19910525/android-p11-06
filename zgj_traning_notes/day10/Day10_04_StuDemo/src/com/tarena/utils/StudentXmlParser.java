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

		// ����������
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser parser = factory.newPullParser();

		// ��������Դ
		parser.setInput(reader);

		// ��ȡ��ǰ�¼�
		int eventType = parser.getEventType();

		// �� �¼������ĵ���������ʱ ѭ������
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// �ж��¼����� ִ����Ӧ����
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				students = new ArrayList<Student>();
				break;
			case XmlPullParser.START_TAG:
				String tagName = parser.getName();//���������⣿
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
			// ��ȡ��һ�¼�
			eventType = parser.next();
		}
		return students;
	}
}
