package com.tarena.biz;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.xml.sax.SAXException;

import com.tarena.entity.Music;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.MusicXmlParser;

public class MusicBiz {
	public ArrayList<Music> getMusics(String uri,List<? extends NameValuePair> params,int method){
		ArrayList<Music> musics = null;
		try {
			HttpEntity entity = HttpUtils.getEntity(uri, params, method);
			InputStream in = HttpUtils.getStream(entity);
			musics = new MusicXmlParser().parse(in);
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return musics;
	}
}
