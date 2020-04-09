package com.github.hailouwang.demosforapi.widget.da.monitor;

import androidx.recyclerview.widget.RecyclerView;

public interface RecyclerViewRangeOp {
    int[] getRecyclerViewDataItemRange(RecyclerView rv, RecyclerView.Adapter recyclerViewAdapter);
}
