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
			// 创建解析器对象
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);//支持命名空间,如android:....
			XmlPullParser parser = factory.newPullParser();

			// File file = new File(path);
			// if (file.exists()) {
			// 设置解析器的输入流
			parser.setInput(reader);
			// 获取当前事件
			int eventType = parser.getEventType();
			Student stu = null;
			// 当 当前事件不是文档解析结束时，循环解析
			while (eventType != XmlPullParser.END_DOCUMENT) {

				// 判断当前事件类型，做出相应处理
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 开始解析xml文档
					students = new ArrayList<Student>();
					break;
				case XmlPullParser.START_TAG:// 开始解析某标签
					// 获取当前正在解析的标签的标签名
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
				case XmlPullParser.END_TAG:// 某标签解析结束
					if ("student".equals(parser.getName())) {
						students.add(stu);
					}
					break;
				}
				// 获取下一事件
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
