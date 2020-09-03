package com.github.hailouwang.demosforapi.widget.slidegridview

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.hailouwang.demosforapi.R


class SlideGridActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var seekBar: SeekBar? = null
    private var titleData = arrayListOf<String>()
    private val adapter: Adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_grid)

        for (i in 0..15) {
            titleData.add("title$i")
        }

        recyclerView = findViewById(R.id.recyclerview)
        seekBar = findViewById(R.id.seekBar)

        //设置seekbar初始状态
        seekBar?.setPadding(0, 0, 0, 0)
        seekBar?.thumbOffset = 0

        initRecyclerview()

        setData()

    }


    fun initRecyclerview() {

        //设置为竖向排列，一共两排，可以根据需求改变排列规则
        val layoutManage = GridLayoutManager(this, 2)
        layoutManage.setOrientation(GridLayoutManager.HORIZONTAL)
        recyclerView?.setLayoutManager(layoutManage)

        recyclerView?.adapter = adapter

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                //少于是个是不会超出屏幕的，不做处理
                if (titleData.size <= 10) {
                    return
                }

                val range = recyclerView.computeHorizontalScrollRange()//整体的长度，包括屏幕之外的
                val extent = recyclerView.computeHorizontalScrollExtent()//显示区域的长度
                val offset = recyclerView.computeHorizontalScrollOffset()//滑动的距离

                seekBar?.setMax(range - extent)//设置最大值为整体长度减去屏幕内的

                //设置高亮宽度
                val thumbWidth =
                    (seekBar?.getMeasuredWidth()?.times((extent.toDouble() / range.toDouble())))?.toInt()
                        ?: 0
                val gradientDrawable = seekBar?.getThumb() as GradientDrawable
                gradientDrawable.setSize(thumbWidth, 5)

                //滑动时设置seekbar进度
                if (dx == 0) {
                    seekBar?.setProgress(0)
                } else {
                    seekBar?.setProgress(offset)
                }
            }
        })
    }

    private fun setData() {

        adapter.setDara(titleData)
        adapter.notifyDataSetChanged()
        recyclerView?.scrollBy(0, 0)

        //小于10个不显示seekbar
        if (titleData.size > 10) {
            seekBar?.visibility = View.VISIBLE
        } else {
            seekBar?.visibility = View.GONE

        }

    }
}
