package com.github.hailouwang.demosforapi.main.router

import com.github.hailouwang.demosforapi.annotation.AnnotationDemoFragment
import com.github.hailouwang.demosforapi.classloader.ClassLoaderDemo1
import com.github.hailouwang.demosforapi.flutter.demo1.FlutterDemoFragment1
import com.github.hailouwang.demosforapi.java.TestJavaFragment
import com.github.hailouwang.demosforapi.router.demo.ARouteDemo1Fragment
import com.github.hailouwang.demosforapi.view.demo1.GlobalVisibleForViewFragment
import com.github.hailouwang.demosforapi.view.demo1.LocalVisibleForViewFragment
import com.github.hailouwang.demosforapi.view.demo2.GlobalVisibleForViewFragment2
import com.github.hailouwang.demosforapi.view.demo2.LocalVisibleForViewFragment2
import com.github.hailouwang.demosforapi.view.demo2.LocalVisibleForViewFragment2222
import com.github.hailouwang.demosforapi.widget.da.ui.DataAnalysisRecyclerViewFragment
import com.github.hailouwang.demosforapi.widget.lifecycle.VisibleToUserChangeFragment
import com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.demo.LifeCyclerDemoFragment
import com.hailouwang.fragmentrouter.BMRouterFragmentActivity

class RouterActivity : BMRouterFragmentActivity() {

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
//            ENTRY_FRAGMENTS.add(AnnotationDemoFragment::class.java.name)
//            ENTRY_FRAGMENTS.add(AnnotationDemoFragment::class.java.name)
        }
    }

    override fun defaultFragment(): String {
        // 当前不需要 此Activity 内置Fragment ，故：此处，返回 null
        return ""
    }

    override fun isValidFragment(fragmentName: String?): Boolean {
        return ENTRY_FRAGMENTS.contains(fragmentName)
    }
}