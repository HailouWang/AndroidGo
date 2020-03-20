package com.github.hailouwang.demosforapi.widget.recyclerview.test8.holder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.github.hailouwang.demosforapi.R
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.CategoryView
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.ChildRecyclerView
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.adapter.CategoryPagerAdapter
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.bean.CategoryBean
import com.google.android.material.tabs.TabLayout

class SimpleCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTabLayout: TabLayout = itemView.findViewById(R.id.tabs) as TabLayout
    private val mViewPager: ViewPager = itemView.findViewById(R.id.viewPager) as ViewPager

    val viewList = ArrayList<ChildRecyclerView>()

    private var mCurrentRecyclerView :ChildRecyclerView? = null

    init {

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if(viewList.isEmpty().not()) {
                    mCurrentRecyclerView = viewList[position]
                    Log.d("gaohui","onPageSelected: $position $mCurrentRecyclerView")
                }
            }
            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    fun bindData(obj: Any) {
        //TODO 需要优化，这里每次item被回收时都会重新setupWithViewPager
        (obj as? CategoryBean)?.apply {
            viewList.clear()
            tabTitleList.forEach{ _ ->
                val categoryView = CategoryView(itemView.context)
                viewList.add(categoryView)
            }
            mCurrentRecyclerView = viewList[mViewPager.currentItem]
            val lastItem = mViewPager.currentItem
            Log.d("gaohui","bindData: ${mViewPager.currentItem} $mCurrentRecyclerView")

            mViewPager.adapter = CategoryPagerAdapter(viewList,tabTitleList)
            mTabLayout.setupWithViewPager(mViewPager)
            mViewPager.currentItem = lastItem
        }
    }

    fun getCurrentChildRecyclerView(): ChildRecyclerView? {
        return mCurrentRecyclerView
    }
}