package com.github.hailouwang.demosforapi.widget.recyclerview.test4;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewTest4Adapter extends RecyclerView.Adapter<RecyclerHolder> {
    private List<RecyclerViewHolderData> data = new ArrayList<>();
    private Activity activity;

    public RecyclerViewTest4Adapter(List<RecyclerViewHolderData> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_sticky, parent, false);
        //View itemView =  View.inflate(activity,R.layout.item_sticky,null);
        RecyclerHolder holder = new RecyclerHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.setData(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}