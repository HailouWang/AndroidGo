package com.dueeeke.dkplayer.fragment.main;

import android.content.Intent;
import android.view.View;

import com.dueeeke.dkplayer.R;
import com.dueeeke.dkplayer.activity.extend.DefinitionPlayerActivity;
import com.dueeeke.dkplayer.activity.extend.ADActivity;
import com.dueeeke.dkplayer.activity.extend.CacheActivity;
import com.dueeeke.dkplayer.activity.extend.CustomExoPlayerActivity;
import com.dueeeke.dkplayer.activity.extend.CustomIjkPlayerActivity;
import com.dueeeke.dkplayer.activity.extend.DanmakuActivity;
import com.dueeeke.dkplayer.activity.extend.FullScreenActivity;
import com.dueeeke.dkplayer.activity.extend.PadActivity;
import com.dueeeke.dkplayer.activity.extend.PlayListActivity;
import com.dueeeke.dkplayer.fragment.BaseFragment;

public class ExtensionFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int getLayoutResId() {
        return R.layout.dueeeke_fragment_extension;
    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.btn_fullscreen).setOnClickListener(this);
        findViewById(R.id.btn_danmu).setOnClickListener(this);
        findViewById(R.id.btn_ad).setOnClickListener(this);
        findViewById(R.id.btn_proxy_cache).setOnClickListener(this);
        findViewById(R.id.btn_play_list).setOnClickListener(this);
        findViewById(R.id.btn_pad).setOnClickListener(this);
        findViewById(R.id.btn_custom_exo_player).setOnClickListener(this);
        findViewById(R.id.btn_custom_ijk_player).setOnClickListener(this);
        findViewById(R.id.btn_definition).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_fullscreen) {
            startActivity(new Intent(getActivity(), FullScreenActivity.class));
        } else if (id == R.id.btn_danmu) {
            startActivity(new Intent(getActivity(), DanmakuActivity.class));
        } else if (id == R.id.btn_ad) {
            startActivity(new Intent(getActivity(), ADActivity.class));
        } else if (id == R.id.btn_proxy_cache) {
            startActivity(new Intent(getActivity(), CacheActivity.class));
        } else if (id == R.id.btn_play_list) {
            startActivity(new Intent(getActivity(), PlayListActivity.class));
        } else if (id == R.id.btn_pad) {
            startActivity(new Intent(getActivity(), PadActivity.class));
        } else if (id == R.id.btn_custom_exo_player) {
            startActivity(new Intent(getActivity(), CustomExoPlayerActivity.class));
        } else if (id == R.id.btn_custom_ijk_player) {
            startActivity(new Intent(getActivity(), CustomIjkPlayerActivity.class));
        } else if (id == R.id.btn_definition) {
            startActivity(new Intent(getActivity(), DefinitionPlayerActivity.class));
        }
    }
}
