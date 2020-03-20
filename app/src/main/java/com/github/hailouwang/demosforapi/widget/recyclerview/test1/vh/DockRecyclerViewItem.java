package com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.main.data.DemosLibData;
import com.github.hailouwang.demosforapi.main.ui.DemosForApiAdapter;

import java.util.List;
import java.util.Map;

public class DockRecyclerViewItem extends BaseRecyclerViewAdapterVh1 {

    private ViewGroup container;

    private RecyclerView recyclerView;
    private DemosForApiAdapter demosForApiAdapter;

    public DockRecyclerViewItem(@NonNull Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.dock_test1_vh, null));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView = itemView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<Map<String, Object>> itemDatas = DemosLibData.getData(context, "");
        itemDatas.addAll(DemosLibData.getData(context, ""));
        itemDatas.addAll(DemosLibData.getData(context, ""));
        itemDatas.addAll(DemosLibData.getData(context, ""));

        demosForApiAdapter = new DemosForApiAdapter(itemDatas);
        recyclerView.setAdapter(demosForApiAdapter);
    }

    @Override
    public void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data) {
    }

}
