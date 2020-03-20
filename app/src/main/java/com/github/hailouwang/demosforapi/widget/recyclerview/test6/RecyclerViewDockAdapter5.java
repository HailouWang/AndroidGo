package com.github.hailouwang.demosforapi.widget.recyclerview.test6;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.widget.recyclerview.test6.vh.BaseRecyclerViewAdapterVh1;
import com.github.hailouwang.demosforapi.widget.recyclerview.test6.vh.DockRecyclerViewItem;
import com.github.hailouwang.demosforapi.widget.recyclerview.test6.vh.TextRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDockAdapter5 extends RecyclerView.Adapter<BaseRecyclerViewAdapterVh1> {

    public static final int VIEW_TYPE_STRING_SINBLE = 1;
    public static final int VIEW_TYPE_STRING_DOUBLE = 2;
    public static final int VIEW_TYPE_DOCKER = 3;

    private List<String> datas = new ArrayList<>();

    public RecyclerViewDockAdapter5(List<String> datas) {
        this.datas.addAll(datas);
    }

    @NonNull
    @Override
    public BaseRecyclerViewAdapterVh1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DOCKER) {
            return new DockRecyclerViewItem(parent.getContext());
        } else {
            return new TextRecyclerViewItem(parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapterVh1 holder, int position) {
        String data = (position >= datas.size()) ? "" : datas.get(position);

        holder.onBindRecyclerViewHolder(holder, position, data);
    }

    @Override
    public int getItemViewType(int position) {
        String data = datas.get(position);
        if ("dock".equals(data)) {
            return VIEW_TYPE_DOCKER;
        } else {
            if (data.startsWith("single")) {
                return VIEW_TYPE_STRING_SINBLE;
            } else {
                return VIEW_TYPE_STRING_DOUBLE;
            }
        }
    }

    @Override
    public int getItemCount() {
        int size = this.datas.size();
        return size;
    }

    public static class SpanSize extends GridLayoutManager.SpanSizeLookup {
        RecyclerViewDockAdapter5 adapter5;

        public SpanSize(RecyclerViewDockAdapter5 adapter5) {
            this.adapter5 = adapter5;
        }

        @Override
        public int getSpanSize(int position) {
            if (adapter5.getItemViewType(position) == VIEW_TYPE_DOCKER || adapter5.getItemViewType(position) == VIEW_TYPE_STRING_SINBLE) {
                return 2;
            } else {
                return 1;
            }
        }
    }
}
