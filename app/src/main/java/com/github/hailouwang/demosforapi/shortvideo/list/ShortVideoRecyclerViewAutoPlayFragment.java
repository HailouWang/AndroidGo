package com.github.hailouwang.demosforapi.shortvideo.list;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.dkplayer.activity.MainActivity;
import com.dueeeke.dkplayer.adapter.VideoRecyclerViewAdapter;
import com.dueeeke.dkplayer.adapter.listener.OnItemChildClickListener;
import com.dueeeke.dkplayer.bean.VideoBean;
import com.dueeeke.dkplayer.util.DataUtil;
import com.dueeeke.dkplayer.util.ProgressManagerImpl;
import com.dueeeke.dkplayer.util.Tag;
import com.dueeeke.dkplayer.util.Utils;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.util.L;
import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.shortvideo.BaseShortVideoFragment;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * 视频播放列表
 * - 自动播放，利用：getLocalVisibleRect 来计算展示区域
 */
public class ShortVideoRecyclerViewAutoPlayFragment extends ShortVideoRecyclerViewFragment {
    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) { //滚动停止
                    autoPlayVideo(recyclerView);
                }
            }

            private void autoPlayVideo(RecyclerView view) {
                if (view == null) return;
                //遍历RecyclerView子控件,如果mPlayerContainer完全可见就开始播放
                int count = view.getChildCount();
                L.d("ChildCount:" + count);
                for (int i = 0; i < count; i++) {
                    View itemView = view.getChildAt(i);
                    if (itemView == null) continue;
                    VideoRecyclerViewAdapter.VideoHolder holder = (VideoRecyclerViewAdapter.VideoHolder) itemView.getTag();
                    Rect rect = new Rect();
                    holder.mPlayerContainer.getLocalVisibleRect(rect);
                    int height = holder.mPlayerContainer.getHeight();
                    if (rect.top == 0 && rect.bottom == height) {
                        startPlay(holder.mPosition);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        mRecyclerView.post(() -> {
            //自动播放第一个
            startPlay(0);
        });
    }
}
