package com.github.hailouwang.demosforapi.widget.da.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.widget.da.data.DataRepo;
import com.github.hailouwang.demosforapi.widget.da.monitor.RecyclerViewDataStatCallback;
import com.github.hailouwang.demosforapi.widget.da.monitor.RecyclerViewDataStatManager;
import com.github.hailouwang.demosforapi.widget.lifecycle.VisibleToUserChangeFragment;

import java.util.List;

public class DataAnalysisRecyclerViewFragment extends VisibleToUserChangeFragment implements RecyclerViewDataStatCallback, View.OnClickListener {
    private static final String TAG = "hlwang";

    private DataAnalysisAdapter dataAnalysisAdapter;

    private RecyclerView recyclerView;

    private RecyclerViewDataStatManager recyclerViewDataStatManager;

    private View swipeRefreshViewAll;
    private View swipeRefreshViewAdd;
    private View swipeRefreshViewRemove;

    private List<String> datas = DataRepo.getRecyclerViewData();

    @Override
    protected void onVisibleShowToUserChange(boolean isVisibleShowToUser, int stage) {
        super.onVisibleShowToUserChange(isVisibleShowToUser, stage);

        Log.d(TAG, "DataAnalysisRecyclerViewFragment onVisibleShowToUserChange isVisibleShowToUser : " + isVisibleShowToUser);

        if (recyclerViewDataStatManager != null) {
            recyclerViewDataStatManager.setVisibleShowToUser(isVisibleShowToUser);

            // 有可能，执行此步骤时，RecyclerView 上一条数据都没有
            if (isVisibleShowToUser) {
                recyclerViewDataStatManager.performStatRecyclerView(true);
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_analysis_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshViewAll = view.findViewById(R.id.swipeRefreshview_all);
        swipeRefreshViewAll.setOnClickListener(this);
        swipeRefreshViewAdd = view.findViewById(R.id.swipeRefreshview_add);
        swipeRefreshViewAdd.setOnClickListener(this);
        swipeRefreshViewRemove = view.findViewById(R.id.swipeRefreshview_remove);
        swipeRefreshViewRemove.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        dataAnalysisAdapter = new DataAnalysisAdapter(datas);

        recyclerViewDataStatManager = RecyclerViewDataStatManager.newMonitorRecyclerView(recyclerView, this, dataAnalysisAdapter);

        recyclerView.setAdapter(dataAnalysisAdapter);
    }

    @Override
    public void onRecyclerViewStatView(RecyclerView recyclerView, RecyclerView.Adapter adapter, View view, RecyclerView.ViewHolder viewHolder, int position, int viewType) {
        String content = (adapter instanceof DataAnalysisAdapter) ? ((DataAnalysisAdapter) adapter).getItem(position) : "";
        Log.d(TAG, "DataAnalysisRecyclerViewFragment onRecyclerViewStatView position : " + position
                + ", content : " + content
                + ", size : " + (adapter != null ? adapter.getItemCount() : 0));
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (R.id.swipeRefreshview_all == viewId) {
            dataAnalysisAdapter.setDatasAll(DataRepo.getRecyclerViewDataWithPrefix("全部更新数据 - "));
        } else if (R.id.swipeRefreshview_add == viewId) {
            dataAnalysisAdapter.getDatas().add(10, "局部刷新数据：10");
            dataAnalysisAdapter.notifyItemInserted(10);
        } else if (R.id.swipeRefreshview_remove == viewId) {
            dataAnalysisAdapter.getDatas().remove(4);
            dataAnalysisAdapter.notifyItemRemoved(4);
            Toast.makeText(getContext(), "数据列表：4 元素已被移除", Toast.LENGTH_SHORT).show();
        }
    }
}
