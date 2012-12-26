package com.tarena.day1703;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.tarena.entity.ProviderInfo;

public class StudentProvider extends ContentProvider {
	
	private static final int MULTI_STU = 1;
	private static final int SINGLE_STU = 2;
	private static final int MULTI_USER = 3;
	private static final int SINGLE_USER = 4;
	
	private static UriMatcher matcher;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(ProviderInfo.AUTHORITY, ProviderInfo.MULTI_STU_PATH, MULTI_STU);
		matcher.addURI(ProviderInfo.AUTHORITY, ProviderInfo.SINGLE_STU_PATH, SINGLE_STU);
		matcher.addURI(ProviderInfo.AUTHORITY, ProviderInfo.MULTI_USER_PATH, MULTI_USER);
		matcher.addURI(ProviderInfo.AUTHORITY, ProviderInfo.SINGLE_USER_PATH, SINGLE_USER);
	}

	private DBOpenHelper helper;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String tblName = null;
		String where = null;
		switch (matcher.match(uri)) {
		case MULTI_STU:
			tblName = DBOpenHelper.TBL_STU;
			where = selection;
			break;
		case SINGLE_STU:
			tblName = DBOpenHelper.TBL_STU;
			where = ProviderInfo.StudentInfo._ID + "="
					+ uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		case MULTI_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = selection;
			break;
		case SINGLE_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = ProviderInfo.UserInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("未知uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		int count = db.delete(tblName, where, selectionArgs);
		db.close();
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String tblName = null;
		switch (matcher.match(uri)) {
		case MULTI_STU:
			tblName = DBOpenHelper.TBL_STU;
			break;
		case MULTI_USER:
			tblName = DBOpenHelper.TBL_USER;
			break;
		default:
			throw new IllegalArgumentException("非法uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		long rowId = db.insert(tblName, null, values);
		if (rowId > 0) {
			return ContentUris.withAppendedId(uri, rowId);
		}
		db.close();
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		String tblName = null;
		String where = null;
		switch (matcher.match(uri)) {
		case MULTI_STU:
			tblName = DBOpenHelper.TBL_STU;
			where = selection;
			break;
		case SINGLE_STU:
			tblName = DBOpenHelper.TBL_STU;
			where = ProviderInfo.StudentInfo._ID + "="
					+ uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		case MULTI_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = selection;
			break;
		case SINGLE_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = ProviderInfo.UserInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("未知uri:" + uri);
		}

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query(tblName, projection, where, selectionArgs, null, null, sortOrder);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		String tblName = null;
		String where = null;
		switch (matcher.match(uri)) {
		case MULTI_STU:
			tblName = DBOpenHelper.TBL_STU;
			where = selection;
			break;
		case SINGLE_STU:
			tblName = DBOpenHelper.TBL_STU;
			where = ProviderInfo.StudentInfo._ID + "="
					+ uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		case MULTI_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = selection;
			break;
		case SINGLE_USER:
			tblName = DBOpenHelper.TBL_USER;
			where = ProviderInfo.UserInfo._ID + "=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("未知uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		int count = db.update(tblName, values, where, selectionArgs);
		db.close();
		return count;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper = new DBOpenHelper(getContext());
		Log.i("context-getContext", getContext().toString());
		Log.i("context-this", this.toString());
		if (helper != null)
			return true;
		return false;
	}

}
