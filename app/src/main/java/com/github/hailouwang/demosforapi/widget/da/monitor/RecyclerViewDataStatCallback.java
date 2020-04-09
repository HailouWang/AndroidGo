package com.github.hailouwang.demosforapi.widget.da.monitor;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import javax.annotation.Nullable;

/**
 * 曝光的列表Item 回调
 */
public interface RecyclerViewDataStatCallback {
    void onRecyclerViewStatView(RecyclerView recyclerView, @Nullable RecyclerView.Adapter adapter, View view, RecyclerView.ViewHolder viewHolder, int position, int viewType);
}
