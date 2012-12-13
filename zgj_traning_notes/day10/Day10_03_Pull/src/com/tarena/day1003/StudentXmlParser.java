package com.tarena.day1003;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.tarena.entity.Student;

public class StudentXmlParser {
	public static ArrayList<Student> parse(Reader reader) {
		ArrayList<Student> students = null;
		try {
			// ��������������
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);//֧�������ռ�,��android:....
			XmlPullParser parser = factory.newPullParser();

			// File file = new File(path);
			// if (file.exists()) {
			// ���ý�������������
			parser.setInput(reader);
			// ��ȡ��ǰ�¼�
			int eventType = parser.getEventType();
			Student stu = null;
			// �� ��ǰ�¼������ĵ���������ʱ��ѭ������
			while (eventType != XmlPullParser.END_DOCUMENT) {

				// �жϵ�ǰ�¼����ͣ�������Ӧ����
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// ��ʼ����xml�ĵ�
					students = new ArrayList<Student>();
					break;
				case XmlPullParser.START_TAG:// ��ʼ����ĳ��ǩ
					// ��ȡ��ǰ���ڽ����ı�ǩ�ı�ǩ��
					String tagName = parser.getName();
					if ("student".equals(tagName)) {
						stu = new Student();
						stu.setId(Integer.parseInt(parser.getAttributeValue(
								null, "id")));
					} else if ("name".equals(tagName)) {
						stu.setName(parser.nextText());
					} else if ("sex".equals(tagName)) {
						stu.setSex(parser.nextText());
					} else if ("age".equals(tagName)) {
						stu.setAge(Integer.parseInt(parser.nextText()));
					}
					break;
				case XmlPullParser.END_TAG:// ĳ��ǩ��������
					if ("student".equals(parser.getName())) {
						students.add(stu);
					}
					break;
				}
				// ��ȡ��һ�¼�
				eventType = parser.next();
			}
			// }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
}
