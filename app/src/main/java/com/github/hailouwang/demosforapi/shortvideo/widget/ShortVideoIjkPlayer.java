package com.github.hailouwang.demosforapi.shortvideo.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dueeeke.dkplayer.widget.videoview.IjkVideoView;

public class ShortVideoIjkPlayer extends FrameLayout {

    private ImageView mCorverImage;
    private IjkVideoView mIjkVideoView;

    private boolean mIsRelease;
    private long mPreparedStartPosition = 0;
//    private ShortVideoControllerListener mControllerListener;
    private boolean mControllerAutoProgressEnable;
    private int mPlayCount;
    private boolean mPlayPositionHalefed;

    public ShortVideoIjkPlayer(@NonNull Context context) {
        super(context);
    }

    public ShortVideoIjkPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShortVideoIjkPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShortVideoIjkPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    /**
//     * 设置封图占位图
//     *
//     * @param resourceId
//     */
//    public void setVideoImagePlaceholderImageResId(int resourceId) {
//
//        getCoverImageView().setDefPlaceholderImage(resourceId);
//    }
//
//    /**
//     * 设置并显示视频封图
//     *
//     * @param coverImageUrl
//     */
//    public void setVideoImageUri(String coverImageUrl) {
//
//        showCoverImageView(coverImageUrl);
//        removeCallbacks(mHideCoverImageViewRunnable);
//    }
//
//    /**
//     * 加载并显示封图
//     *
//     * @param coverImageUrl
//     */
//    private void showCoverImageView(String coverImageUrl) {
//
//        getCoverImageView().setImageUriByLp(coverImageUrl);
//        ViewUtil.show(getCoverImageView());
//    }
//
//    /**
//     * 隐藏封图
//     */
//    private void hideCoverImageView() {
//
//        if (isCoverImageViewInit()) {
//            getCoverImageView().setImageUriByLp((String) null);
//            ViewUtil.hide(getCoverImageView());
//        }
//    }
//
//    /**
//     * 延迟隐藏视频封图
//     *
//     * @param delayMillis
//     */
//    private void delayHideCoverImageView(int delayMillis) {
//
//        if (delayMillis > 0) {
//            postDelayed(mHideCoverImageViewRunnable, delayMillis);
//        } else {
//            mHideCoverImageViewRunnable.run();
//        }
//    }
//
//    /**
//     * 隐藏视频封图runnable
//     */
//    private Runnable mHideCoverImageViewRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//            hideCoverImageView();
//        }
//    };
//
//    ///////////////////////////////////////////////////////////////////////////
//    // 播放器api
//    ///////////////////////////////////////////////////////////////////////////
//
//    /**
//     * 设置屏幕缩放模式
//     *
//     * @param scaleType
//     */
//    public void setScreenScaleType(int scaleType) {
//
//        getVideoView().setScreenScale(scaleType);
//    }
//
//    /**
//     * 设置gif模式市场
//     *
//     * @param durationMillis
//     */
//    public void setGifModeDuration(int durationMillis) {
//
//        mGifModeDuration = durationMillis;
//        setControllerAutoProgressEnable(true);
//    }
//
//    /**
//     * 设置播放中静音
//     * 只能在调用start之后才能调用
//     *
//     * @param mute
//     */
//    public void setStartedMute(boolean mute) {
//
//        getVideoView().setMute(mute);
//    }
//
//    /**
//     * 设置是否循环播放
//     * 播放前和播放中都可调用
//     *
//     * @param looping
//     */
//    public void setLooping(boolean looping) {
//
//        getVideoView().setLooping(looping);
//    }
//
//    /**
//     * 设置视频url,不会加载视频
//     *
//     * @param videoUrl
//     */
//    public void setVideoUri(String videoUrl) {
//
//        getVideoView().setUrl(videoUrl);
//    }
//
//    /**
//     * 设置是否开启环路滤波
//     *
//     * @param loopFilter
//     */
//    public void setOptionSkipLoopFilter(boolean loopFilter) {
//
//        getVideoView().setOptionSkipLoopFilter(loopFilter);
//    }
//
//    /**
//     * 设置视频重新加载续播点
//     * 视频准备前设置才有用
//     *
//     * @param position
//     */
//    public void setPreparedStartPosition(long position) {
//
//        mPreparedStartPosition = position;
//    }
//
//    /**
//     * 如果是idle状态，加载视频后开始播放
//     * 如果是暂停状态，则继续播放
//     */
//    public void start() {
//
//        if (TextUtils.isEmpty(getVideoView().getUrl())) {
//            onPlayStateChanged(IjkVideoView.STATE_ERROR);
//        } else {
//            getVideoView().start();
//            startControllerPgoressTask();
//            mIsRelease = false;
//        }
//    }
//
//    /**
//     * 是否是正在播放状态
//     *
//     * @return
//     */
//    public boolean isPlaying() {
//
//        return getVideoView().isPlaying();
//    }
//
//    /**
//     * 获取当前播放进度
//     *
//     * @return
//     */
//    public long getPlayPosition() {
//
//        return getVideoView().getCurrentPosition();
//    }
//
//    /**
//     * 获取播放总时长
//     *
//     * @return
//     */
//    public long getPlayTotalPosition() {
//
//        long totalPlayPosition = getPlayPosition();
//        if (mPlayCount > 0) {
//            totalPlayPosition += getDuration() * mPlayCount;
//        }
//        return totalPlayPosition;
//    }
//
//    /**
//     * 获取视频时长,单位毫秒
//     *
//     * @return
//     */
//    public long getDuration() {
//
//        return getVideoView().getDuration();
//    }
//
//    /**
//     * 暂停视频
//     *
//     * @return
//     */
//    public boolean pause() {
//
//        if (getVideoView().isPlaying()) {
//            getVideoView().pause();
//            stopControllerProgressTask();
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 是否是播放暂停状态
//     *
//     * @return
//     */
//    public boolean isPause() {
//
//        return getVideoView().getCurrentPlayState() == IjkVideoView.STATE_PAUSED;
//    }
//
//    /**
//     * seek操作
//     *
//     * @param position
//     */
//    public void seekTo(long position) {
//
//        mVideoView.seekTo(position);
//    }
//
//    /**
//     * 释放资源资源
//     * 1、隐藏封图
//     * 2、释放视频资源
//     * 3、停止进度任务
//     */
//    public void release() {
//
//        if (!mIsRelease) {
//            getVideoView().release();
//            stopControllerProgressTask();
//            mPlayCount = 0;
//            mPlayPositionHalefed = false;
//            mIsRelease = true;
//        }
//    }
//
//    /**
//     * 设置控制器监听
//     *
//     * @param listener
//     */
//    public void setControllerListener(ControllerListener listener) {
//
//        mControllerListener = listener;
//    }
//
//    /**
//     * 设置控制器自动计算进度
//     * 进度计算会在ControllerListener给予外部回调
//     *
//     * @param progressEnable
//     */
//    public void setControllerAutoProgressEnable(boolean progressEnable) {
//
//        mControllerAutoProgressEnable = progressEnable;
//    }
//
//    /**
//     * 播放状态变更
//     *
//     * @param playState
//     */
//    @Override
//    public void onPlayStateChanged(int playState) {
//        if (playState == IjkVideoView.STATE_PREPARING) {
//
//            if (mControllerListener != null) {
//                mControllerListener.onControllerLoadingStart();
//            }
//
//        } else if (playState == IjkVideoView.STATE_PREPARED) {
//
//            //为什么要delay：有些视频第一帧出来较慢，过早消失封图，有黑屏闪烁现象
//            delayHideCoverImageView(300);
//            stopControllerProgressTask();
//            if (mPreparedStartPosition > 0) {
//                getVideoView().seekTo(mPreparedStartPosition);
//            }
//            startControllerPgoressTask();
//
//            if (mControllerListener != null) {
//                mControllerListener.onControllerLoadingCompleted();
//            }
//
//        } else if (playState == IjkVideoView.STATE_BUFFERING) {
//
//            if (mControllerListener != null) {
//                mControllerListener.onControllerLoadingStart();
//            }
//
//        } else if (playState == IjkVideoView.STATE_BUFFERED) {
//
//            if (mControllerListener != null) {
//                mControllerListener.onControllerLoadingCompleted();
//            }
//
//        } else if (playState == IjkVideoView.STATE_PLAYBACK_COMPLETED) {
//
//            //nothing
//
//        } else if (playState == IjkVideoView.STATE_ERROR) {
//
//            release();//释放视频资源
//            if (mControllerListener != null) {
//                mControllerListener.onControllerError();
//            }
//        }
//    }
//
//    /**
//     * 是否全屏的回调？
//     *
//     * @param state
//     */
//    @Override
//    public void onPlayerStateChanged(int state) {
//
//        //nothing
//    }
//
//    ///////////////////////////////////////////////////////////////////////////
//    // 控制器进度条api
//    ///////////////////////////////////////////////////////////////////////////
//
//    /**
//     * 开始控制器进度任务
//     */
//    private void startControllerPgoressTask() {
//
//        if (mControllerAutoProgressEnable) {
//            post(mControllerProgressRunnable);
//        }
//    }
//
//    /**
//     * 停止控制器进度任务
//     */
//    private void stopControllerProgressTask() {
//
//        if (mControllerAutoProgressEnable) {
//            removeCallbacks(mControllerProgressRunnable);
//        }
//    }
//
//    /**
//     * 播放进度计算
//     */
//    private Runnable mControllerProgressRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//            long playPosition = getPlayPosition();
//            long duration = getVideoView().getDuration();
//            float progress = 0;
//            if (duration > 0) {
//                progress = (playPosition / (duration * 1.0f));
//            }
//
//            //播放过半标记和播放次数统计
//            if (progress >= 0.5f) {
//                mPlayPositionHalefed = true;
//            } else {
//
//                if (playPosition <= 1500) {
//                    if (mPlayPositionHalefed) {
//                        mPlayCount++;
//                        mPlayPositionHalefed = false;
//                    }
//                }
//            }
//
//            //播放进度回调
//            if (mControllerListener != null) {
//                mControllerListener.onControllerPrgressChanged(playPosition, progress);
//            }
//
//            //gifmode逻辑
//            if (mGifModeDuration > 0 && playPosition >= mGifModeDuration) {
//
//                if (mControllerListener != null) {
//                    mControllerListener.onGifModePlayLoopOnce();
//                }
//                seekTo(0);
//            }
//
//            //计算和发送下一次回调
//            if (playPosition <= 0) {
//                playPosition = 1000;
//            }
//            long delay = (1000 - (playPosition % 1000));//逢1秒给外部回调一次
//            postDelayed(this, delay);
//        }
//    };
//
//    public interface ShortVideoControllerListener{
//        /**
//         * 播放进度变更回调
//         *
//         * @param position
//         * @param percentProgress 百分比进度
//         */
//        void onControllerPrgressChanged(long position, float percentProgress);
//
//        /**
//         * 加载状态开始
//         */
//        void onControllerLoadingStart();
//
//        /**
//         * 加载状态结束
//         */
//        void onControllerLoadingCompleted();
//
//        /**
//         * gif模式播放一次完成
//         */
//        void onGifModePlayLoopOnce();
//
//        /**
//         * 视频加载或播放失败
//         */
//        void onControllerError();
//    }
//
//    /**
//     * 返回并初始化视频播放视图
//     * 播放视图永远放在最底层
//     *
//     * @return
//     */
//    private IjkVideoView getVideoView() {
//
//        if (mIjkVideoView == null) {
//            mIjkVideoView = new IjkVideoView(getContext());
//            mIjkVideoView.addOnVideoViewStateChangeListener(this);
//            mIjkVideoView.setPlayOnMobileNetwork(true);
//            addView(mIjkVideoView, 0, createChildViewLayoutParamsFromParent());//永远添加到最底层
//        }
//        return mIjkVideoView;
//    }
//
//    /**
//     * 封图组件是否初始化
//     *
//     * @return
//     */
//    private boolean isCoverImageViewInit() {
//
//        return mCorverImage != null;
//    }
//
//    /**
//     * 返回并初始化封图组件
//     *
//     * @return
//     */
//    private ImageView getCoverImageView() {
//
//        if (mCorverImage == null) {
//            mCorverImage = new ImageView(getContext());
//            mCorverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            addView(mCorverImage, createChildViewLayoutParamsFromParent());
//        }
//
//        return mCorverImage;
//    }
//
//    @Override
//    public void setLayoutParams(ViewGroup.LayoutParams params) {
//
//        super.setLayoutParams(params);
//        setChildViewLayoutParamsFromParent(mCorverImage);
//        setChildViewLayoutParamsFromParent(mVideoView);
//    }
//
//    /**
//     * 设置view同步父布局layoutparams
//     *
//     * @param childView
//     */
//    private void setChildViewLayoutParamsFromParent(View childView) {
//
//        if (childView != null) {
//            childView.setLayoutParams(createChildViewLayoutParamsFromParent());
//        }
//    }
//
//    /**
//     * 根据父布局创建子视图的layoutparams
//     *
//     * @return
//     */
//    private FrameLayout.LayoutParams createChildViewLayoutParamsFromParent() {
//
//        ViewGroup.LayoutParams parentLayoutParams = null;
//        if (getParent() != null) {
//            parentLayoutParams = ((ViewGroup) getParent()).getLayoutParams();
//        }
//
//        if (parentLayoutParams != null && parentLayoutParams.width >= 0 && parentLayoutParams.height >= 0) {
//            return new FrameLayout.LayoutParams(parentLayoutParams.width, parentLayoutParams.width);
//        } else {
//            return new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        }
//    }
//
//    ///////////////////////////////////////////////////////////////////////////
//    // 其他api
//    ///////////////////////////////////////////////////////////////////////////
//
//    /**
//     * 状态toString
//     *
//     * @param playState
//     * @return
//     */
//    private static String playState2String(int playState) {
//
//        switch (playState) {
//            case IjkVideoView.STATE_ERROR:
//                return "播放错误";
//            case IjkVideoView.STATE_IDLE:
//                return "闲置状态";
//            case IjkVideoView.STATE_PREPARING:
//                return "准备中";
//            case IjkVideoView.STATE_PREPARED:
//                return "视频准备完成";
//            case IjkVideoView.STATE_PLAYING:
//                return "视频播放中";
//            case IjkVideoView.STATE_PAUSED:
//                return "视频暂停";
//            case IjkVideoView.STATE_PLAYBACK_COMPLETED:
//                return "视频播放完成";
//            case IjkVideoView.STATE_BUFFERING:
//                return "视频正在缓冲";
//            case IjkVideoView.STATE_BUFFERED:
//                return "视频缓冲完成";
//            default:
//                return "未知";
//        }
//    }
//
//    private String simpleTag() {
//        return "hlwang";
//    }
}
