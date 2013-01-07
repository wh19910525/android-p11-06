package com.tarena.day2505;

import com.tarena.biz.ProcessBiz;
import com.tarena.entity.ProcessInfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ProcessDetailsActivity extends Activity {
	private TextView tvProcessId, tvProcessName;
	private ListView lvApps;
	private ProcessBiz biz;
	private AppAdapter adapter;
	private ProcessInfo process;

	private void setupView() {
		tvProcessId = (TextView) findViewById(R.id.tvProcessId_Details);
		tvProcessName = (TextView) findViewById(R.id.tvProcessName_Details);

		tvProcessId.setText(process.getProcessId() + "");
		tvProcessName.setText(process.getProcessName());

		lvApps = (ListView) findViewById(R.id.lvApps);
		adapter = new AppAdapter(this, process.getPkgList());
		lvApps.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		biz = new ProcessBiz(this);
		process = (ProcessInfo) getIntent().getSerializableExtra("process");
		setupView();
	}
}
