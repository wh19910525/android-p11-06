package wh.zjg;

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

public class Zgj_day02_t1Activity extends Activity implements OnClickListener{
	
	private static final int BUTTON_CAL = 1;
	private static final int BUTTON_RESET = 2;
	
	private EditText etWeight, etHeight;
	private TextView tvWeitht, tvHeight;
	private Button btCal, btReset;
	private LinearLayout root, row1, row2, row3;
	
	private void setupView()
	{
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		
		//第一行
		tvHeight = new TextView(this);
		tvHeight.setText("身高");
		
		etHeight = new EditText(this);
		etHeight.setHint("请输入 身高");
		etHeight.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		row1 = new LinearLayout(this);
		row1.addView(tvHeight);
		row1.addView(etHeight, params);
	
		//第二行
		tvWeitht = new TextView(this);
		tvWeitht.setText("体重");
		
		etWeight = new EditText(this);
		etWeight.setHint("请输入 体重");
		etWeight.setInputType(InputType.TYPE_CLASS_NUMBER);

		row2 = new LinearLayout(this);
		row2.addView(tvWeitht);
		row2.addView(etWeight, params);
		
		//第三行
		btCal = new Button(this);
		btCal.setText("计算");
		btCal.setId(BUTTON_CAL);
		
		btReset = new Button(this);
		btReset.setText("重置");
		btReset.setId(BUTTON_RESET);
	
		row3 = new LinearLayout(this);
		row3.addView(btCal, params);
		row3.addView(btReset, params);
	
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.addView(row1);
		root.addView(row2);
		root.addView(row3);
		
		setContentView(root);
		
	}
	
	private void addListeners()
	{
		btCal.setOnClickListener(this);
		btReset.setOnClickListener(this);
	}
	
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case BUTTON_CAL:
			if(isEmpty(etHeight))
			{
				etHeight.setError("身高 不能 为空");
				return;
			}
			
			if (isEmpty(etWeight)) {
				etWeight.setError("体重不能为空");
				return;
			}
			
			double h = Double.parseDouble(etHeight.getText().toString());
			double w = Double.parseDouble(etWeight.getText().toString());
			
			// 计算
			double bmi = w * 10000 / h / h;
			
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
        //setContentView(R.layout.main);
        setupView();
        addListeners();
    }
}