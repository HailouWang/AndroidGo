package com.github.hailouwang.demosforapi.app.resources;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

public class DimenTest extends AppCompatActivity {

    final DisplayMetrics metrics = new DisplayMetrics();

    final int LENGTH = 13;

    private TextView mTv01,mTv02,mTv03,mTv04,mTv05,mTv06,mTv07,mTv08,mTv09,mTv10,mTv11,mTv12,mTv13
            ,mTv14,mTv15,mTv16;

    Object[][] titleValue;

    TextView[] views;

    Object[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimen_test);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(metrics);

        Resources mResources = getResources();
        Configuration configuration = mResources.getConfiguration();

        mTv01 = (TextView) findViewById(R.id.tv01);
        mTv02 = (TextView) findViewById(R.id.tv02);
        mTv03 = (TextView) findViewById(R.id.tv03);
        mTv04 = (TextView) findViewById(R.id.tv04);
        mTv05 = (TextView) findViewById(R.id.tv05);
        mTv06 = (TextView) findViewById(R.id.tv06);
        mTv07 = (TextView) findViewById(R.id.tv07);
        mTv08 = (TextView) findViewById(R.id.tv08);
        mTv09 = (TextView) findViewById(R.id.tv09);
        mTv10 = (TextView) findViewById(R.id.tv10);
        mTv11 = (TextView) findViewById(R.id.tv11);
        mTv12 = (TextView) findViewById(R.id.tv12);
        mTv13 = (TextView) findViewById(R.id.tv13);
        mTv14 = (TextView) findViewById(R.id.tv14);
        mTv15 = (TextView) findViewById(R.id.tv15);
        mTv16 = (TextView) findViewById(R.id.tv16);

        titleValue = new Object[][]{
                {"屏幕密度(density)：",metrics.density,},
                {"软件层面dots-per-inch(densityDpi)：",metrics.densityDpi,},
                {"硬件层面dots-per-inch(xdpi):",metrics.xdpi,},
                {"硬件层面dots-per-inch(ydpi)：",metrics.ydpi,},
                {"屏幕像素宽度：",metrics.widthPixels,},
                {"屏幕像素高度：",metrics.heightPixels,},
                {"字体缩放(scaledDensity)：",metrics.scaledDensity,},
                {"字体设置：",configuration.fontScale,},
                {"屏幕宽度(DP)：",configuration.screenWidthDp,},
                {"屏幕高度(DP)：",configuration.screenHeightDp,},
                {"160 PX(像素) = ",mResources.getDimension(R.dimen.test_dimen_px),},
                {"160 DP = ",mResources.getDimension(R.dimen.test_dimen_dp),},
                {"160 SP = ",mResources.getDimension(R.dimen.test_dimen_sp),},
                {"72 PT（point） = ",mResources.getDimension(R.dimen.test_dimen_pt),},
                {"1 IN（英寸） = ",mResources.getDimension(R.dimen.test_dimen_in),},
                {"1 MM（毫米） = ",mResources.getDimension(R.dimen.test_dimen_mm),},
        };

        views = new TextView[]{
                mTv01,
                mTv02,
                mTv03,
                mTv04,
                mTv05,
                mTv06,
                mTv07,
                mTv08,
                mTv09,
                mTv10,
                mTv11,
                mTv12,
                mTv13,
                mTv14,
                mTv15,
                mTv16,
        };

        for(int i=0;i<views.length;i++){
            TextView tv = views[i];
            if(tv == null) continue;
            tv.setText(titleValue[i][0].toString()+titleValue[i][1]);
        }
    }
}
