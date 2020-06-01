package com.github.hailouwang.demosforapi.shortvideo.demo1;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.dueeeke.dkplayer.util.DataUtil;
import com.dueeeke.dkplayer.util.IntentKeys;
import com.dueeeke.dkplayer.widget.component.DebugInfoView;
import com.dueeeke.dkplayer.widget.component.PlayerMonitor;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.LiveControlView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.util.L;
import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.shortvideo.BaseShortVideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 多播放器实例
 */
public class ShortVideoMultiItemFragment extends BaseShortVideoFragment {
    private static final String VOD_URL_1 = "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4";
    //    private static final String VOD_URL_2 = DataUtil.SAMPLE_URL;
    private static final String VOD_URL_2 = VOD_URL_1;

    private List<VideoView> mVideoViews = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_parallel_play_dueeeke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(view);

        VideoView player1 = view.findViewById(com.dueeeke.dkplayer.R.id.player_1);
        player1.setUrl(VOD_URL_1);

        //必须设置
        player1.setEnableAudioFocus(false);
        StandardVideoController controller1 = new StandardVideoController(getContext());
        controller1.addDefaultControlComponent(getString(com.dueeeke.dkplayer.R.string.str_multi_player), false);
        player1.setVideoController(controller1);
        mVideoViews.add(player1);

        VideoView player2 = view.findViewById(com.dueeeke.dkplayer.R.id.player_2);
        player2.setUrl(VOD_URL_2);
        //必须设置
        player2.setEnableAudioFocus(false);
        StandardVideoController controller2 = new StandardVideoController(getContext());
        controller2.addDefaultControlComponent(getString(com.dueeeke.dkplayer.R.string.str_multi_player), false);
        player2.setVideoController(controller2);
        mVideoViews.add(player2);
    }

    @Override
    public void onPause() {
        super.onPause();
        for (VideoView vv : mVideoViews) {
            vv.pause();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        for (VideoView vv : mVideoViews) {
            vv.resume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (VideoView vv : mVideoViews) {
            vv.release();
        }
    }

    @Override
    public boolean onBackPressed() {
        for (VideoView vv : mVideoViews) {
            if (mVideoView == null || !mVideoView.onBackPressed()) {
                return super.onBackPressed();
            }
        }
        return true;
    }
}
