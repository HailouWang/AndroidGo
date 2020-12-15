package com.github.hailouwang.flutter_boost_demo.channels

import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostPlugin
import io.flutter.plugin.common.MethodChannel

class FlutterChannelRegister {
    companion object {
        fun register() {
            FlutterBoostPlugin.addActionAfterRegistered {
                MethodChannel(
                    FlutterBoost.instance().engineProvider().dartExecutor,
                    "methodCallChannelName"
                ).setMethodCallHandler { call, result ->
                    // balabala
                }
            }
        }

        fun release() {

        }
    }
}