package com.github.hailouwang.demosforapi.view.demo2

import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.hailouwang.demosforapi.R
import kotlinx.android.synthetic.main.local_visible_for_view_fragment.*

/**
 * https://www.cnblogs.com/ai-developers/p/4413585.html
 */
class GlobalVisibleForViewFragment2 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.global_visible_for_view_fragment2,
            container,
            false
        )
    }


    // 360 480
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        innerL.setOnClickListener {
            val globalRect = Rect()
            it.getGlobalVisibleRect(globalRect)
            Log.d("hlwang", "GlobalVisibleForViewFragment ---> " + globalRect)

            val globalRect2 = Rect()
            var gobalOffset = Point();
            it.getGlobalVisibleRect(globalRect2, gobalOffset)
            Log.d("hlwang", "GlobalVisibleForViewFragment ---> globalRect2 : " + globalRect2
                        + ", gobalOffset : " + gobalOffset)
        }
    }
}