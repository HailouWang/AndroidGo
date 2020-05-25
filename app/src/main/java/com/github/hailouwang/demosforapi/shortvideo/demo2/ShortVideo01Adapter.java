package com.github.hailouwang.demosforapi.shortvideo.demo2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.shortvideo.common.ShortVideo;

import java.util.ArrayList;
import java.util.List;

public class ShortVideo01Adapter extends RecyclerView.Adapter<ShortVideo01ViewHolder> {
    private List<ShortVideo> shortVideos = new ArrayList<>();

    public ShortVideo01Adapter() {

    }

    public ShortVideo01Adapter(List<ShortVideo> shortVideos) {
        addDataAll(shortVideos);
    }

    @NonNull
    @Override
    public ShortVideo01ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShortVideo01ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return shortVideos.size();
    }

    public void addDataAll(List<ShortVideo> shortVideos) {
        this.shortVideos.clear();
        this.shortVideos.addAll(shortVideos);
    }
}
