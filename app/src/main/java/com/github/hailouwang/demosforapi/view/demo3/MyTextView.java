package com.github.hailouwang.demosforapi.view.demo3;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {

	public MyTextView(Context context) {
		super(context);
		this.setGravity(Gravity.CENTER);
		this.setText("MyTextView");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d(Constant.LOGCAT, "MyTextView onTouchEvent 触发事件："+Constant.getActionTAG(event.getAction()));
		return false;
	}

}
