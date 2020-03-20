package com.github.hailouwang.demosforapi.drawable;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * GradientDrawable的作用在于定于各种样式的渐变。
 *
 *解析成：LevelListDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <shape
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:shape=["rectangle" | "oval" | "line" | "ring"] >
 <corners
 android:radius="integer"
 android:topLeftRadius="integer"
 android:topRightRadius="integer"
 android:bottomLeftRadius="integer"
 android:bottomRightRadius="integer" />
 <gradient
 android:angle="integer"
 android:centerX="float"
 android:centerY="float"
 android:centerColor="integer"
 android:endColor="color"
 android:gradientRadius="integer"
 android:startColor="color"
 android:type=["linear" | "radial" | "sweep"]
 android:useLevel=["true" | "false"] />
 <padding
 android:left="integer"
 android:top="integer"
 android:right="integer"
 android:bottom="integer" />
 <size
 android:width="integer"
 android:height="integer" />
 <solid
 android:color="color" />
 <stroke
 android:width="integer"
 android:color="color"
 android:dashWidth="integer"
 android:dashGap="integer" />
 </shape>


 * 说明：
 *
 *
 <shape>
 形状可绘制对象。这必须是根元素。
 属性：

 xmlns:android
 字符串。必备。定义 XML 命名空间，其必须是 "http://schemas.android.com/apk/res/android"。
 android:shape
 关键字。定义形状的类型。有效值为：
 值	描述
 "rectangle"	填充包含视图的矩形。这是默认形状。
 "oval"	适应包含视图尺寸的椭圆形状。
 "line"	跨越包含视图宽度的水平线。此形状需要 <stroke> 元素定义线宽。
 "ring"	环形。
 仅当 android:shape="ring" 如下时才使用以下属性：

 android:innerRadius
 尺寸。环内部（中间的孔）的半径，以尺寸值或尺寸资源表示。
 android:innerRadiusRatio
 浮点型。环内部的半径，以环宽度的比率表示。例如，如果 android:innerRadiusRatio="5"，则内半径等于环宽度除以 5。此值被 android:innerRadius 覆盖。默认值为 9。
 android:thickness
 尺寸。环的厚度，以尺寸值或尺寸资源表示。
 android:thicknessRatio
 浮点型。环的厚度，表示为环宽度的比率。例如，如果 android:thicknessRatio="2"，则厚度等于环宽度除以 2。此值被 android:innerRadius 覆盖。默认值为 3。
 android:useLevel
 布尔值。如果这用作 LevelListDrawable，则此值为“true”。这通常应为“false”，否则形状不会显示。
 <corners>
 为形状产生圆角。仅当形状为矩形时适用。
 属性：

 android:radius
 尺寸。所有角的半径，以尺寸值或尺寸资源表示。对于每个角，这会被以下属性覆盖。
 android:topLeftRadius
 尺寸。左上角的半径，以尺寸值或尺寸资源表示。
 android:topRightRadius
 尺寸。右上角的半径，以尺寸值或尺寸资源表示。
 android:bottomLeftRadius
 尺寸。左下角的半径，以尺寸值或尺寸资源表示。
 android:bottomRightRadius
 尺寸。右下角的半径，以尺寸值或尺寸资源表示。
 注：（最初）必须为每个角提供大于 1 的角半径，否则无法产生圆角。如果希望特定角不要倒圆角，解决方法是使用 android:radius 设置大于 1 的默认角半径，然后使用实际所需的值替换每个角，为不希望倒圆角的角提供零（“0dp”）。

 <gradient>
 指定形状的渐变颜色。
 属性：

 android:angle
 整型。渐变的角度（度）。0 为从左到右，90 为从上到上。必须是 45 的倍数。默认值为 0。
 android:centerX
 浮点型。渐变中心的相对 X 轴位置 (0 - 1.0)。
 android:centerY
 浮点型。渐变中心的相对 Y 轴位置 (0 - 1.0)。
 android:centerColor
 颜色。起始颜色与结束颜色之间的可选颜色，以十六进制值或颜色资源表示。
 android:endColor
 颜色。结束颜色，表示为十六进制值或颜色资源。
 android:gradientRadius
 浮点型。渐变的半径。仅在 android:type="radial" 时适用。
 android:startColor
 颜色。起始颜色，表示为十六进制值或颜色资源。
 android:type
 关键字。要应用的渐变图案的类型。有效值为：
 值	说明
 "linear"	线性渐变。这是默认值。
 "radial"	径向渐变。起始颜色为中心颜色。
 "sweep"	流线型渐变。
 android:useLevel
 布尔值。如果这用作 LevelListDrawable，则此值为“true”。
 <padding>
 要应用到包含视图元素的内边距（这会填充视图内容的位置，而非形状）。
 属性：

 android:left
 尺寸。左内边距，表示为尺寸值或尺寸资源
 android:top
 尺寸。上内边距，表示为尺寸值或尺寸资源
 android:right
 尺寸。右内边距，表示为尺寸值或尺寸资源
 android:bottom
 尺寸。下内边距，表示为尺寸值或尺寸资源
 <size>
 形状的大小。
 属性：

 android:height
 尺寸。形状的高度，表示为尺寸值或尺寸资源
 android:width
 尺寸。形状的宽度，表示为尺寸值或尺寸资源
 注：默认情况下，形状按照此处定义的尺寸按比例缩放至容器视图的大小。在 ImageView 中使用形状时，可通过将 android:scaleType 设置为 "center" 来限制缩放。

 <solid>
 用于填充形状的纯色。
 属性：

 android:color
 颜色。应用于形状的颜色，以十六进制值或颜色资源表示。
 <stroke>
 形状的笔划中线。
 属性：

 android:width
 尺寸。线宽，以尺寸值或尺寸资源表示。
 android:color
 颜色。线的颜色，表示为十六进制值或颜色资源。
 android:dashGap
 尺寸。短划线的间距，以尺寸值或尺寸资源表示。仅在设置了 android:dashWidth 时有效。
 android:dashWidth
 尺寸。每个短划线的大小，以尺寸值或尺寸资源表示。仅在设置了 android:dashGap 时有效。

 */
