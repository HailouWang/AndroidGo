package com.github.hailouwang.demosforapi.drawable;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * https://developer.android.com/guide/topics/graphics/vector-drawable-resources.html
 *
 VectoryDrawable 是在XML文件中定义为具有与其相关联的颜色信息一起的一组点，线和曲线的矢量图形

 使用矢量绘制的主要优点是图像可扩展性。它可以在不显示质量，
 这意味着相同的文件的大小调整为不同屏幕密度不损失图像质量的损失的情况下缩放。
 这将导致更小的APK文件，减少开发商的维护。您也可以通过使用多个XML文件，
 而不是多个图像的每个显示器的分辨率，使用矢量图像动画。
 *
 *
 VectorDrawable定义了一个静态绘制对象。类似于SVG格式中，
 每个矢量图形被定义为一个树层次结构，其是由path与group对象。
 每个path包含对象的轮廓的几何形状和 group包含转换细节。当它们出现在XML文件中的所有路径被描绘在相同的顺序。

 */
public class VectorDrawableTest extends AppCompatActivity {

    boolean isTansition01 = false;
    boolean isTansition02 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawable_layout);

        ImageView imageView01 = (ImageView) findViewById(R.id.imageView_01);
        imageView01.setImageResource(R.drawable.battery_charging);

        ImageView imageView02 = (ImageView) findViewById(R.id.imageView_02);
        imageView02.setImageResource(R.drawable.vector_drawable);
    }
}
