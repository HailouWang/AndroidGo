package com.github.hailouwang.demosforapi.widget.recyclerview.test2;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.BaseRecyclerViewAdapterVh1;
import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.DockRecyclerViewItem;
import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.TextRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDockAdapter2 extends RecyclerView.Adapter<BaseRecyclerViewAdapterVh1> {

    private static final int VIEW_TYPE_STRING = 1;
    private static final int VIEW_TYPE_FOOTER = 2;

    private List<String> datas = new ArrayList<>();

    BaseRecyclerViewAdapterVh1 footer;

    public RecyclerViewDockAdapter2(List<String> datas) {
        this.datas.addAll(datas);
    }

    @NonNull
    @Override
    public BaseRecyclerViewAdapterVh1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FOOTER) {
            return footer;
        } else {
            return new TextRecyclerViewItem(parent);
        }
    }

    public void addFooterView(BaseRecyclerViewAdapterVh1 footer){
        this.footer = footer;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapterVh1 holder, int position) {
        String data = (position >= datas.size()) ? "" : datas.get(position);

        holder.onBindRecyclerViewHolder(holder, position, data);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            if (footer == null) {
                return VIEW_TYPE_STRING;
            } else {
                return VIEW_TYPE_FOOTER;
            }
        } else {
            return VIEW_TYPE_STRING;
        }
    }

    @Override
    public int getItemCount() {
        int size = this.datas.size();

        if (footer != null) {
            return size + 1;
        }

        return size;
    }

    public void setFooter(BaseRecyclerViewAdapterVh1 footer) {
        this.footer = footer;
    }
}
