package com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapterVh1 extends RecyclerView.ViewHolder {
    public BaseRecyclerViewAdapterVh1(@NonNull View itemView) {
        super(itemView);
    }

    public BaseRecyclerViewAdapterVh1(ViewGroup parent, @LayoutRes int layoutRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public abstract void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data);
}
