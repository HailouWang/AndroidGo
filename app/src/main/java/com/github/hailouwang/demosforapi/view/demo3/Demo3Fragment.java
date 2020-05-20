package com.github.hailouwang.demosforapi.view.demo3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 事件分发：https://www.cnblogs.com/xiaoQLu/archive/2013/04/02/2994030.html
 * <p>
 * 事件分发：https://www.cnblogs.com/SCAU_que/articles/2552871.html
 *
 * https://blog.csdn.net/mq2553299/article/details/105547607
 */
public class Demo3Fragment extends Fragment {
    Group1 group1;
    Group2 group2;
    MyTextView myTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getContext(), "adb logcat -v time -s hlwang", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //--group1
        //----|
        //-------group2
        //---------|
        //------------myTv

        group1 = new Group1(getContext());
        group1.setBackgroundColor(Color.RED);
        group2 = new Group2(getContext());
        myTv = new MyTextView(getContext());
        myTv.setBackgroundColor(Color.BLUE);
        group2.addView(myTv, new ViewGroup.LayoutParams(400,
                400));
        group2.setBackgroundColor(Color.GREEN);
        group1.addView(group2, new ViewGroup.LayoutParams(800,
                800));

        return group1;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
