package com.github.hailouwang.demosforapi.widget.recyclerview.test5.vh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.main.data.DemosLibData;
import com.github.hailouwang.demosforapi.main.ui.DemosForApiAdapter;

import java.util.List;
import java.util.Map;

public class DockRecyclerViewItem extends BaseRecyclerViewAdapterVh1 {

    public DockRecyclerViewItem(@NonNull Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.dock_test5_dock_vh, null));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data) {
    }

}
