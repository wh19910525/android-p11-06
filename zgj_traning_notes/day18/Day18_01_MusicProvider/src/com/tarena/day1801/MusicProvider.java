package com.tarena.day1801;

import com.tarena.dal.DBOpenHelper;
import com.tarena.dal.MusicProviderInfo;
import com.tarena.dal.MusicProviderInfo.MusicInfo;
import com.tarena.dal.MusicProviderInfo.UserInfo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MusicProvider extends ContentProvider {
	
	private static final int CODE_MULTI_MUSIC = 1;
	private static final int CODE_MULTI_USER = 2;
	private static final int CODE_SINGLE_MUSIC = 3;
	private static final int CODE_SINGLE_USER = 4;
	
	private static UriMatcher matcher;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(MusicProviderInfo.AUTHORITY,
				MusicProviderInfo.MULTI_MUSIC_PATH, CODE_MULTI_MUSIC);
		matcher.addURI(MusicProviderInfo.AUTHORITY,
				MusicProviderInfo.MULTI_USER_PATH, CODE_MULTI_USER);
		matcher.addURI(MusicProviderInfo.AUTHORITY,
				MusicProviderInfo.SINGLE_MUSIC_PATH, CODE_SINGLE_MUSIC);
		matcher.addURI(MusicProviderInfo.AUTHORITY,
				MusicProviderInfo.SINGLE_USER_PATH, CODE_SINGLE_USER);
	}

	private DBOpenHelper helper;
	/**
	 * 想要支持观察者需执行contentResolver对象的notifyChange(Uri,ContentObserver)
	 * ***********
	 * 另外ContentProvider虽然不是上下文对象,但是可以通过getContent()得到上下文对象
	 */

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
		String where = null;
		String tblName = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_MUSIC:
			where = selection;
			tblName = DBOpenHelper.TBL_MUSIC;
			break;
		case CODE_SINGLE_MUSIC:
			tblName = DBOpenHelper.TBL_MUSIC;
			where = MusicInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		case CODE_MULTI_USER:
			where = selection;
			tblName = DBOpenHelper.TBL_USER;
			break;
		case CODE_SINGLE_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = UserInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("非法uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		count = db.delete(tblName, where, selectionArgs);
		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		db.close();

		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case CODE_MULTI_MUSIC:
			return "vnd.android.cursor.dir/muisc";
		case CODE_SINGLE_MUSIC:
			return "vnd.android.cursor.item/music";
		case CODE_MULTI_USER:
			return "vnd.android.cursor.dir/user";
		case CODE_SINGLE_USER:
			return "vnd.android.cursor.item/user";
		default:
			throw new IllegalArgumentException("未知uri:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String tblName = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_MUSIC:
			tblName = DBOpenHelper.TBL_MUSIC;
			break;
		case CODE_MULTI_USER:
			tblName = DBOpenHelper.TBL_USER;
			break;
		default:
			throw new IllegalArgumentException("未知uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		long rowId = db.insert(tblName, null, values);
		if (rowId != -1) {
			db.close();
			//支持观察者模式
			getContext().getContentResolver().notifyChange(uri, null);
			return ContentUris.withAppendedId(uri, rowId);
		}
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor c = null;
		String where = null;
		String tblName = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_MUSIC:
			where = selection;
			tblName = DBOpenHelper.TBL_MUSIC;
			break;
		case CODE_SINGLE_MUSIC:
			tblName = DBOpenHelper.TBL_MUSIC;
			where = MusicInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		case CODE_MULTI_USER:
			where = selection;
			tblName = DBOpenHelper.TBL_USER;
			break;
		case CODE_SINGLE_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = UserInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("非法uri:" + uri);
		}

		SQLiteDatabase db = helper.getReadableDatabase();
		c = db.query(tblName, projection, where, selectionArgs, null, null,
				sortOrder);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		String where = null;
		String tblName = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_MUSIC:
			where = selection;
			tblName = DBOpenHelper.TBL_MUSIC;
			break;
		case CODE_SINGLE_MUSIC:
			tblName = DBOpenHelper.TBL_MUSIC;
			where = MusicInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		case CODE_MULTI_USER:
			where = selection;
			tblName = DBOpenHelper.TBL_USER;
			break;
		case CODE_SINGLE_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = UserInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("非法uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		count = db.update(tblName, values, where, selectionArgs);
		if (count > 0) {
			
			getContext().getContentResolver().notifyChange(uri, null);
			
		}
		db.close();

		return count;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper = new DBOpenHelper(getContext(), "music.db");
		Log.i("context-getContext", getContext().toString());
		Log.i("context-this", this.toString());
		if (helper != null)
			return true;
		return false;
	}
}
