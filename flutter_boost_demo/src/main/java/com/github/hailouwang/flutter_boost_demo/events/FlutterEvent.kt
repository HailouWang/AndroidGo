package com.github.hailouwang.flutter_boost_demo.events

import android.app.Activity
import com.github.hailouwang.flutter_boost_demo.ui.container.AndroidGoFlutterContainerActivity
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostPlugin

abstract class FlutterEvent {
    protected abstract open fun eventName(): String
    protected abstract open fun onFlutterBoostPluginEventListener(): FlutterBoostPlugin.EventListener

    fun register() {
        FlutterBoostPlugin.addActionAfterRegistered {
            FlutterBoost.instance().channel()
                .addEventListener(eventName(), onFlutterBoostPluginEventListener())
        }
    }

    fun release() {

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