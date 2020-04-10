package com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleService;

/**
 * 日志查看命令：
 * <p>
 * adb logcat -v time -s hlwang
 * <p>
 * 打印：
 * 04-09 16:28:35.519 D/hlwang  (18851): 生命周期 onCreate....
 * 04-09 16:28:35.520 D/hlwang  (18851): 生命周期 onStart....
 * 04-09 16:28:35.521 D/hlwang  (18851): 生命周期 onResume....
 * <p>
 * 04-09 16:28:42.543 D/hlwang  (18851): 生命周期 onPause....
 * 04-09 16:28:43.000 D/hlwang  (18851): 生命周期 onStop....
 * 04-09 16:28:43.004 D/hlwang  (18851): 生命周期 onDestory...
 */
public class LifeCyclerDemoService extends LifecycleService {

    @Override
    public void onCreate() {
        super.onCreate();
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_CREATE) {
                    Log.d("hlwang", "生命周期 onCreate....");
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    Log.d("hlwang", "生命周期 onResume....");
                } else if (event == Lifecycle.Event.ON_START) {
                    Log.d("hlwang", "生命周期 onStart....");
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.d("hlwang", "生命周期 onPause....");
                } else if (event == Lifecycle.Event.ON_STOP) {
                    Log.d("hlwang", "生命周期 onStop....");
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    Log.d("hlwang", "生命周期 onDestory....");
                }
            }
        });
    }
}
