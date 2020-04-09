package com.github.hailouwang.demosforapi.main.router

import com.github.hailouwang.demosforapi.widget.da.ui.DataAnalysisRecyclerViewFragment
import com.github.hailouwang.demosforapi.widget.lifecycle.VisibleToUserChangeFragment
import com.hailouwang.fragmentrouter.BMRouterFragmentActivity

class RouterActivity : BMRouterFragmentActivity() {

    companion object {
        private val ENTRY_FRAGMENTS: MutableSet<String> = HashSet()

        init {
            ENTRY_FRAGMENTS.add(VisibleToUserChangeFragment::class.java.name)
            ENTRY_FRAGMENTS.add(DataAnalysisRecyclerViewFragment::class.java.name)
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