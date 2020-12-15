package com.github.hailouwang.flutter_boost_demo.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

object WindowUtils {
    fun getScreenDisplayMetrics(context: Context?): DisplayMetrics? {
        if (context == null) {
            return null
        }
        val screenDisplayMetrics = DisplayMetrics()
        try {
            val wm =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay

            // Get the real display metrics if we are using API level 17 or higher.
            // The real metrics include system decor elements (e.g. soft menu bar).
            //
            // See: http://developer.android.com/reference/android/view/Display.html#getRealMetrics(android.util.DisplayMetrics)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                display.getRealMetrics(screenDisplayMetrics)
            } else {
                // For 14 <= API level <= 16, we need to invoke getRawHeight and getRawWidth to get the real dimensions.
                //
                // Reflection exceptions are rethrown at runtime.
                //
                // See: http://stackoverflow.com/questions/14341041/how-to-get-real-screen-height-and-width/23861333#23861333
                val mGetRawH =
                    Display::class.java.getMethod("getRawHeight")
                val mGetRawW =
                    Display::class.java.getMethod("getRawWidth")
                screenDisplayMetrics.widthPixels = (mGetRawW.invoke(display) as Int)
                screenDisplayMetrics.heightPixels = (mGetRawH.invoke(display) as Int)
            }
        } catch (t: Throwable) {
            val displayMetrics: DisplayMetrics? = getMetrics(context)
            screenDisplayMetrics.setTo(displayMetrics)
        }
        return screenDisplayMetrics
    }

    fun getMetrics(context: Context?): DisplayMetrics? {
        return if (context == null) {
            null
        } else {
            val resources = context.resources
            resources?.displayMetrics
        }
    }
}