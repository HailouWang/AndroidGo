package com.github.hailouwang.flutter_boost_demo.model

import android.app.Application
import android.os.Build
import com.github.hailouwang.flutter_boost_demo.widget.TextPlatformViewFactory
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoost.BoostLifecycleListener
import com.idlefish.flutterboost.Utils
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.embedding.android.FlutterView
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec

class ApplicationFlutterModel {
    fun init(application: Application) {
        val router =
            INativeRouter { context, url, urlParams, requestCode, exts ->
                val assembleUrl =
                    Utils.assembleUrl(url, urlParams)
                FlutterPageRouter.openPageByUrl(
                    context,
                    assembleUrl
                )
            }

        val boostLifecycleListener: BoostLifecycleListener = object : BoostLifecycleListener {
            override fun beforeCreateEngine() {}
            override fun onEngineCreated() {

                // 注册MethodChannel，监听flutter侧的getPlatformVersion调用
                val methodChannel = MethodChannel(
                    FlutterBoost.instance().engineProvider().dartExecutor,
                    "flutter_native_channel"
                )
                methodChannel.setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method == "getPlatformVersion") {
                        result.success(Build.VERSION.RELEASE)
                    } else {
                        result.notImplemented()
                    }
                }

                // 注册PlatformView viewTypeId要和flutter中的viewType对应
                FlutterBoost
                    .instance()
                    .engineProvider()
                    .platformViewsController
                    .registry
                    .registerViewFactory(
                        "plugins.test/view",
                        TextPlatformViewFactory(
                            StandardMessageCodec.INSTANCE
                        )
                    )
            }

            override fun onPluginsRegistered() {}
            override fun onEngineDestroy() {}
        }

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //


        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //
        val platform = FlutterBoost.ConfigBuilder(application, router)
            .isDebug(true)
            .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
            .renderMode(FlutterView.RenderMode.texture)
            .lifecycleListener(boostLifecycleListener)
            .build()
        FlutterBoost.instance().init(platform)
    }
}