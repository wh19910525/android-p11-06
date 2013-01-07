package com.tarena.biz;

import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.Launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class LauncherBiz {
	
	private PackageManager pm;

	public LauncherBiz(Context context) {
		pm = context.getPackageManager();
	}

	public List<Launcher> getLaunchers() {
		
		List<Launcher> launchers = null;
		
		// 创建 intent
		Intent target = new Intent();
		target.setAction(Intent.ACTION_MAIN);
		target.addCategory(Intent.CATEGORY_LAUNCHER);
		
		// 根据intent查找能与之匹配的activity组件
		List<ResolveInfo> resInfos = pm.queryIntentActivities(target, PackageManager.GET_INTENT_FILTERS);

		if (resInfos != null && !resInfos.isEmpty()) {
			launchers = new ArrayList<Launcher>();

			for (ResolveInfo resInfo : resInfos) {
				//创建Launcher对象
				Launcher launcher = new Launcher();
				//设置图标
				launcher.setIcon(resInfo.loadIcon(pm));
				//设置标题
				launcher.setTitle(resInfo.loadLabel(pm).toString());

				//获取activity所在的程序包名
				String pkgName = resInfo.activityInfo.packageName;
				//获取activity的类名
				String clsName = resInfo.activityInfo.name;
				//根据包名和类名创建组件名对象
				ComponentName component = new ComponentName(pkgName, clsName);
				//创建显式意图
				Intent intent = new Intent();
				intent.setComponent(component);
				//设置意图对象
				launcher.setIntent(intent);
				
				//添加到集合
				launchers.add(launcher);
			}
		}
		return launchers;
	}
}
