package com.tarena.day2502;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.format.Formatter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.tarena.biz.AppBiz;
import com.tarena.entity.AppInfo;

public class AppsActivity extends Activity {

	private static final int MENU_OPTS_ALL = 1;
	private static final int MENU_OPTS_SYSTEM = 2;
	private static final int MENU_OPTS_THIRD = 3;
	private static final int MENU_OPTS_SDCARD = 4;

	private ListView lvApps;
	private AppBiz biz;
	private AppAdapter adapter;
	private AlertDialog dialog;
	private long totalSize;
	private long codeSize;
	private long dataSize;
	private long cacheSize;
	private PackageManager pm;
	private PackageSizeObserver observer;

	private void setupView() {
		lvApps = (ListView) findViewById(R.id.lvApps);
		adapter = new AppAdapter(this, biz.getAppInfos(AppBiz.TYPE_ALL));
		lvApps.setAdapter(adapter);

		dialog = new Builder(this).setTitle("详情").setPositiveButton("确定", null).create();
	}

	private void addListener() {
		lvApps.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.add(1, 1, 1, "详情");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, MENU_OPTS_ALL, 1, "全部");
		menu.add(1, MENU_OPTS_SYSTEM, 2, "系统");
		menu.add(1, MENU_OPTS_THIRD, 3, "第三方");
		menu.add(1, MENU_OPTS_SDCARD, 4, "sdcard");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		List<AppInfo> apps = null;
		switch (item.getItemId()) {
		case MENU_OPTS_ALL:
			apps = biz.getAppInfos(AppBiz.TYPE_ALL);
			break;
		case MENU_OPTS_SYSTEM:
			apps = biz.getAppInfos(AppBiz.TYPE_SYSTEM);
			break;
		case MENU_OPTS_THIRD:
			apps = biz.getAppInfos(AppBiz.TYPE_THIRD);
			break;
		case MENU_OPTS_SDCARD:
			apps = biz.getAppInfos(AppBiz.TYPE_SDCARD);
			break;
		}
		adapter.changData(apps);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		AppInfo app = (AppInfo) adapter.getItem(menuInfo.position);
		switch (item.getItemId()) {

		case 1:
			try {
				Method method = pm.getClass().getDeclaredMethod("getPackageSizeInfo", String.class, //这里是 什么机制？
						IPackageStatsObserver.class);
				method.invoke(pm, app.getPkgName(), observer);//invoke()的参数分别表示,哪个对象调用此方法 和 调用方法的参数

				StringBuilder sb = new StringBuilder("程序详情:\n");
				sb.append("程序大小:").append(Formatter.formatFileSize(this, codeSize)).append('\n');
				sb.append("数据大小:").append(Formatter.formatFileSize(this, dataSize)).append('\n');
				sb.append("缓存大小:").append(Formatter.formatFileSize(this, cacheSize)).append('\n');
				sb.append("总  大  小:").append(Formatter.formatFileSize(this, totalSize)).append('\n');

				dialog.setMessage(sb.toString());
				dialog.show();

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			break;
		}
		return super.onContextItemSelected(item);
	}
	
	private class PackageSizeObserver extends IPackageStatsObserver.Stub {//

		@Override
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
	 		// TODO Auto-generated method stub
			codeSize = pStats.codeSize;
			dataSize = pStats.dataSize;
			cacheSize = pStats.cacheSize;
			totalSize = codeSize + dataSize + cacheSize;
		}

	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new AppBiz(this);
		pm = getPackageManager();
		observer = new PackageSizeObserver();
		setupView();
		addListener();
	}
}