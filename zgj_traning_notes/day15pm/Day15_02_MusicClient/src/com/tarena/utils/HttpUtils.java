package com.tarena.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

public class HttpUtils {
	public static final String BASE_URL = "http://192.168.1.101:8080/musiconline/";
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;

	/**
	 * ��ָ��uri����ȡ��Ӧʵ��
	 * 
	 * @param uri
	 * @param params
	 * @param method
	 * @return
	 * @throws IOException
	 */
	public static HttpEntity getEntity(String uri,
			List<? extends NameValuePair> params, int method)
			throws ConnectTimeoutException, IOException {
		HttpEntity entity = null;
		// �����ͻ��˶���
		HttpClient client = new DefaultHttpClient();
		// ������������
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		// �����������
		HttpUriRequest request = null;
		switch (method) {
		case METHOD_GET:
			// ƴ��uri
			StringBuilder sb = new StringBuilder(uri);
			if (params != null && !params.isEmpty()) {
				sb.append('?');
				for (NameValuePair pair : params) {
					sb.append(pair.getName()).append('=')
							.append(pair.getValue()).append('&');
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			request = new HttpGet(sb.toString());
			break;
		case METHOD_POST:
			request = new HttpPost(uri);
			if (params != null && !params.isEmpty()) {
				UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
						params);
				((HttpPost) request).setEntity(reqEntity);
			}
			break;
		}
		// ִ����������Ӧ����
		HttpResponse response = client.execute(request);
		// �ж���Ӧ���ȡ ʵ�����
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			entity = response.getEntity();
		}
		return entity;
	}

	public static InputStream getStream(HttpEntity entity) throws IOException {
		InputStream in = null;
		if (entity != null) {
			in = entity.getContent();
		}

		return in;
	}

	public static long getLength(HttpEntity entity) {
		if (entity != null)
			return entity.getContentLength();
		return 0;
	}
}
