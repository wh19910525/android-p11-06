package com.tarena.biz;

import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.AppInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class AppBiz {
	
	public static final int TYPE_ALL = 1;
	public static final int TYPE_SYSTEM = 2;
	public static final int TYPE_THIRD = 3;
	public static final int TYPE_SDCARD = 4;
	
	private PackageManager pm;

	public AppBiz(Context context) {
		pm = context.getPackageManager();
	}

	public List<AppInfo> getAppInfos(int type) {
		
		List<AppInfo> apps = null;
		// 查询全部安装过的应用程序信息
		List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		// 如果查询结果不为空
		if (infos != null && !infos.isEmpty()) {
			// 实例化apps集合
			apps = new ArrayList<AppInfo>();

			// 遍历查询结果
			switch (type) {
			case TYPE_ALL:
				for (ApplicationInfo info : infos) {
					fill(apps, info);
				}
				break;
			case TYPE_SYSTEM:
				for (ApplicationInfo info : infos) {
					if ((info.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {//位与运算符
						fill(apps, info);
					}
				}
				break;
			case TYPE_THIRD:
				for (ApplicationInfo info : infos) {
					if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
						fill(apps, info);
					}
				}
				break;
			case TYPE_SDCARD:
				for (ApplicationInfo info : infos) {
					if ((info.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) > 0) {
						fill(apps, info);
					}
				}
				break;
			}

		}
		return apps;
	}

	private void fill(List<AppInfo> apps, ApplicationInfo info) {
		AppInfo app = new AppInfo();
		app.setTitle(info.loadLabel(pm).toString());
		app.setIcon(info.loadIcon(pm));
		app.setPkgName(info.packageName);

		apps.add(app);
	}
}
