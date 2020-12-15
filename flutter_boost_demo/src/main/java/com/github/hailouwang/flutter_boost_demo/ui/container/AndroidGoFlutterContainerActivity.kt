package com.github.hailouwang.flutter_boost_demo.ui.container

import android.app.Activity
import android.os.Bundle
import com.github.hailouwang.flutter_boost_demo.utils.WindowUtils
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostPlugin
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import java.lang.ref.WeakReference

class AndroidGoFlutterContainerActivity : BoostFlutterActivity() {
    lateinit var flutterBoost: FlutterBoost
    lateinit var flutterBoostPlugin: FlutterBoostPlugin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityReference = WeakReference(this)

        flutterBoost = FlutterBoost.instance()
        flutterBoostPlugin = flutterBoost.channel()

        notifyScreenInfoChange()
    }


    /**
     * 动态传参
     * 屏幕信息
     */
    private fun notifyScreenInfoChange() {
        FlutterBoostPlugin.addActionAfterRegistered {
            val map: MutableMap<String, Any?> = HashMap()
            val density = WindowUtils.getScreenDisplayMetrics(this)?.density ?: 480f
            map["screen_width"] = WindowUtils.getScreenDisplayMetrics(this)?.widthPixels?.let {
                it / density
            } ?: 360f
            map["screen_height"] = WindowUtils.getScreenDisplayMetrics(this)?.heightPixels?.let {
                it / density
            } ?: 640f
            map["density"] = density
            FlutterBoost.instance().channel().sendEvent("device/getScreenInfo", map)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        activityReference?.clear()
        activityReference = null
    }

    companion object {
        var activityReference: WeakReference<Activity>? = null

        fun isFinishing(): Boolean {
            return getActivity()?.isFinishing ?: true
        }

        fun getActivity(): Activity? {
            return activityReference?.get()
        }
    }
}