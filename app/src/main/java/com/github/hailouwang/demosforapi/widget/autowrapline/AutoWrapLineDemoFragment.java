package com.github.hailouwang.demosforapi.widget.autowrapline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

import java.util.ArrayList;
import java.util.List;

public class AutoWrapLineDemoFragment extends Fragment {

    private AutoLinefeedLayout autoLinefeedLayoutDemo1;
    private AutoLinefeedLayout autoLinefeedLayoutDemo2;

    private List<String> tempList = new ArrayList<>();

    {
        tempList.add("小红在跳舞");
        tempList.add("小率");
        tempList.add("521");
        tempList.add("798");
        tempList.add("abc");
        tempList.add("xyz");
        tempList.add("上海广场");
        tempList.add("吃炸鸡");
        tempList.add("*&……%￥#@#￥%……&");
        tempList.add("大壳子");
        tempList.add("小可爱");
        tempList.add("小领带");
        tempList.add("美貌动人");
        tempList.add("可爱伊娃");
        tempList.add("流标");
        tempList.add("~~");
        tempList.add("*&……%￥#@#￥%……&");
        tempList.add("ooo");
        tempList.add("lll");
        tempList.add("jj");
        tempList.add("rr");
        tempList.add("www");
        tempList.add("fff");
        tempList.add("ffd");
        tempList.add("*&……%￥#@#￥%……&");
        tempList.add("bbb");
        tempList.add("nnn");
        tempList.add("sss");
        tempList.add("xxx");
        tempList.add("zzz");
        tempList.add("iii");
        tempList.add("qqq");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auto_wrap_line_demo_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoLinefeedLayoutDemo1 = view.findViewById(R.id.autoLineDemo1);
        autoLinefeedLayoutDemo2 = view.findViewById(R.id.autoLineDemo2);

        for (String value : tempList) {
            TextView textView01 = new TextView(getContext());
            textView01.setText(value);
            textView01.setBackgroundResource(R.drawable.bg_default_card);
            autoLinefeedLayoutDemo1.addView(textView01);

            TextView textView02 = new TextView(getContext());
            textView02.setText(value);
            textView02.setBackgroundResource(R.drawable.bg_default_card);
            autoLinefeedLayoutDemo2.addView(textView02);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
