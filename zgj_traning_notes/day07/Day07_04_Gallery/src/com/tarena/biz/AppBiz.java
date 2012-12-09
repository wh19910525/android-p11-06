package com.tarena.biz;

import java.util.ArrayList;

import com.tarena.day0703.R;
import com.tarena.entity.AppInfo;

public class AppBiz {
	public ArrayList<AppInfo> getAppInfos() {
		ArrayList<AppInfo> apps = new ArrayList<AppInfo>();
		for (int i = 1; i <= 30; i++) {
			AppInfo app = new AppInfo(R.drawable.ic_launcher, "app" + i);//注意 每一个 add的对象 必须 要 重新 开辟 实例，否则 添加的信息 相同;
			apps.add(app);
		}
		return apps;
	}
}
