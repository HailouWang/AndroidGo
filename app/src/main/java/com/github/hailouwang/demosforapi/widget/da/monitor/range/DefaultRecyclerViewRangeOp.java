package com.github.hailouwang.demosforapi.widget.da.monitor.range;

import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.widget.da.monitor.RecyclerViewRangeOp;

public class DefaultRecyclerViewRangeOp implements RecyclerViewRangeOp {
    @Override
    public int[] getRecyclerViewDataItemRange(RecyclerView rv, RecyclerView.Adapter recyclerViewAdapter) {
        return new int[2];
    }
}
