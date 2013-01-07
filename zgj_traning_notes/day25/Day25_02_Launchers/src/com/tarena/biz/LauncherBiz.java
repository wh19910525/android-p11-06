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
		
		// ���� intent
		Intent target = new Intent();
		target.setAction(Intent.ACTION_MAIN);
		target.addCategory(Intent.CATEGORY_LAUNCHER);
		
		// ����intent��������֮ƥ���activity���
		List<ResolveInfo> resInfos = pm.queryIntentActivities(target, PackageManager.GET_INTENT_FILTERS);

		if (resInfos != null && !resInfos.isEmpty()) {
			launchers = new ArrayList<Launcher>();

			for (ResolveInfo resInfo : resInfos) {
				//����Launcher����
				Launcher launcher = new Launcher();
				//����ͼ��
				launcher.setIcon(resInfo.loadIcon(pm));
				//���ñ���
				launcher.setTitle(resInfo.loadLabel(pm).toString());

				//��ȡactivity���ڵĳ������
				String pkgName = resInfo.activityInfo.packageName;
				//��ȡactivity������
				String clsName = resInfo.activityInfo.name;
				//���ݰ����������������������
				ComponentName component = new ComponentName(pkgName, clsName);
				//������ʽ��ͼ
				Intent intent = new Intent();
				intent.setComponent(component);
				//������ͼ����
				launcher.setIntent(intent);
				
				//��ӵ�����
				launchers.add(launcher);
			}
		}
		return launchers;
	}
}
