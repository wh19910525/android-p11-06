package com.tarena.day0709;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout.LayoutParams;

public class Day07_09_TouchActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����һ��MyTextView�ؼ�
        MyTextView tv = new MyTextView(this);
        LayoutParams params = new LayoutParams(300,300);//��ʲô��
        tv.setLayoutParams(params);
        tv.setBackgroundColor(Color.BLUE);
        //���ؼ����ӵ�activity��������
        setContentView(tv);
    }
}