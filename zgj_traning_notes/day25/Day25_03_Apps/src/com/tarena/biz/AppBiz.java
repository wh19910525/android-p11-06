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
		// ��ѯȫ����װ����Ӧ�ó�����Ϣ
		List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		// �����ѯ�����Ϊ��
		if (infos != null && !infos.isEmpty()) {
			// ʵ����apps����
			apps = new ArrayList<AppInfo>();

			// ������ѯ���
			switch (type) {
			case TYPE_ALL:
				for (ApplicationInfo info : infos) {
					fill(apps, info);
				}
				break;
			case TYPE_SYSTEM:
				for (ApplicationInfo info : infos) {
					if ((info.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {//λ�������
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
