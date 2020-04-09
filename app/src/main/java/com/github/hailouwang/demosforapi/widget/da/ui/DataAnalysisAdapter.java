package com.github.hailouwang.demosforapi.widget.da.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.BaseRecyclerViewAdapterVh1;
import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.TextRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class DataAnalysisAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapterVh1> {
    private static final int VIEW_TYPE_STRING = 1;

    private List<String> datas = new ArrayList<>();

    public DataAnalysisAdapter(List<String> datas) {
        this.datas.addAll(datas);
    }

    @NonNull
    @Override
    public BaseRecyclerViewAdapterVh1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextRecyclerViewItem(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapterVh1 holder, int position) {
        String data = getItem(position);

        holder.onBindRecyclerViewHolder(holder, position, data);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_STRING;
    }

    @Override
    public int getItemCount() {
        int size = this.datas.size();
        return size;
    }

    public String getItem(int position) {
        return (position >= datas.size()) ? "" : datas.get(position);
    }

    public void setDatasAll(List<String> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return datas;
    }
}
