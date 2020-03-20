package com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.hailouwang.demosforapi.R;

public class TextRecyclerViewItem extends BaseRecyclerViewAdapterVh1 {

    private TextView text1;

    public TextRecyclerViewItem(@NonNull View itemView) {
        super(itemView);
    }

    public TextRecyclerViewItem(ViewGroup parent) {
        super(parent, R.layout.demos_for_api_item);

        text1 = itemView.findViewById(android.R.id.text1);
    }

    @Override
    public void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data) {
        text1.setText(data);
    }

}
