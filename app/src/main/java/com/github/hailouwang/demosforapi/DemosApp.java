package com.github.hailouwang.demosforapi;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dueeeke.dkplayer.app.MyApplication;
import com.tencent.mmkv.MMKV;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class DemosApp extends Application {

    private static FlutterEngine flutterEngine;
    private static final String CACHE_ENGINE = "AndroidGoEngineId";

    private static DemosApp demosApp;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApp();

        demosApp = this;

        // ARouter start
        if (BuildConfig.DEBUG) {   // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application
        // ARouter end

        AVOSCloud.initialize(this, "cMTWImC5AHMXIlzvbKHoA1Xg-MdYXbMMI", "1RNk2jAVtPrdIFDOkA4waWXa");
        // 在 AVOSCloud.initialize 之前调用
        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);// or AVOSCloud.setLogLevel(AVLogger.Level.VERBOSE);

        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(this);

        flutterEngine.getNavigationChannel().setInitialRoute("/d");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put(CACHE_ENGINE, flutterEngine);

        // MMKV
        String rootDir = MMKV.initialize(this);
        Log.d("hlwang", "DemosApp onCreate MMKV rootDir : " + rootDir);

        // ijkplayer start
        MyApplication.init(getApp());
        // ijkplayer end
    }

    public static String getCacheEngine() {
        return CACHE_ENGINE;
    }

    public static FlutterEngine getFlutterEngine() {
        return flutterEngine;
    }

    public static Application getApp() {
        return demosApp;
    }
}
