package com.github.hailouwang.demosforapi.router.demo

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.github.hailouwang.demosforapi.DemosApp
import com.github.hailouwang.demosforapi.R
import com.github.hailouwang.demosforapi.main.ui.DemosForApiActivity
import com.github.hailouwang.demosforapi.router.page.testinject.TestObj
import com.github.hailouwang.demosforapi.router.page.testinject.TestParcelable
import com.github.hailouwang.demosforapi.router.page.testinject.TestSerializable
import com.github.hailouwang.demosforapi.router.page.testservice.AndroidGoPathReplaceService
import com.github.hailouwang.demosforapi.router.page.testservice.HelloService
import com.github.hailouwang.demosforapi.router.page.testservice.SingleService
import kotlinx.android.synthetic.main.aroute_demo1_fragment.*
import java.util.*

/**
 * https://www.jianshu.com/p/857aea5b54a8
 */
class ARouteDemo1Fragment : Fragment(),
    View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.aroute_demo1_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openLog.setOnClickListener(this::onClick)
        openDebug.setOnClickListener(this::onClick)
        init.setOnClickListener(this::onClick)
        destroy.setOnClickListener(this::onClick)
        normalNavigation.setOnClickListener(this::onClick)
        kotlinNavigation.setOnClickListener(this::onClick)
        normalNavigation2.setOnClickListener(this::onClick)
        getFragment.setOnClickListener(this::onClick)
        normalNavigationWithParams.setOnClickListener(this::onClick)
        oldVersionAnim.setOnClickListener(this::onClick)
        newVersionAnim.setOnClickListener(this::onClick)
        navByUrl.setOnClickListener(this::onClick)
        interceptor.setOnClickListener(this::onClick)
        autoInject.setOnClickListener(this::onClick)
        navByName.setOnClickListener(this::onClick)
        navByType.setOnClickListener(this::onClick)
        callSingle.setOnClickListener(this::onClick)
        navToMoudle1.setOnClickListener(this::onClick)
        navToMoudle2.setOnClickListener(this::onClick)
        failNav.setOnClickListener(this::onClick)
        failNav2.setOnClickListener(this::onClick)
        failNav3.setOnClickListener(this::onClick)
        replaceService.setOnClickListener(this::onClick)
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.openLog -> ARouter.openLog()
            R.id.openDebug -> ARouter.openDebug()
            R.id.init -> {
                // 调试模式不是必须开启，但是为了防止有用户开启了InstantRun，但是
                // 忘了开调试模式，导致无法使用Demo，如果使用了InstantRun，必须在
                // 初始化之前开启调试模式，但是上线前需要关闭，InstantRun仅用于开
                // 发阶段，线上开启调试模式有安全风险，可以使用BuildConfig.DEBUG
                // 来区分环境
                ARouter.openDebug()
                ARouter.init(DemosApp.getApp())
            }
            R.id.normalNavigation -> ARouter.getInstance()
                .build("/test/activity2")
                .navigation()
            R.id.kotlinNavigation -> ARouter.getInstance()
                .build("/kotlin/test")
                .withString("name", "老王")
                .withInt("age", 23)
                .navigation()
            R.id.normalNavigationWithParams -> {
                // ARouter.getInstance()
//         .build("/test/activity2")
//         .withString("key1", "value1")
//         .navigation();
                val testUriMix =
                    Uri.parse("arouter://m.aliyun.com/test/activity2")
                ARouter.getInstance().build(testUriMix)
                    .withString("key1", "value1")
                    .navigation()
            }
            R.id.oldVersionAnim -> ARouter.getInstance()
                .build("/test/activity2")
                .withTransition(
                    R.anim.slide_in_bottom,
                    R.anim.slide_out_bottom
                )
                .navigation(context)
            R.id.newVersionAnim -> if (Build.VERSION.SDK_INT >= 16) {
                val compat =
                    ActivityOptionsCompat.makeScaleUpAnimation(
                        v,
                        v.width / 2,
                        v.height / 2,
                        0,
                        0
                    )
                ARouter.getInstance()
                    .build("/test/activity2")
                    .withOptionsCompat(compat)
                    .navigation()
            } else {
                Toast.makeText(
                    context,
                    "API < 16,不支持新版本动画",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.interceptor -> ARouter.getInstance()
                .build("/test/activity4")
                .navigation(context, object : NavCallback() {
                    override fun onArrival(postcard: Postcard) {}
                    override fun onInterrupt(postcard: Postcard) {
                        Log.d("ARouter", "被拦截了")
                    }
                })
            R.id.navByUrl -> ARouter.getInstance()
                .build("/test/webview")
                .withString("url", "file:///android_asset/scheme-test.html")
                .navigation()
            R.id.autoInject -> {
                val testSerializable = TestSerializable("Titanic", 555)
                val testParcelable = TestParcelable("jack", 666)
                val testObj = TestObj("Rose", 777)
                val objList: MutableList<TestObj> =
                    ArrayList()
                objList.add(testObj)
                val map: MutableMap<String, List<TestObj>> =
                    HashMap()
                map["testMap"] = objList
                ARouter.getInstance().build("/test/activity1")
                    .withString("name", "老王")
                    .withInt("age", 18)
                    .withBoolean("boy", true)
                    .withLong("high", 180)
                    .withString("url", "https://a.b.c")
                    .withSerializable("ser", testSerializable)
                    .withParcelable("pac", testParcelable)
                    .withObject("obj", testObj)
                    .withObject("objList", objList)
                    .withObject("map", map)
                    .navigation()
            }
            R.id.navByName -> (ARouter.getInstance().build("/yourservicegroupname/hello")
                .navigation() as HelloService).sayHello(
                "mike"
            )
            R.id.navByType -> ARouter.getInstance().navigation(
                HelloService::class.java
            ).sayHello("mike")
            R.id.navToMoudle1 -> ARouter.getInstance().build("/module/1").navigation()
            R.id.navToMoudle2 ->  // 这个页面主动指定了Group名
                ARouter.getInstance().build("/module/2", "m2").navigation()
            R.id.destroy -> ARouter.getInstance().destroy()
            R.id.failNav -> ARouter.getInstance().build("/xxx/xxx").navigation(
                context,
                object : NavCallback() {
                    override fun onFound(postcard: Postcard) {
                        Log.d("ARouter", "找到了")
                    }

                    override fun onLost(postcard: Postcard) {
                        Log.d("ARouter", "找不到了")
                    }

                    override fun onArrival(postcard: Postcard) {
                        Log.d("ARouter", "跳转完了")
                    }

                    override fun onInterrupt(postcard: Postcard) {
                        Log.d("ARouter", "被拦截了")
                    }
                })
            R.id.callSingle -> ARouter.getInstance().navigation(
                SingleService::class.java
            ).sayHello("Mike")
            R.id.failNav2 -> ARouter.getInstance().build("/xxx/xxx").navigation()
            R.id.failNav3 -> ARouter.getInstance().navigation(
                DemosForApiActivity::class.java
            )
            R.id.normalNavigation2 -> ARouter.getInstance()
                .build("/test/activity2")
                .navigation(activity, 666)
            R.id.getFragment -> {
                val fragment =
                    ARouter.getInstance().build("/test/fragment").navigation() as Fragment
                Toast.makeText(
                    context,
                    "找到Fragment:$fragment",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.replaceService -> {
                ARouter.getInstance().build("/test/fragment");
            }
            else -> {
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            666 -> Log.e("activityResult", resultCode.toString())
            else -> {
            }
        }
    }
}