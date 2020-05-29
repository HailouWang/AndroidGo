package com.github.hailouwang.demosforapi.shortvideo;

import androidx.fragment.app.Fragment;

import com.dueeeke.dkplayer.widget.videoview.IjkVideoView;
import com.hailouwang.fragmentbackhandler.FragmentBackHandler;

public class BaseShortVideoFragment extends Fragment implements FragmentBackHandler {
    protected IjkVideoView mVideoView;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
