package com.github.hailouwang.demosforapi.widget.slidegridview

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.github.hailouwang.demosforapi.DemosApp

/**
 *
 * Created by linlif on 2020-03-16
 */


private var sScreenWidth = -1

/**
 * 获取设备屏幕宽度
 *
 * @return
 */
fun getScreenWidth(): Int {

    if (sScreenWidth > 0) {
        return sScreenWidth
    }

    val displayMetrics = getDisplayMetrics(DemosApp.context)
    var screenWidth =
        if (displayMetrics != null) displayMetrics!!.widthPixels else 1080
    val srceenHeight =
        if (displayMetrics != null) displayMetrics!!.heightPixels else 1920

    /**
     * 针对获取到屏幕宽度大于屏幕高度情况下：
     * 尝试解决屏幕因某种情况下获取屏幕宽高信息交换
     * ，导致APP样式展示错乱问题，因此需要width与height的值进行替换处理
     */
    if (screenWidth > srceenHeight) {

        screenWidth = srceenHeight
    }

    sScreenWidth = screenWidth
    // 1. 优先走自定义方案获取 DisplayMetrics对象，否则使用 640dp
    return screenWidth
}

/***
 * 获取DisplayMetrics对象
 *
 * @param context
 * @return
 */
private fun getDisplayMetrics(context: Context?): DisplayMetrics? {

    if (context == null) {

        return null
    }

    val screenDisplayMetrics = DisplayMetrics()
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay

    try {

        // Get the real display metrics if we are using API level 17 or higher.
        // The real metrics include system decor elements (e.g. soft menu bar).
        //
        // See: http://developer.android.com/reference/android/view/Display.html#getRealMetrics(android.util.DisplayMetrics)
        if (Build.VERSION.SDK_INT >= 17) {

            display.getRealMetrics(screenDisplayMetrics)
        } else {
            // For 14 <= API level <= 16, we need to invoke getRawHeight and getRawWidth to get the real dimensions.
            //
            // Reflection exceptions are rethrown at runtime.
            //
            // See: http://stackoverflow.com/questions/14341041/how-to-get-real-screen-height-and-width/23861333#23861333

            val mGetRawH = Display::class.java.getMethod("getRawHeight")
            val mGetRawW = Display::class.java.getMethod("getRawWidth")
            screenDisplayMetrics.widthPixels = mGetRawW.invoke(display) as Int
            screenDisplayMetrics.heightPixels = mGetRawH.invoke(display) as Int
        }

    } catch (e: Throwable) {

        val displayMetrics = context.resources.displayMetrics
        screenDisplayMetrics.setTo(displayMetrics)
    }

    return screenDisplayMetrics
}
