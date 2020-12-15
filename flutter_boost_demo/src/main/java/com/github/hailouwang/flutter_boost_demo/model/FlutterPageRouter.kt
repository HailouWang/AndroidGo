package com.github.hailouwang.flutter_boost_demo.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.github.hailouwang.flutter_boost_demo.ui.container.AndroidGoFlutterContainerActivity
import com.github.hailouwang.flutter_boost_demo.ui.demo.FlutterFragmentPageActivity
import com.github.hailouwang.flutter_boost_demo.ui.demo.NativePageActivity
import com.github.hailouwang.flutter_boost_demo.ui.notfound.AndroidGoPageNotFound
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import java.util.*

object FlutterPageRouter {
    val pageName: Map<String, String> =
        object : HashMap<String, String>() {
            init {
                put("first", "first")
                put("second", "second")
                put("tab", "tab")
                put("sample://flutterPage", "flutterPage")
                put("main", "main")
            }
        }
    const val NATIVE_PAGE_URL = "sample://nativePage"
    const val FLUTTER_PAGE_URL = "sample://flutterPage"
    const val FLUTTER_FRAGMENT_PAGE_URL = "sample://flutterFragmentPage"
    const val FLUTTER_DEMO_LIST_MAIN = "main"

    @JvmOverloads
    fun openPageByUrl(
        context: Context,
        url: String,
        params: Map<Any, Any> = mutableMapOf(),
        requestCode: Int = 0
    ): Boolean {
        val path = url.split("\\?".toRegex()).toTypedArray()[0]
        Log.i("openPageByUrl", path)
        return try {
            if (pageName.containsKey(path)) {
                val intent = BoostFlutterActivity.NewEngineIntentBuilder(
                    AndroidGoFlutterContainerActivity::class.java
                ).url(pageName.get(path)!!).params(params)
                    .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context)
                if (context is Activity) {
                    context.startActivityForResult(intent, requestCode)
                } else {
                    context.startActivity(intent)
                }
                return true
            } else if (url.startsWith(FLUTTER_FRAGMENT_PAGE_URL)) {
                context.startActivity(
                    Intent(
                        context,
                        FlutterFragmentPageActivity::class.java
                    )
                )
                return true
            } else if (url.startsWith(NATIVE_PAGE_URL)) {
                context.startActivity(
                    Intent(
                        context,
                        NativePageActivity::class.java
                    )
                )
                return true
            }

            context.startActivity(
                Intent(
                    context,
                    AndroidGoPageNotFound::class.java
                )
            )

            true
        } catch (t: Throwable) {
            t.printStackTrace()
            false
        }
    }
}