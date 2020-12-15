package com.github.hailouwang.flutter_boost_demo.channels

import android.app.Activity
import com.github.hailouwang.flutter_boost_demo.ui.container.AndroidGoFlutterContainerActivity
import com.idlefish.flutterboost.FlutterBoost
import io.flutter.plugin.common.MethodChannel

abstract class FlutterChannel {
    private var channel: MethodChannel? = null

    protected abstract open fun methodCallChannelName(): String
    protected abstract open fun onMethodCallHandler(): MethodChannel.MethodCallHandler

    fun register() {
        if (channel == null) {
            MethodChannel(
                        FlutterBoost.instance().engineProvider().dartExecutor,
                methodCallChannelName()
            ).setMethodCallHandler(onMethodCallHandler())
        }
    }

    fun release() {
        channel = null
    }

    companion object {
        fun isFinishing(): Boolean {
            return getActivity()?.isFinishing ?: true
        }

        fun getActivity(): Activity? {
            return AndroidGoFlutterContainerActivity.activityReference?.get()
        }
    }
}