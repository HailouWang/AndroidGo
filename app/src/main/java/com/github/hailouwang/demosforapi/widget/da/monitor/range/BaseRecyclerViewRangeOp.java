package com.github.hailouwang.demosforapi.widget.da.monitor.range;

import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.widget.da.monitor.RecyclerViewRangeOp;

public abstract class BaseRecyclerViewRangeOp implements RecyclerViewRangeOp {

    /**
     * 计算列表中数据item起始范围
     *
     * @param recyclerView
     * @param firstVisiblePos
     * @param lastVisiblePos
     * @return 如果没有数据item，或数据item未显示，返回null
     */
    protected int[] statDataItemRange(RecyclerView recyclerView, RecyclerView.Adapter recyclerViewAdapter,
                                      int firstVisiblePos, int lastVisiblePos) {
        if (recyclerView == null) {
            return new int[2];
        }

        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = recyclerView.getAdapter();
        }

        if (recyclerViewAdapter == null || recyclerViewAdapter.getItemCount() == 0) {
            return new int[2];
        }

        int dataItemFirstPos = firstVisiblePos;
        int dataItemLastPos = lastVisiblePos;

        if (dataItemFirstPos < 0) {
            dataItemFirstPos = 0;
        }

        if (dataItemLastPos < 0) {
            dataItemLastPos = 0;
        }

        int[] rangeArray = new int[2];
        rangeArray[0] = dataItemFirstPos;
        rangeArray[1] = dataItemLastPos;
        return rangeArray;
    }
}
