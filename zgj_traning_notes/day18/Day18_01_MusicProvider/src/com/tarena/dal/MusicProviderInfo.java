package com.tarena.dal;

import android.net.Uri;

public class MusicProviderInfo {
	
	public static final String AUTHORITY = "com.tarena.providers.music";
	public static final String MULTI_USER_PATH = "user";
	public static final String SINGLE_USER_PATH = "user/#";
	public static final String MULTI_MUSIC_PATH = "music";
	public static final String SINGLE_MUSIC_PATH = "music/#";

	public static class MusicInfo {
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + MULTI_MUSIC_PATH);

		public static final String _ID = "_id";
		public static final String NAME = "name";
		public static final String ALBUM = "album";
		public static final String AUTHOR = "author";
		public static final String COMPOSER = "composer";
		public static final String SINGER = "singer";
		public static final String DURATION = "duration";
		public static final String ALBUM_PATH = "album_path";
		public static final String MUSIC_PATH = "music_path";
	}

	public static class UserInfo {
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + MULTI_USER_PATH);

		public static final String _ID = "_id";
		public static final String NAME = "username";
		public static final String PASSWORD = "userpass";
	}
}
