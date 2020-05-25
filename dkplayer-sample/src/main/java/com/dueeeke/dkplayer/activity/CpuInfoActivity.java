package com.dueeeke.dkplayer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dueeeke.videoplayer.util.PlayerUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * CPU信息
 */
public class CpuInfoActivity extends BaseActivity {

    private TextView mCpuInfo;

    public static void start(Context context) {
        context.startActivity(new Intent(context, CpuInfoActivity.class));
    }

    @Override
    protected View getContentView() {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setPadding(PlayerUtils.dp2px(this, 20), 0, 0, 0);
        mCpuInfo = new TextView(this);
        mCpuInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mCpuInfo.setTextColor(Color.BLACK);
        scrollView.addView(mCpuInfo);
        return scrollView;
    }

    @Override
    protected void initView() {
        super.initView();
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");

        sb.append("===================\n");
        sb.append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
        sb.append("===================\n\n");

        sb.append("===== CPU =====\n\n");

        String str;
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }
            br.close();
        } catch (IOException e) {
            //ignore
        }

        sb.append("\n");

        sb.append("===== ABI =====\n\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String[] abis = Build.SUPPORTED_ABIS;
            for (int i = 0; i < abis.length; i++) {
                sb.append("CPU ABI").append(i).append(":").append(abis[i]).append("\n");
            }
        }

        sb.append("\n");
        sb.append("===== Codecs =====\n\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            int numCodecs = MediaCodecList.getCodecCount();
            for (int i = 0; i < numCodecs; i++) {
                MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
                String[] types = codecInfo.getSupportedTypes();
                for (String type : types) {
                    sb.append(type).append("\n");
                    sb.append(codecInfo.getName()).append("\n\n");
                }
            }
        }

        mCpuInfo.setText(sb.toString());
    }

}
