package com.github.hailouwang.demosforapi.main.router

import com.github.hailouwang.demosforapi.annotation.AnnotationDemoFragment
import com.github.hailouwang.demosforapi.classloader.ClassLoaderDemo1
import com.github.hailouwang.demosforapi.classloader.ClassLoaderInitLifecycleDemoFragment
import com.github.hailouwang.demosforapi.classloader.ClassLoaderInitStaticMethodInvokeDemoFragment
import com.github.hailouwang.demosforapi.drawable.cache.ThreeCacheDemoFragment
import com.github.hailouwang.demosforapi.flutter.demo1.FlutterDemoFragment1
import com.github.hailouwang.demosforapi.java.TestJavaFragment
import com.github.hailouwang.demosforapi.livedata.LiveDataDemoObserveForeverFragment
import com.github.hailouwang.demosforapi.livedata.LiveDataDemoObserverFragment
import com.github.hailouwang.demosforapi.mmkv.MMKVDemoFragment
import com.github.hailouwang.demosforapi.poet.PoetDemoFragment
import com.github.hailouwang.demosforapi.proxy.JavaProxyDemoFragment
import com.github.hailouwang.demosforapi.room.RoomDemoFragment
import com.github.hailouwang.demosforapi.router.demo.ARouteDemo1Fragment
import com.github.hailouwang.demosforapi.rxjava.AutoisponseDemoFragment
import com.github.hailouwang.demosforapi.rxjava.BackpressDemoFragment
import com.github.hailouwang.demosforapi.rxjava.ObservableLifeCycle
import com.github.hailouwang.demosforapi.rxjava.SingleDemoFragment
import com.github.hailouwang.demosforapi.shortvideo.demo1.ShortVideoDianboFragment
import com.github.hailouwang.demosforapi.shortvideo.demo1.ShortVideoMultiItemFragment
import com.github.hailouwang.demosforapi.shortvideo.demo1.ShortVideoRawOrAssetsFragment
import com.github.hailouwang.demosforapi.shortvideo.demo1.ShortVideoZhiboFragment
import com.github.hailouwang.demosforapi.shortvideo.extend.ShortVideoDanmakuFragment
import com.github.hailouwang.demosforapi.shortvideo.extend.ShortVideoFullscreenDirectlyFragment
import com.github.hailouwang.demosforapi.shortvideo.list.*
import com.github.hailouwang.demosforapi.statemachine.StateMachineHelloWorldFragment
import com.github.hailouwang.demosforapi.statemachine.StateMachineMoreStateFragment
import com.github.hailouwang.demosforapi.thread.SafeStopThreadFragment
import com.github.hailouwang.demosforapi.view.demo1.GlobalVisibleForViewFragment
import com.github.hailouwang.demosforapi.view.demo1.LocalVisibleForViewFragment
import com.github.hailouwang.demosforapi.view.demo2.GlobalVisibleForViewFragment2
import com.github.hailouwang.demosforapi.view.demo2.LocalVisibleForViewFragment2
import com.github.hailouwang.demosforapi.view.demo2.LocalVisibleForViewFragment2222
import com.github.hailouwang.demosforapi.view.demo3.Demo3Fragment
import com.github.hailouwang.demosforapi.view.performance.AnalysisPerformanceFragment
import com.github.hailouwang.demosforapi.view.performance.PerformanceLinearLayoutNoWeightFragment
import com.github.hailouwang.demosforapi.view.performance.PerformanceLinearLayoutWeightFragment
import com.github.hailouwang.demosforapi.view.performance.PerformanceRelativeLayoutFragment
import com.github.hailouwang.demosforapi.widget.autowrapline.AutoWrapLineDemoFragment
import com.github.hailouwang.demosforapi.widget.da.ui.DataAnalysisRecyclerViewFragment
import com.github.hailouwang.demosforapi.widget.lifecycle.VisibleToUserChangeFragment
import com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.demo.LifeCyclerDemoFragment
import com.hailouwang.fragmentbackhandler.BackHandlerHelper
import com.hailouwang.fragmentrouter.BMRouterFragmentActivity

open class RouterActivity : BMRouterFragmentActivity() {

    companion object {
        private val ENTRY_FRAGMENTS: MutableSet<String> = HashSet()

        init {
            ENTRY_FRAGMENTS.add(VisibleToUserChangeFragment::class.java.name)
            ENTRY_FRAGMENTS.add(DataAnalysisRecyclerViewFragment::class.java.name)
            ENTRY_FRAGMENTS.add(LifeCyclerDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(FlutterDemoFragment1::class.java.name)
            ENTRY_FRAGMENTS.add(ARouteDemo1Fragment::class.java.name)
            ENTRY_FRAGMENTS.add(LocalVisibleForViewFragment::class.java.name)
            ENTRY_FRAGMENTS.add(GlobalVisibleForViewFragment::class.java.name)
            ENTRY_FRAGMENTS.add(GlobalVisibleForViewFragment2::class.java.name)
            ENTRY_FRAGMENTS.add(LocalVisibleForViewFragment2::class.java.name)
            ENTRY_FRAGMENTS.add(LocalVisibleForViewFragment2222::class.java.name)
            ENTRY_FRAGMENTS.add(TestJavaFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ClassLoaderDemo1::class.java.name)
            ENTRY_FRAGMENTS.add(AnnotationDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(JavaProxyDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(PerformanceLinearLayoutNoWeightFragment::class.java.name)
            ENTRY_FRAGMENTS.add(PerformanceLinearLayoutWeightFragment::class.java.name)
            ENTRY_FRAGMENTS.add(PerformanceRelativeLayoutFragment::class.java.name)
            ENTRY_FRAGMENTS.add(AnalysisPerformanceFragment::class.java.name)
            ENTRY_FRAGMENTS.add(MMKVDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(LiveDataDemoObserveForeverFragment::class.java.name)
            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
            ENTRY_FRAGMENTS.add(RoomDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ThreeCacheDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(SafeStopThreadFragment::class.java.name)
            ENTRY_FRAGMENTS.add(PoetDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(Demo3Fragment::class.java.name)
            ENTRY_FRAGMENTS.add(SingleDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(BackpressDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(AutoisponseDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(AutoWrapLineDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoDianboFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ObservableLifeCycle::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoZhiboFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoRawOrAssetsFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoMultiItemFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoRecyclerViewFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoRecyclerViewAutoPlayFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoTikTokListFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoSeamlessFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoStartFullScreenFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoFullscreenDirectlyFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ShortVideoDanmakuFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ClassLoaderInitLifecycleDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(ClassLoaderInitStaticMethodInvokeDemoFragment::class.java.name)
            ENTRY_FRAGMENTS.add(StateMachineHelloWorldFragment::class.java.name)
            ENTRY_FRAGMENTS.add(StateMachineMoreStateFragment::class.java.name)

//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(LiveDataDemoObserverFragment::class.java.name)
        }
    }

    override fun defaultFragment(): String {
        // 当前不需要 此Activity 内置Fragment ，故：此处，返回 null
        return ""
    }

    override fun isValidFragment(fragmentName: String?): Boolean {
        return ENTRY_FRAGMENTS.contains(fragmentName)
    }

    override fun onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed()
        }
    }
}