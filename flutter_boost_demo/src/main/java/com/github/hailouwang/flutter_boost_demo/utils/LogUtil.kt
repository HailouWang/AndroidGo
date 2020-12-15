package com.github.hailouwang.flutter_boost_demo.utils

import com.github.hailouwang.flutter_boost_demo.BuildConfig

object LogUtil {
    fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}

object Tags {
    const val HLWANG = "hlwang"
}