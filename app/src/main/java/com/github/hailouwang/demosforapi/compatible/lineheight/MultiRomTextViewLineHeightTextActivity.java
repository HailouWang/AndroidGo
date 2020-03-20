package com.github.hailouwang.demosforapi.compatible.lineheight;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 * 链接：https://mp.weixin.qq.com/s?__biz=MzUxMzk2ODI1NQ==&mid=2247483964&idx=1&sn=fc5c4cd18e3564a237dff075bc055a3f&scene=21#wechat_redirect
 *
 * 百度技术:一种简单优雅的TextView行间距适配方案
 *
 * TextView的自带行间距是由于绘制的汉字没有占满descent和ascent的空间引起的，且该行间距在不同的字号以及分辨率下表现不一。
 * 若能够去除掉这部分行间距，就能达到适配目的。怎么去除呢？
 *
 * 我们再看一下系统TextView和视觉对一行文字行高的定义：
 *
 * TextView：行高 = descent - ascent (descent值为正，ascent值为负)
 *
 * 视觉：行高 = 字体大小 (比如16dp的文字，行高=48px)
 */
public class MultiRomTextViewLineHeightTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.multi_rom_line_height_test);
    }
}
