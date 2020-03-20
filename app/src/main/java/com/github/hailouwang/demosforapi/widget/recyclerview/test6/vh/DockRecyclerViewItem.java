package com.github.hailouwang.demosforapi.widget.recyclerview.test6.vh;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.github.hailouwang.demosforapi.R;

public class DockRecyclerViewItem extends BaseRecyclerViewAdapterVh1 {

    public DockRecyclerViewItem(@NonNull Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.dock_test5_dock_vh, null));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data) {
    }

}
