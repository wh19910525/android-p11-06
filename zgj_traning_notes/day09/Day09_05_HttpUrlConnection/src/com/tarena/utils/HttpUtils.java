package com.tarena.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

public class HttpUtils {
	public static InputStream doGet(String uri, HashMap<String, String> params)
			throws IOException {
		InputStream is = null;
		if (uri != null) {
			StringBuilder sb = new StringBuilder(uri);
			if (params != null && !params.isEmpty()) {
				sb.append('?');
				buildEntity(params, sb);
			}

			URL url = new URL(sb.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			if (conn.getResponseCode() == 200)
				is = conn.getInputStream();
		}
		return is;
	}

	private static void buildEntity(HashMap<String, String> params,
			StringBuilder sb) {
		for (Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue())
					.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);
	}

	public static InputStream doPost(String uri, HashMap<String, String> params)
			throws IOException {
		InputStream is = null;
		if (uri != null) {
			HttpURLConnection conn = (HttpURLConnection) new URL(uri)
					.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			if (params != null && !params.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				buildEntity(params, sb);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Length", sb.length() + "");
				OutputStream out = conn.getOutputStream();
				out.write(sb.toString().getBytes());
				out.close();
			}
			if (conn.getResponseCode() == 200)
				is = conn.getInputStream();
		}
		return is;
	}
}
