package com.github.hailouwang.demosforapi;

import android.app.Application;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;

public class DemosApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AVOSCloud.initialize(this, "cMTWImC5AHMXIlzvbKHoA1Xg-MdYXbMMI", "1RNk2jAVtPrdIFDOkA4waWXa");
        // 在 AVOSCloud.initialize 之前调用
        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);// or AVOSCloud.setLogLevel(AVLogger.Level.VERBOSE);
    }
}
