package com.github.hailouwang.demosforapi.view.demo3;

import android.view.MotionEvent;

public class Constant {
    public static final String LOGCAT = "hlwang";

    public static String getActionTAG(int action) {
        switch (action) {
            case 0:
                return "ACTION_DOWN";
            case 1:
                return "ACTION_UP";
            case 2:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            default:
                return "NULL";
        }
    }
}
