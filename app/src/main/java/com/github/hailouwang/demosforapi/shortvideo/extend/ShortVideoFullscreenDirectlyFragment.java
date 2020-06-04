package com.github.hailouwang.demosforapi.shortvideo.extend;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dueeeke.dkplayer.util.DataUtil;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.github.hailouwang.demosforapi.shortvideo.BaseShortVideoFragment;

public class ShortVideoFullscreenDirectlyFragment extends BaseShortVideoFragment {
    private StandardVideoController mController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mVideoView = new VideoView(getContext());
        adaptCutoutAboveAndroidP();
        return mVideoView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        mVideoView.startFullScreen();
        mVideoView.setUrl(DataUtil.SAMPLE_URL);
        mController = new StandardVideoController(getContext());
        mController.addControlComponent(new CompleteView(getContext()));
        mController.addControlComponent(new ErrorView(getContext()));
        mController.addControlComponent(new PrepareView(getContext()));

        TitleView titleView = new TitleView(getContext());
        // 我这里改变了返回按钮的逻辑，我不推荐这样做，我这样只是为了方便，
        // 如果你想对某个组件进行定制，直接将该组件的代码复制一份，改成你想要的样子
        titleView.findViewById(com.dueeeke.dkplayer.R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        titleView.setTitle(getString(com.dueeeke.dkplayer.R.string.str_fullscreen_directly));
        mController.addControlComponent(titleView);
        VodControlView vodControlView = new VodControlView(getContext());
        // 我这里隐藏了全屏按钮并且调整了边距，我不推荐这样做，我这样只是为了方便，
        // 如果你想对某个组件进行定制，直接将该组件的代码复制一份，改成你想要的样子
        vodControlView.findViewById(com.dueeeke.dkplayer.R.id.fullscreen).setVisibility(View.GONE);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) vodControlView.findViewById(com.dueeeke.dkplayer.R.id.total_time).getLayoutParams();
        lp.rightMargin = PlayerUtils.dp2px(getContext(), 16);
        mController.addControlComponent(vodControlView);
        mController.addControlComponent(new GestureView(getContext()));
        mVideoView.setVideoController(mController);
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_16_9);
        mVideoView.start();
    }

    private void adaptCutoutAboveAndroidP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getActivity().getWindow().setAttributes(lp);
        }
    }

    @Override
    public boolean onBackPressed() {
        if (mController != null && !mController.isLocked()) {
            getActivity().finish();
            return true;
        }

        return false;
    }
}
