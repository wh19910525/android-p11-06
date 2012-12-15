package com.tarena.biz;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.entity.Student;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.StudentXmlParser;

public class StudentBiz {
	public ArrayList<Student> getStudent(String uri,List<? extends NameValuePair> params,int method){
		try {
			HttpEntity entity = HttpUtils.getEntity(uri, params, method);
			InputStream in = HttpUtils.getStream(entity);
			ArrayList<Student> students = StudentXmlParser.parse(new InputStreamReader(in));//½âÎöxml
			return students;
			
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
