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

import butterknife.ButterKnife;

/**
 * 直播
 */
public class ShortVideoZhiboFragment extends BaseShortVideoFragment {
    private static final String THUMB = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg";
    private static final String LIVE_URL = "http://220.161.87.62:8800/hls/0/index.m3u8";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_player_dueeeke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(view);

        view.findViewById(R.id.scale_default).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.scale_169).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.scale_43).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.scale_original).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.scale_match_parent).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.scale_center_crop).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.speed_0_5).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.speed_0_75).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.speed_1_0).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.speed_1_5).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.speed_2_0).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.screen_shot).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.mirror_rotate).setOnClickListener(this::onButtonClick);
        view.findViewById(R.id.btn_mute).setOnClickListener(this::onButtonClick);

        mVideoView = view.findViewById(com.dueeeke.dkplayer.R.id.player);

        Bundle intent = getArguments();
        if (!intent.containsKey(IntentKeys.URL)) {
            intent.putBoolean(IntentKeys.IS_LIVE, true);
            intent.putString(IntentKeys.TITLE, "ijkPlayer - 直播");
            intent.putString(IntentKeys.URL, LIVE_URL);
        }

        if (intent != null) {
            StandardVideoController controller = new StandardVideoController(getContext());
            //根据屏幕方向自动进入/退出全屏
            controller.setEnableOrientation(true);

            PrepareView prepareView = new PrepareView(getContext());//准备播放界面
            ImageView thumb = prepareView.findViewById(com.dueeeke.dkplayer.R.id.thumb);//封面图
            Glide.with(this).load(THUMB).into(thumb);
            controller.addControlComponent(prepareView);

            controller.addControlComponent(new CompleteView(getContext()));//自动完成播放界面

            controller.addControlComponent(new ErrorView(getContext()));//错误界面

            TitleView titleView = new TitleView(getContext());//标题栏
            controller.addControlComponent(titleView);

            //根据是否为直播设置不同的底部控制条
            boolean isLive = intent.getBoolean(IntentKeys.IS_LIVE, false);
            if (isLive) {
                controller.addControlComponent(new LiveControlView(getContext()));//直播控制条
            } else {
                VodControlView vodControlView = new VodControlView(getContext());//点播控制条
                //是否显示底部进度条。默认显示
//                vodControlView.showBottomProgress(false);
                controller.addControlComponent(vodControlView);
            }

            GestureView gestureControlView = new GestureView(getContext());//滑动控制视图
            controller.addControlComponent(gestureControlView);
            //根据是否为直播决定是否需要滑动调节进度
            controller.setCanChangePosition(!isLive);

            //设置标题
            String title = intent.getString(IntentKeys.TITLE);
            titleView.setTitle(title);

            //注意：以上组件如果你想单独定制，我推荐你把源码复制一份出来，然后改成你想要的样子。
            //改完之后再通过addControlComponent添加上去
            //你也可以通过addControlComponent添加一些你自己的组件，具体实现方式参考现有组件的实现。
            //这个组件不一定是View，请发挥你的想象力😃

            //如果你不需要单独配置各个组件，可以直接调用此方法快速添加以上组件
//            controller.addDefaultControlComponent(title, isLive);

            //竖屏也开启手势操作，默认关闭
//            controller.setEnableInNormal(true);
            //滑动调节亮度，音量，进度，默认开启
//            controller.setGestureEnabled(false);
            //适配刘海屏，默认开启
//            controller.setAdaptCutout(false);

            //在控制器上显示调试信息
            controller.addControlComponent(new DebugInfoView(getContext()));
            //在LogCat显示调试信息
            controller.addControlComponent(new PlayerMonitor());

            //如果你不想要UI，不要设置控制器即可
            mVideoView.setVideoController(controller);

            mVideoView.setUrl(intent.getString(IntentKeys.URL));

            //保存播放进度
//            mVideoView.setProgressManager(new ProgressManagerImpl());
            //播放状态监听
            mVideoView.addOnStateChangeListener(mOnStateChangeListener);

            //临时切换播放核心，如需全局请通过VideoConfig配置，详见MyApplication
            //使用IjkPlayer解码
//            mVideoView.setPlayerFactory(IjkPlayerFactory.create());
            //使用ExoPlayer解码
//            mVideoView.setPlayerFactory(ExoMediaPlayerFactory.create());
            //使用MediaPlayer解码
//            mVideoView.setPlayerFactory(AndroidMediaPlayerFactory.create());

            mVideoView.start();
        }

        //播放其他视频
        EditText etOtherVideo = view.findViewById(com.dueeeke.dkplayer.R.id.et_other_video);
        view.findViewById(com.dueeeke.dkplayer.R.id.btn_start_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.release();
                mVideoView.setUrl(etOtherVideo.getText().toString());
                mVideoView.start();
            }
        });
    }

    private VideoView.OnStateChangeListener mOnStateChangeListener = new VideoView.SimpleOnStateChangeListener() {
        @Override
        public void onPlayerStateChanged(int playerState) {
            switch (playerState) {
                case VideoView.PLAYER_NORMAL://小屏
                    break;
                case VideoView.PLAYER_FULL_SCREEN://全屏
                    break;
            }
        }

        @Override
        public void onPlayStateChanged(int playState) {
            switch (playState) {
                case VideoView.STATE_IDLE:
                    break;
                case VideoView.STATE_PREPARING:
                    //在STATE_PREPARING时设置setMute(true)可实现静音播放
//                    mVideoView.setMute(true);
                    break;
                case VideoView.STATE_PREPARED:
                    break;
                case VideoView.STATE_PLAYING:
                    //需在此时获取视频宽高
                    int[] videoSize = mVideoView.getVideoSize();
                    L.d("视频宽：" + videoSize[0]);
                    L.d("视频高：" + videoSize[1]);
                    break;
                case VideoView.STATE_PAUSED:
                    break;
                case VideoView.STATE_BUFFERING:
                    break;
                case VideoView.STATE_BUFFERED:
                    break;
                case VideoView.STATE_PLAYBACK_COMPLETED:
                    break;
                case VideoView.STATE_ERROR:
                    break;
            }
        }
    };

    private int i = 0;

    public void onButtonClick(View view) {
        int id = view.getId();
        if (id == R.id.scale_default) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);
        } else if (id == R.id.scale_169) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_16_9);
        } else if (id == R.id.scale_43) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_4_3);
        } else if (id == R.id.scale_original) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL);
        } else if (id == R.id.scale_match_parent) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);
        } else if (id == R.id.scale_center_crop) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        } else if (id == R.id.speed_0_5) {
            mVideoView.setSpeed(0.5f);
        } else if (id == R.id.speed_0_75) {
            mVideoView.setSpeed(0.75f);
        } else if (id == R.id.speed_1_0) {
            mVideoView.setSpeed(1.0f);
        } else if (id == R.id.speed_1_5) {
            mVideoView.setSpeed(1.5f);
        } else if (id == R.id.speed_2_0) {
            mVideoView.setSpeed(2.0f);
        } else if (id == R.id.screen_shot) {
            ImageView imageView = view.findViewById(R.id.iv_screen_shot);
            Bitmap bitmap = mVideoView.doScreenShot();
            imageView.setImageBitmap(bitmap);
        } else if (id == R.id.mirror_rotate) {
            mVideoView.setMirrorRotation(i % 2 == 0);
            i++;
        } else if (id == R.id.btn_mute) {
            mVideoView.setMute(true);
        }
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
