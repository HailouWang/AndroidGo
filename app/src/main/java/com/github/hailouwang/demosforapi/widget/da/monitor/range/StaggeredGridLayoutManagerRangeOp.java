package com.github.hailouwang.demosforapi.widget.da.monitor.range;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class StaggeredGridLayoutManagerRangeOp extends BaseRecyclerViewRangeOp {

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public int[] getRecyclerViewDataItemRange(RecyclerView rv, RecyclerView.Adapter recyclerViewAdapter) {
        return statDataItemRange(rv, recyclerViewAdapter,
                findFirstVisibleItemPosition(rv),
                findLastVisibleItemPosition(rv));
    }

    public StaggeredGridLayoutManagerRangeOp setStaggeredGridLayoutManager(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
        return this;
    }

    private int findFirstVisibleItemPosition(RecyclerView rv) {
        if (rv == null || staggeredGridLayoutManager == null) {
            return 0;
        }

        int[] spanCount = new int[staggeredGridLayoutManager.getSpanCount()];
        return findMin(staggeredGridLayoutManager.findFirstVisibleItemPositions(spanCount));
    }

    private int findLastVisibleItemPosition(RecyclerView rv) {
        if (rv == null || staggeredGridLayoutManager == null) {
            return 0;
        }

        int[] spanCount = new int[staggeredGridLayoutManager.getSpanCount()];
        return findMax(staggeredGridLayoutManager.findLastVisibleItemPositions(spanCount));
    }

    /**
     * 查找数组中最小值
     *
     * @param intArray
     * @return
     */
    private int findMin(int[] intArray) {

        if (intArray == null || intArray.length == 0) {
            return -1;
        }

        int min = intArray[0];
        for (int value : intArray) {
            if (value < min) {
                min = value;
            }
        }

        return min;
    }

    /**
     * 查找数组中最大值
     *
     * @param intArray
     * @return
     */
    private int findMax(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return -1;
        }

        int max = intArray[0];
        for (int value : intArray) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
