package com.github.hailouwang.demosforapi.widget.da.monitor;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.github.hailouwang.demosforapi.widget.da.monitor.range.DefaultRecyclerViewRangeOp;
import com.github.hailouwang.demosforapi.widget.da.monitor.range.LinearLayoutManagerRangeOp;
import com.github.hailouwang.demosforapi.widget.da.monitor.range.StaggeredGridLayoutManagerRangeOp;

import javax.annotation.Nullable;

public class RecyclerViewRangeOpFactory {
    public static @Nullable
    RecyclerViewRangeOp createRecyclerViewRangeOp(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return null;
        }

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return new LinearLayoutManagerRangeOp().setLinearLayoutManager(((LinearLayoutManager) layoutManager));
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return new StaggeredGridLayoutManagerRangeOp().setStaggeredGridLayoutManager((StaggeredGridLayoutManager) layoutManager);
        } else {
            return null;
        }
    }
}
