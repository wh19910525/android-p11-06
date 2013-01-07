package com.tarena.biz;

import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.AppInfo;
import com.tarena.entity.ProcessInfo;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.graphics.drawable.Drawable;

public class ProcessBiz {
	private ActivityManager am;
	private PackageManager pm;

	public ProcessBiz(Context context) {
		pm = context.getPackageManager();
		am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
	}

	public List<ProcessInfo> getRunningRrocesses() {
		List<ProcessInfo> processes = null;
		List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		if (infos != null && !infos.isEmpty()) {
			processes = new ArrayList<ProcessInfo>();
			for (RunningAppProcessInfo info : infos) {
				ProcessInfo process = new ProcessInfo();
				process.setProcessId(info.pid);
				process.setProcessName(info.processName);
				process.setPkgList(info.pkgList);

				processes.add(process);
			}
		}
		return processes;
	}

	public Drawable getIcon(String pkgName) {
		try {
			return pm.getApplicationIcon(pkgName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public AppInfo getAppInfo(String pkgName) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(pkgName, 0);
			AppInfo app = new AppInfo();
			app.setLable(info.loadLabel(pm).toString());
			app.setIcon(info.loadIcon(pm));
			app.setPkgName(pkgName);

			return app;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void killProcess(String pkgName) {
		am.killBackgroundProcesses(pkgName);
	}
}
