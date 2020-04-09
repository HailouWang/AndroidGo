package com.github.hailouwang.demosforapi.widget.da.ui.vh;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.BaseRecyclerViewAdapterVh1;

public class DataAnalysisViewHolder extends BaseRecyclerViewAdapterVh1 {
    public DataAnalysisViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public DataAnalysisViewHolder(ViewGroup parent, int layoutRes) {
        super(parent, layoutRes);
    }

    @Override
    public void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data) {

    }
}
