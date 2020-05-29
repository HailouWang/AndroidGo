package com.hailouwang.fragmentrouter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log

object ActivityUtils {
    fun startActivitySafely(context: Context, intent: Intent?) {
        try {
            context.startActivity(intent)
        } catch (var3: ActivityNotFoundException) {
            if (TransactionFragmentLogConfig.LOCAL_LOGD) {
                Log.d(Tags.TAGS_MAIN, "ActivityUtils startActivitySafely : " + var3.message)
            }
        } catch (var4: SecurityException) {
            if (TransactionFragmentLogConfig.LOCAL_LOGD) {
                Log.d(Tags.TAGS_MAIN, "ActivityUtils startActivitySafely : " + var4.message)
            }
        }
    }
}