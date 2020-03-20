package com.github.hailouwang.demosforapi.drawable;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 * 定义平铺模式。
 *
 * Constant	Value	Description
 * disabled	-1	图像不平铺，默认值
 * clamp	0	复制边缘色彩
 * repeat	1	图像平铺
 * mirror	2	在水平和垂直方向上使用交替镜像的方式重复图片的绘制
 */
public class TileMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_mode);
    }
}
