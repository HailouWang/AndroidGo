package com.github.hailouwang.demosforapi.view.demo3;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class Group2 extends FrameLayout {

	public Group2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.d(Constant.LOGCAT, "Group2 dispatchTouchEvent 触发事件："+Constant.getActionTAG(ev.getAction()));
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.d(Constant.LOGCAT, "Group2 onInterceptTouchEvent 触发事件："+Constant.getActionTAG(ev.getAction()));
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d(Constant.LOGCAT, "Group2 onTouchEvent 触发事件："+Constant.getActionTAG(event.getAction()));
		return false;
	}

}
