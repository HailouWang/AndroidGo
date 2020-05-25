package com.dueeeke.dkplayer.activity.extend;

import android.view.View;

import com.dueeeke.dkplayer.R;
import com.dueeeke.dkplayer.activity.BaseActivity;
import com.dueeeke.dkplayer.widget.videoview.ExoVideoView;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.exo.ExoMediaPlayer;
import com.dueeeke.videoplayer.exo.ExoMediaSourceHelper;
import com.dueeeke.videoplayer.player.AbstractPlayer;
import com.google.android.exoplayer2.source.ClippingMediaSource;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;

/**
 * 自定义MediaPlayer，有多种情形：
 * 第一：继承某个现成的MediaPlayer，对其功能进行扩展，此demo就演示了通过继承{@link ExoMediaPlayer}
 * 对其功能进行扩展。
 * 第二：通过继承{@link AbstractPlayer}扩展一些其他的播放器。
 */
public class CustomExoPlayerActivity extends BaseActivity<ExoVideoView> {

    private ExoMediaSourceHelper mHelper;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_custom_exo_player_dueeeke;
    }

    @Override
    protected void initView() {
        super.initView();
        mVideoView = findViewById(R.id.vv);
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent("custom exo", false);
        mVideoView.setVideoController(controller);
        mHelper = ExoMediaSourceHelper.getInstance(this);
    }

    public void onButtonClick(View view) {
        mVideoView.release();
        mVideoView.setCacheEnabled(false);
        int id = view.getId();
        if (id == R.id.cache) {
            mVideoView.setCacheEnabled(true);
            mVideoView.setUrl("http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8");
        } else if (id == R.id.concat) {//将多个视频拼接在一起播放
            ConcatenatingMediaSource concatenatingMediaSource = new ConcatenatingMediaSource();
            MediaSource mediaSource1 = mHelper.getMediaSource("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4");
            MediaSource mediaSource2 = mHelper.getMediaSource("http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4");
            MediaSource mediaSource3 = mHelper.getMediaSource("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4");
            concatenatingMediaSource.addMediaSource(mediaSource1);
            concatenatingMediaSource.addMediaSource(mediaSource2);
            concatenatingMediaSource.addMediaSource(mediaSource3);
            mVideoView.setMediaSource(concatenatingMediaSource);
        } else if (id == R.id.clip) {
            MediaSource mediaSource = mHelper.getMediaSource("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4");
            //裁剪10-15秒的内容进行播放
            ClippingMediaSource clippingMediaSource = new ClippingMediaSource(mediaSource, 10_000_000, 15_000_000);
            LoopingMediaSource loopingMediaSource = new LoopingMediaSource(clippingMediaSource);
            mVideoView.setMediaSource(loopingMediaSource);
        }

        mVideoView.start();
    }
}
