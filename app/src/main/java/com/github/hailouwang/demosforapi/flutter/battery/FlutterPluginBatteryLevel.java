package com.github.hailouwang.demosforapi.flutter.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

import java.util.Random;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class FlutterPluginBatteryLevel implements MethodChannel.MethodCallHandler, EventChannel.StreamHandler {

    /**
     * MethodCallHandler
     */
    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Log.d("hlwang", "FlutterPluginBatteryLevel onMethodCall call : " + call
                + ", result : " + result);
        if (call.method.equals("getBatteryLevel")) {
            Random random = new Random();
            result.success(random.nextInt(100));
        } else {
            result.notImplemented();
        }
    }

    /**
     * EventChannel.StreamHandler
     */
    @Override
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        Log.d("hlwang", "FlutterPluginBatteryLevel onListen obj : " + obj
                + ", eventSink : " + eventSink);
        BroadcastReceiver chargingStateChangeReceiver = createChargingStateChangeReceiver(eventSink);
    }

    @Override
    public void onCancel(Object obj) {
        Log.d("hlwang", "FlutterPluginBatteryLevel onCancel obj : " + obj);
    }

    private BroadcastReceiver createChargingStateChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                Log.d("hlwang", "FlutterPluginBatteryLevel onReceive status : " + status);

                if (status == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                    events.error("UNAVAILABLE", "Charging status unavailable", null);
                } else {
                    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;
                    events.success(isCharging ? "charging" : "discharging");
                }
            }
        };
    }
}