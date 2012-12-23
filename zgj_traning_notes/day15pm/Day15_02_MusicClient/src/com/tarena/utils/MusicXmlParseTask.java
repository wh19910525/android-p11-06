package com.tarena.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;
import org.xml.sax.SAXException;

import android.widget.Toast;

import com.tarena.day1201.MusicClientActivity;
import com.tarena.entity.Music;
import com.tarena.utils.MyAsyncTask;

public class MusicXmlParseTask extends MyAsyncTask<String, String, ArrayList<Music>> {
	
	private MusicClientActivity context;//

	public MusicXmlParseTask(MusicClientActivity context) {
		this.context = context;
	}

	@Override
	public ArrayList<Music> doInBackground(String params) {
		ArrayList<Music> musics = null;
		try {
			HttpEntity entity = HttpUtils.getEntity(params, null, HttpUtils.METHOD_GET);
			publishProgress("���ʷ���ˣ���ȡ��Ӧʵ�����");

			InputStream in = HttpUtils.getStream(entity);
			publishProgress("���ʵ��������,��ʼ����xml");

			musics = new MusicXmlParser().parse(in);
			publishProgress("xml�������");
			
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

	@Override
	public void onPostExecute(ArrayList<Music> result) {
		// TODO Auto-generated method stub
		context.updateListView(result);
	}

	@Override
	public void onPreExecute() {
		// TODO Auto-generated method stub
		Toast.makeText(context, "��Ҫ���ӷ���˻�ȡ���ݣ������ĵȺ�...", 3000).show();
	}

	@Override
	public void onUpdateProgress(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(context, arg0, 3000).show();
	}

}
