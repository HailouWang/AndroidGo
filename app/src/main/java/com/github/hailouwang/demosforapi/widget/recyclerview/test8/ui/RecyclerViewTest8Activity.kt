package com.github.hailouwang.demosforapi.widget.recyclerview.test8.ui;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.hailouwang.demosforapi.R
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.ParentRecyclerView
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.adapter.MultiTypeAdapter
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.bean.CategoryBean
import java.util.*

class RecyclerViewTest8Activity : AppCompatActivity() {

    private val mDataList = ArrayList<Any>()

    private val strArray = arrayOf("关注", "推荐", "视频", "直播", "图片", "段子", "精华", "热门")
//    private val strArray = arrayOf("关注")

    var lastBackPressedTime = 0L

    lateinit var parentRecyclerView:ParentRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_test8)

        parentRecyclerView = findViewById(R.id.parentRecyclerView)

        parentRecyclerView.initLayoutManager()

        initData()
    }

    private fun initData() {
        val multiTypeAdapter = MultiTypeAdapter(mDataList)
        for (i in 0..8) {
            mDataList.add("parent item text $i")
        }
        val categoryBean = CategoryBean()
        categoryBean.tabTitleList.clear()
        categoryBean.tabTitleList.addAll(strArray.asList())
        mDataList.add(categoryBean)
        parentRecyclerView.adapter = multiTypeAdapter
        multiTypeAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastBackPressedTime < 2000) {
            super.onBackPressed()
        } else {
            parentRecyclerView.scrollToPosition(0)
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show()
            lastBackPressedTime = System.currentTimeMillis()
        }
    }
}
