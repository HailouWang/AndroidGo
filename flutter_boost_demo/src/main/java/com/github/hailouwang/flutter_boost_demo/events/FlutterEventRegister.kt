package com.github.hailouwang.flutter_boost_demo.events

import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostPlugin

class FlutterEventRegister {
    companion object {
        fun register() {
            FlutterBoostPlugin.addActionAfterRegistered {
                FlutterBoost.instance().channel()
                    .addEventListener(
                        "eventName"
                    ) { name: String?, args: MutableMap<Any?, Any?>? ->
//                        if (LogUtil.isDebug()) {
//                            Log.d(
//                                Tags.HLWANG,
//                                "FlutterEventRegister onEvent name : ${name} , args : ${args} "
//                            )
//                        }
                    }
            }
        }

        fun release() {

        }
    }
}