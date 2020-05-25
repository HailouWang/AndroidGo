package com.dueeeke.dkplayer.fragment.main;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.dueeeke.dkplayer.R;
import com.dueeeke.dkplayer.activity.pip.AndroidOPiPActivity;
import com.dueeeke.dkplayer.activity.pip.PIPActivity;
import com.dueeeke.dkplayer.activity.pip.PIPListActivity;
import com.dueeeke.dkplayer.activity.pip.TinyScreenActivity;
import com.dueeeke.dkplayer.fragment.BaseFragment;

public class PipFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutResId() {
        return R.layout.dueeeke_fragment_pip;
    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.btn_pip).setOnClickListener(this);
        findViewById(R.id.btn_pip_in_list).setOnClickListener(this);
        findViewById(R.id.btn_pip_android_o).setOnClickListener(this);
        findViewById(R.id.btn_tiny_screen).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_pip) {
            startActivity(new Intent(getActivity(), PIPActivity.class));
        } else if (id == R.id.btn_pip_in_list) {
            startActivity(new Intent(getActivity(), PIPListActivity.class));
        } else if (id == R.id.btn_pip_android_o) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startActivity(new Intent(getActivity(), AndroidOPiPActivity.class));
            } else {
                Toast.makeText(getActivity(), "Android O required.", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btn_tiny_screen) {
            startActivity(new Intent(getActivity(), TinyScreenActivity.class));
        }
    }
}
