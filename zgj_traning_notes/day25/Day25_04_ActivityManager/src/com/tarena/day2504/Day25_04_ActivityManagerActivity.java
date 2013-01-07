package com.tarena.day2504;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Bundle;

public class Day25_04_ActivityManagerActivity extends Activity {
	private ActivityManager am;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		// List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		// List<RunningServiceInfo> infos = am.getRunningServices(20);
		// List<RunningTaskInfo> infos = am.getRunningTasks(20);
		// List<RecentTaskInfo> infos = am.getRecentTasks(20, 0);
		// MemoryInfo info = new MemoryInfo();
		// am.getMemoryInfo(info);
		// Log.i("info", "availMem:"+info.availMem);
		// Log.i("info", "内存不足：" + info.lowMemory);
		// Log.i("info", "内存不足临界值:" + info.threshold);

		// android.os.Debug.MemoryInfo[] infos = am.getProcessMemoryInfo(new
		// int[]{1});
		// infos[0].dalvikPrivateDirty

		// am.killBackgroundProcesses("pkgName");
		// am.restartPackage("pkgName");

		// RunningAppProcessInfo info = null;
		// info.pid
		// info.processName
		// info.pkgList
		// info.uid

		
		RunningServiceInfo info = null;
//		info.pid
//		info.process
//		info.activeSince
//		info.clientCount
//		info.flags
//		info.foreground
//		info.lastActivityTime
//		info.restarting
//		info.service
//		info.started
//		info.uid
		
	}

}