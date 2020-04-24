package com.github.hailouwang.demosforapi.view.demo2

import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.hailouwang.demosforapi.R
import kotlinx.android.synthetic.main.local_visible_for_view_fragment2.*

/**
 * https://www.cnblogs.com/ai-developers/p/4413585.html
 */
class LocalVisibleForViewFragment2222 : Fragment() {

    private var lastX = 0
    private var lastY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.local_visible_for_view_fragment2,
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
        img.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.getAction()) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = event?.getRawX().toInt();
                        lastY = event?.getRawY().toInt();
                    }

                    MotionEvent.ACTION_MOVE -> {
                        var dx = (event?.getRawX() - lastX).toInt()
                        var dy = (event?.getRawY() - lastY).toInt()

                        var left = (v?.left ?:0) + dx
                        var top = (v?.top ?:0) + dy
                        var right = (v?.right ?:0) + dx
                        var bottom = (v?.bottom ?:0) + dy

                        v?.layout(left, top, right, bottom);
                        lastX = event.getRawX().toInt()
                        lastY = event.getRawY().toInt()

                        var localRect = Rect()
                        v?.getLocalVisibleRect(localRect);
                        local.setText("local" + localRect.toString());

                        var globalRect = Rect()
                        var globalOffset = Point()
                        v?.getGlobalVisibleRect(globalRect, globalOffset);
                        global.setText("global" + globalRect.toString());
                        offset.setText("globalOffset:" + globalOffset.x + "," + globalOffset.y);
                    }
                    MotionEvent.ACTION_UP -> {
                    }
                }
                return true
            }
        })
    }
}