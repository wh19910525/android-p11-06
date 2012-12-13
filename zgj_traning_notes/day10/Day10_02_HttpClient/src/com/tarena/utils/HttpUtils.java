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
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;

	public static HttpEntity getEntity(String uri,
			List<? extends NameValuePair> params, int method)
			throws ConnectTimeoutException, IOException {
		HttpEntity entity = null;
		// 创建客户端对象
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		// 创建请求对象
		HttpUriRequest request = null;
		switch (method) {
		case METHOD_GET:
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
		// 执行请求获得响应对象
		HttpResponse response = client.execute(request);
		// 判断请求码
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			entity = response.getEntity();
		}
		return entity;
	}

	public static long getLength(HttpEntity entity) {
		if (entity != null)
			return entity.getContentLength();
		return 0;
	}

	public static InputStream getStream(HttpEntity entity) throws IOException {
		if (entity != null)
			return entity.getContent();
		return null;
	}
	
	
}
