package com.tarena.day2501;

import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

public class Day25_01_PackageManagerActivity extends Activity {
	
	private PackageManager pm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pm = getPackageManager();
		// List<PackageInfo> infos = pm.getInstalledPackages(0);
		List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		// List<ResolveInfo> infos = pm.queryIntentActivities(null, 0);
		// List<ResolveInfo> infos = pm.queryIntentServices(null, 0);

		// try {
		// // ActivityInfo info = pm.getActivityInfo(null, 0);
		// // ServiceInfo info = pm.getServiceInfo(null, 0);
		// // ProviderInfo info = pm.getProviderInfo(null, 0);
		// ApplicationInfo info = pm.getApplicationInfo(null, 0);
		//
		// } catch (NameNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// PackageItemInfo info = null;
		// info.icon
		// info.labelRes
		// info.name
		// info.packageName
		// info.loadIcon(pm)
		// info.loadLabel(pm)

		// ApplicationInfo info = null;
		// info.className
		// info.dataDir
		// info.flags
		// info.publicSourceDir
		// info.processName
		// info.taskAffinity
		// info.theme

		// ActivityInfo info = null;
		// info.applicationInfo
		// info.flags
		// info.launchMode
		// info.taskAffinity
		// info.screenOrientation
		
		// ServiceInfo info = null;
		// // info.processName
		// info.applicationInfo
		
		ResolveInfo info = null;
//		info.activityInfo
//		info.serviceInfo
//		info.filter
//		info.priority
//		info.loadIcon(pm)
//		info.loadLabel(pm) 
	}
}