package com.github.hailouwang.demosforapi;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dueeeke.dkplayer.app.MyApplication;
import com.github.hailouwang.flutter_boost_demo.ApplicationFlutterModel;
import com.tencent.mmkv.MMKV;

import java.io.IOException;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

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

        initRxErrorHandler();

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

        // Flutter Boost
        new ApplicationFlutterModel().init(this);
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

    private void initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }
            if (e instanceof IOException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return;
            }
            if (e instanceof InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return;
            }
            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                // that's likely a bug in the application
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                return;
            }
            if (e instanceof IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                return;
            }
        });
    }
}
