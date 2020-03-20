package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 注：颜色资源也可用作 XML 中的可绘制对象。

 例如，在创建状态列表可绘制对象时，
 可以引用 android:drawable 属性的颜色资源 (android:drawable="@color/green")。

 */
public class ColorDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawable_layout);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView_01);
        imageView1.setLayoutParams(new LinearLayout.LayoutParams(400,500));
        imageView1.setBackgroundResource(R.drawable.color_drawable);

        ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.light_green));
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView_02);
        imageView2.setLayoutParams(new LinearLayout.LayoutParams(400,500));
        imageView2.setBackground(colorDrawable);
    }
}
