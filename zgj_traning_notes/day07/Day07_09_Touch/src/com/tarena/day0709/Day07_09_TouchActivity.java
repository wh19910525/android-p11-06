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
        //创建一个MyTextView控件
        MyTextView tv = new MyTextView(this);
        LayoutParams params = new LayoutParams(200,200);
        tv.setLayoutParams(params);
        tv.setBackgroundColor(Color.GRAY);
        //将控件添加到activity的内容区
        setContentView(tv);
    }
}