public class GradientBasicDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view_root_drawable);

        LinearLayout root = (LinearLayout) findViewById(R.id.temp_view_root);

        ImageViewAndTexView first = ImageViewAndTexView.newInstance(this);
        first.getmTextView().setText("solid&stroke&padding,left=10,top=10,right=10,bottom=10");
        first.getmTextView().setBackgroundResource(R.drawable.shape_1);
        first.getmImageView().setVisibility(View.GONE);
        first.addImageViewAndTextView(root);

        ImageViewAndTexView second = ImageViewAndTexView.newInstance(this);
        second.getmTextView().setText("solid&stroke&padding&corners 矩形");
        second.getmTextView().setBackgroundResource(R.drawable.shape_2);
        second.getmImageView().setVisibility(View.GONE);
        second.addImageViewAndTextView(root);

        ImageViewAndTexView third = ImageViewAndTexView.newInstance(this);
        third.getmTextView().setText("solid&stroke&padding&corners 椭圆");
        third.getmTextView().setBackgroundResource(R.drawable.shape_3);
        third.getmImageView().setVisibility(View.GONE);
        third.addImageViewAndTextView(root);

        ImageViewAndTexView four = ImageViewAndTexView.newInstance(this);
        four.getmTextView().setText("solid&stroke 一条线");
        four.getmTextView().setBackgroundResource(R.drawable.shape_4);
        four.getmImageView().setVisibility(View.GONE);
        four.addImageViewAndTextView(root);

        ImageViewAndTexView five = ImageViewAndTexView.newInstance(this);
        five.getmTextView().setText("solid&stroke&padding&corners&gradient 渐变");
        five.getmTextView().setBackgroundResource(R.drawable.shape_5);
        five.getmImageView().setVisibility(View.GONE);
        five.addImageViewAndTextView(root);

    }

    static class ImageViewAndTexView{
        private TextView mTextView;
        private ImageView mImageView;
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        private ImageViewAndTexView(Context context){
            mTextView = new TextView(context);
            mImageView = new ImageView(context);
            mTextView.setLayoutParams(parms);

            parms.weight = 1;
            mImageView.setLayoutParams(parms);
        }

        public static ImageViewAndTexView newInstance(Context context) {

            Bundle args = new Bundle();

            ImageViewAndTexView imageViewAndText = new ImageViewAndTexView(context);
            return imageViewAndText;
        }

        public TextView getmTextView() {
            return mTextView;
        }

        public ImageView getmImageView() {
            return mImageView;
        }

        public void addImageViewAndTextView(LinearLayout linearlayout){
            if(linearlayout != null){
                linearlayout.addView(mTextView);
                linearlayout.addView(mImageView);
            }
        }
    }
}
