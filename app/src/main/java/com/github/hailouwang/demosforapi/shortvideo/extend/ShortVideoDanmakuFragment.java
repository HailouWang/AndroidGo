package com.github.hailouwang.demosforapi.shortvideo.extend;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dueeeke.dkplayer.util.DataUtil;
import com.dueeeke.dkplayer.widget.component.MyDanmakuView;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.shortvideo.BaseShortVideoFragment;

public class ShortVideoDanmakuFragment extends BaseShortVideoFragment {

    private MyDanmakuView mMyDanmakuView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_danmaku_player_dueeeke;
    }

    @Override
    protected void initView() {
        super.initView();
        mVideoView = findViewById(R.id.player);
        StandardVideoController controller = new StandardVideoController(getContext());
        controller.addDefaultControlComponent(getString(R.string.str_danmu), false);
        mMyDanmakuView = new MyDanmakuView(getContext());
        controller.addControlComponent(mMyDanmakuView);
        mVideoView.setVideoController(controller);
        mVideoView.setUrl(DataUtil.SAMPLE_URL);
        mVideoView.start();

        mVideoView.addOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == VideoView.STATE_PREPARED) {
                    simulateDanmu();
                } else if (playState == VideoView.STATE_PLAYBACK_COMPLETED) {
                    mHandler.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.showDanMu).setOnClickListener(this::showDanMu);
        view.findViewById(R.id.hideDanMu).setOnClickListener(this::showDanMu);
        view.findViewById(R.id.addDanmakuWithDrawable).setOnClickListener(this::showDanMu);
        view.findViewById(R.id.addDanmaku).setOnClickListener(this::showDanMu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void showDanMu(View view) {
        mMyDanmakuView.show();
    }

    public void hideDanMu(View view) {
        mMyDanmakuView.hide();
    }

    public void addDanmakuWithDrawable(View view) {
        mMyDanmakuView.addDanmakuWithDrawable();
    }

    public void addDanmaku(View view) {
        mMyDanmakuView.addDanmaku("这是一条文字弹幕~", true);
    }


    private Handler mHandler = new Handler();

    /**
     * 模拟弹幕
     */
    private void simulateDanmu() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mMyDanmakuView.addDanmaku("awsl", false);
                mHandler.postDelayed(this, 100);
            }
        });
    }
}
