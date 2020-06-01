package com.github.hailouwang.demosforapi.shortvideo.demo1;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dueeeke.dkplayer.util.Utils;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.exo.ExoMediaPlayerFactory;
import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.shortvideo.BaseShortVideoFragment;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;

import java.io.IOException;

import butterknife.ButterKnife;

/**
 * 通过 RAW 或者 Assets 来播放本地视频
 */
public class ShortVideoRawOrAssetsFragment extends BaseShortVideoFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_play_raw_assets_dueeeke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(view);

        view.findViewById(R.id.btn_raw).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.btn_assets).setOnClickListener(this::onButtonClick);

        mVideoView = view.findViewById(R.id.player);
        StandardVideoController controller = new StandardVideoController(getContext());
        controller.addDefaultControlComponent(getString(R.string.str_raw_or_assets), false);
        mVideoView.setVideoController(controller);
    }

    private int i = 0;

    public void onButtonClick(View view) {
        mVideoView.release();
        Object playerFactory = Utils.getCurrentPlayerFactory();

        int id = view.getId();
        if (id == R.id.btn_raw) {
            if (playerFactory instanceof ExoMediaPlayerFactory) { //ExoPlayer
                DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.movie));
                RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(getContext());
                try {
                    rawResourceDataSource.open(dataSpec);
                } catch (RawResourceDataSource.RawResourceDataSourceException e) {
                    e.printStackTrace();
                }
                String url = rawResourceDataSource.getUri().toString();
                mVideoView.setUrl(url);
            } else { //MediaPlayer,IjkPlayer
                String url = "android.resource://" + getContext().getPackageName() + "/" + R.raw.movie;
                mVideoView.setUrl(url);
            }
        } else if (id == R.id.btn_assets) {
            if (playerFactory instanceof ExoMediaPlayerFactory) { //ExoPlayer
                mVideoView.setUrl("file:///android_asset/" + "test.mp4");
            } else { //MediaPlayer,IjkPlayer
                AssetManager am = getResources().getAssets();
                AssetFileDescriptor afd = null;
                try {
                    afd = am.openFd("test.mp4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mVideoView.setAssetFileDescriptor(afd);
            }
        }

        mVideoView.start();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            return super.onBackPressed();
        }
        return true;
    }
}
