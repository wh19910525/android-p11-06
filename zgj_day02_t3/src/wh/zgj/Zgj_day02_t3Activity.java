package wh.zgj;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Zgj_day02_t3Activity extends Activity {
	
	private EditText etHeight, etWeight;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupView();
    }
    
	private void setupView() {
		etHeight = (EditText) findViewById(R.id.etHeight);
		etWeight = (EditText) findViewById(R.id.etWeight);
	}
	
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}
    
	public void zgjClick(View v)//在 layout的xml里定义,若是 xml里定义了 监听器，而 代码里 没有 写 这个 监听器，那么当点击的时候 会 出错，推出 应用 程序;
	{
		switch (v.getId()) {
		
		case R.id.btReset:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}		
	}
	
	public void whClick(View v) {//第四种 设置 监听器的 方法，就是在  xml布局文件里 设置, 监听器的名字 自己起;
		switch (v.getId()) {
		case R.id.btCal:
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
			double h = Double.parseDouble(etHeight.getText().toString());
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
		case R.id.btReset:
			etWeight.setText("");
			etHeight.setText("");
			Toast.makeText(this, "重新 开始！", 3000).show();
			break;
		}
	}
}
    
