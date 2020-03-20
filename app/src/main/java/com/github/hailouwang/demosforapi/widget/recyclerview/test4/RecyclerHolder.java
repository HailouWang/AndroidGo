package com.github.hailouwang.demosforapi.widget.recyclerview.test4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;


public class RecyclerHolder extends RecyclerView.ViewHolder {

    private final ImageView ivImage;
    private final TextView tvHead;

    public RecyclerHolder(View rootView) {
        super(rootView);
        tvHead = (TextView) rootView.findViewById(R.id.tv_head);
        ivImage = (ImageView) rootView.findViewById(R.id.iv_image);

    }

    public void setData(RecyclerViewHolderData data, int position) {
        tvHead.setText(data.text);
        ivImage.setImageResource(data.uri);
    }
}
