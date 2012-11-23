package com.tarena.day0201;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalcActivity extends Activity implements OnClickListener {
	private static final int BUTTON_CALC = 1;
	private static final int BUTTON_RESET = 2;

	private EditText etWeight, etHeight;
	private TextView tvWeight, tvHeight;
	private Button btnCalc, btnReset;
	private LinearLayout root, row1, row2, row3;

	/**
	 * 创建和初始化界面
	 */
	private void setupView() {
		// 创建布局参数对象
	//	LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f);//宽、高、权重
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f);

		// 第一行
		row1 = new LinearLayout(this);
		tvHeight = new TextView(this);
		tvHeight.setText("身高");

		etHeight = new EditText(this);
		etHeight.setHint("请输入身高，单位cm");
		etHeight.setInputType(InputType.TYPE_CLASS_NUMBER);//设置 只能 输入  数字

		row1.addView(tvHeight);
		row1.addView(etHeight, params);

		// 第二行
		row2 = new LinearLayout(this);
		tvWeight = new TextView(this);
		tvWeight.setText("体重");

		etWeight = new EditText(this);
		etWeight.setHint("请输入体重，单位kg");
		etWeight.setInputType(InputType.TYPE_CLASS_NUMBER);

		row2.addView(tvWeight);
		row2.addView(etWeight, params);

		// 第三行
		row3 = new LinearLayout(this);

		btnCalc = new Button(this);
		btnCalc.setText("计算");
		btnCalc.setId(BUTTON_CALC);//

		btnReset = new Button(this);
		btnReset.setText("重置");
		btnReset.setId(BUTTON_RESET);

		row3.addView(btnCalc, params);
		row3.addView(btnReset, params);

		// 根容器
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.addView(row1);
		root.addView(row2);
		root.addView(row3);
		
		setContentView(root);
	}

	/**
	 * 注册事件监听器
	 */
	private void addListeners() {
		btnCalc.setOnClickListener(this);
		btnReset.setOnClickListener(this);
	}

	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}

		return false;
	}

	/**
	 * 控件单击事件的处理方法
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case BUTTON_CALC:
			// 验证有效性
			if (isEmpty(etHeight)) {
				etHeight.setError("身高不能为空");
				return;
			}
			if (isEmpty(etWeight)) {
				etWeight.setError("体重不能为空");
				return;
			}
			// 获取输入
			double h = Double.parseDouble(etHeight.getText().toString());//
			double w = Double.parseDouble(etWeight.getText().toString());
			// 计算
			double bmi = w * 10000 / h / h;
			// 输出结果
			if (bmi < 18) {
				Toast.makeText(this, "您的身材偏瘦，请加强营养", 3000).show();
			} else if (bmi < 25) {
				Toast.makeText(this, "您的身材标准，请继续保持", 3000).show();
			} else if (bmi < 30) {
				Toast.makeText(this, "您的身材偏胖，请加强锻炼", 3000).show();
			} else if (bmi < 35) {
				Toast.makeText(this, "您的身材轻度肥胖，请加强锻炼", 3000).show();
			} else if (bmi < 40) {
				Toast.makeText(this, "您的身材中度肥胖，请加强锻炼", 3000).show();
			} else if (bmi < 45) {
				Toast.makeText(this, "您的身材严重肥胖，请加强锻炼", 3000).show();
			} else {
				Toast.makeText(this, "您的身材过分了，请加强锻炼", 3000).show();
			}
		case BUTTON_RESET:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupView();//
		addListeners();//
	}
}