package com.github.hailouwang.demosforapi.shortvideo.demo2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.shortvideo.common.ShortVideo;
import com.github.hailouwang.demosforapi.shortvideo.data.ShortVideoRepo;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ShortVideo01Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ShortVideo01Adapter shortVideo01Adapter;

    private Disposable disposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.short_video_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        shortVideo01Adapter = new ShortVideo01Adapter();

        disposable = ShortVideoRepo.getShortVideoData()
                .subscribe(new Consumer<List<ShortVideo>>() {
                    @Override
                    public void accept(List<ShortVideo> shortVideos) throws Exception {
                        shortVideo01Adapter.addDataAll(shortVideos);
                        shortVideo01Adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
