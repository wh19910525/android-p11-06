package com.tarena.entity;

import android.net.Uri;

public class ProviderInfo {
	
	public static final String AUTHORITY = "com.tarena.providers.student";
	public static final String MULTI_STU_PATH = "student";
	public static final String SINGLE_STU_PATH = "student/#";
	public static final String MULTI_USER_PATH = "user";
	public static final String SINGLE_USER_PATH = "user/#";

	public static class StudentInfo {
		public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY + "/" + MULTI_STU_PATH);

		public static final String _ID = "_id";
		public static final String NAME = "name";
		public static final String SEX = "sex";
		public static final String AGE = "age";
	}

	public static class UserInfo {
		public static final Uri CONTEN_URI = Uri.parse("content://" + AUTHORITY + "/" + MULTI_USER_PATH);

		public static final String _ID = "_id";
		public static final String NAME = "username";
		public static final String PASSWORD = "userpass";
	}

}
