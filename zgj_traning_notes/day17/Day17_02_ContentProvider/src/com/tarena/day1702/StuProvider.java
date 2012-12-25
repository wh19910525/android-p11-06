package com.tarena.day1702;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StuProvider extends ContentProvider {
	private static final int MULTI_MUSIC = 1;
	private static final int SINGLE_MUSIC = 2;

	private DBOpenHelper helper;
	private static UriMatcher matcher;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI("com.tarena.providers.stu", "student", MULTI_MUSIC);
		matcher.addURI("com.tarena.providers.stu", "student/#", SINGLE_MUSIC);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String where = null;
		switch (matcher.match(uri)) {
		case MULTI_MUSIC:
			where = selection;
			break;
		case SINGLE_MUSIC:
			where = "_id=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("δ֪uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		int count = db.delete("stutbl", where, selectionArgs);
		db.close();

		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (matcher.match(uri) != MULTI_MUSIC) {
			throw new IllegalArgumentException("δ֪uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		long rowId = db.insert("stutbl", null, values);
		if (rowId != -1) {
			db.close();
			return ContentUris.withAppendedId(uri, rowId);
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper = new DBOpenHelper(getContext());
		if (helper != null)
			return true;
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String where = null;
		switch (matcher.match(uri)) {
		case MULTI_MUSIC:
			where = selection;
			break;
		case SINGLE_MUSIC:
			where = "_id=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("δ֪uri:" + uri);
		}

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("stutbl", projection, where, selectionArgs, null,
				null, sortOrder);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		String where = null;
		switch (matcher.match(uri)) {
		case MULTI_MUSIC:
			where = selection;
			break;
		case SINGLE_MUSIC:
			where = "_id=" + uri.getLastPathSegment();
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("δ֪uri:" + uri);
		}

		SQLiteDatabase db = helper.getWritableDatabase();
		int count = db.update("stutbl", values, where, selectionArgs);
		db.close();

		// TODO Auto-generated method stub
		return count;
	}

}
