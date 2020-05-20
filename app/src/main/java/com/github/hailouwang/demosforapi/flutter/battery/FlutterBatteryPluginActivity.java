package com.github.hailouwang.demosforapi.flutter.battery;

import android.os.Bundle;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

/**
 * 链接：https://www.yuque.com/xytech/flutter/agwvd2
 */
public class FlutterBatteryPluginActivity extends FlutterActivity {

    /**
     * Channel名称：必须与Flutter App的Channel名称一致
     */
    private static final String METHOD_CHANNEL = "samples.flutter.io/battery";
    private static final String EVENT_CHANNEL = "samples.flutter.io/charging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GeneratedPluginRegistrant.registerWith(getFlutterEngine());

        registerWith(getFlutterEngine().getDartExecutor());
    }

    /**
     * Plugin 注册.
     */
    public static void registerWith(DartExecutor dartExecutor) {
        // 实例Plugin，并绑定到Channel上
        FlutterPluginBatteryLevel plugin = new FlutterPluginBatteryLevel();

        final MethodChannel methodChannel = new MethodChannel(dartExecutor, METHOD_CHANNEL);
        methodChannel.setMethodCallHandler(plugin);

        final EventChannel eventChannel = new EventChannel(dartExecutor, EVENT_CHANNEL);
        eventChannel.setStreamHandler(plugin);
    }
}
