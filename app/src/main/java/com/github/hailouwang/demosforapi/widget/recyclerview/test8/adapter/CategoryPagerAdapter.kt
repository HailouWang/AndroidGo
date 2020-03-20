package com.github.hailouwang.demosforapi.widget.recyclerview.test8.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.github.hailouwang.demosforapi.widget.recyclerview.test8.ChildRecyclerView

class CategoryPagerAdapter(
    private val viewList: ArrayList<ChildRecyclerView>,
    private val tabTitleList: ArrayList<String>
) : PagerAdapter() {
    override fun getCount(): Int {
        return viewList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = viewList[position]
        if (container == view.parent) {
            container.removeView(view)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //container.removeView((View) object);
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitleList[position]
    }
}