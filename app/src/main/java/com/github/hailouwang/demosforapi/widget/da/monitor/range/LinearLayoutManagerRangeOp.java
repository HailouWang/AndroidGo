package com.github.hailouwang.demosforapi.widget.da.monitor.range;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LinearLayoutManagerRangeOp extends BaseRecyclerViewRangeOp {

    private LinearLayoutManager linearLayoutManager;

    @Override
    public int[] getRecyclerViewDataItemRange(RecyclerView rv, RecyclerView.Adapter recyclerViewAdapter) {
        return statDataItemRange(rv, recyclerViewAdapter,
                findFirstVisibleItemPosition(rv),
                findLastVisibleItemPosition(rv));
    }

    public LinearLayoutManagerRangeOp setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
        return this;
    }

    private int findFirstVisibleItemPosition(RecyclerView rv) {
        if (rv == null || linearLayoutManager == null) {
            return 0;
        }

        return linearLayoutManager.findFirstVisibleItemPosition();
    }

    private int findLastVisibleItemPosition(RecyclerView rv) {
        if (rv == null || linearLayoutManager == null) {
            return 0;
        }

        return linearLayoutManager.findLastVisibleItemPosition();
    }
}
