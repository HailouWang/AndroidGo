package com.github.hailouwang.demosforapi.drawable;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * https://juejin.im/entry/58d2355aa22b9d006446373e
 * https://developer.android.com/reference/android/graphics/drawable/RippleDrawable.html
 *
 *
 官方已经有两个已经实现的效果供我们选择：
 ?android:attr/selectableItemBackground和
 ?android:attr/selectableItemBackgroundBorderless
 第一个的波纹效果会被限制在View的大小之内，而第二条属性的波纹扩散范围是圆形的，
 而且圆的大小是以View最远距离点距离View中心点距离为半径，就是说：波纹的扩散范围保证会覆盖整个View，
 而且会扩散出View。官方提供的解决方法有一点不好就是无法定制，我们不能很方便的自定义波纹效果的颜色以及大小，

 */
public class RippleDrawableTest extends AppCompatActivity {

    boolean isTansition01 = false;
    boolean isTansition02 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_drawable_layout);

        TextView textView01 = (TextView) findViewById(R.id.tv01);
        textView01.setText("点击Button，查看效果~");
        Button btn01 = (Button) findViewById(R.id.btn01);
        btn01.setBackgroundResource(R.drawable.ripple_drawable);

        Button btn02 = (Button) findViewById(R.id.btn02);
        btn02.setBackgroundResource(R.drawable.ripple_item_drawable);

        Button btn03 = (Button) findViewById(R.id.btn03);
        btn03.setBackgroundResource(R.drawable.ripple_item2_drawable);

    }
}
