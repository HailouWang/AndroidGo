package com.github.hailouwang.demosforapi.flutter.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.hailouwang.demosforapi.R
import io.flutter.plugin.platform.PlatformView

class PlatformNativeView(context: Context, viewId: Int, args: Any) : PlatformView {

    var selfView: View

    init {
        selfView = LayoutInflater.from(context).inflate(R.layout.platform_native_view, null, false)
    }

    override fun getView(): View {
        return selfView
    }

    override fun dispose() {}
}