package com.github.hailouwang.flutter_boost_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class NativePageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mOpenNative;
    private TextView mOpenFlutter;
    private TextView mOpenFlutterFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flutter_native_page);

        mOpenNative = findViewById(R.id.open_native);
        mOpenFlutter = findViewById(R.id.open_flutter);
        mOpenFlutterFragment = findViewById(R.id.open_flutter_fragment);

        mOpenNative.setOnClickListener(this);
        mOpenFlutter.setOnClickListener(this);
        mOpenFlutterFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Map params = new HashMap();
        params.put("test1","v_test1");
        params.put("test2","v_test2");

        if (v == mOpenNative) {
            FlutterPageRouter.openPageByUrl(this, FlutterPageRouter.NATIVE_PAGE_URL,params);
        } else if (v == mOpenFlutter) {
            FlutterPageRouter.openPageByUrl(this, FlutterPageRouter.FLUTTER_PAGE_URL,params);
        } else if (v == mOpenFlutterFragment) {
            FlutterPageRouter.openPageByUrl(this, FlutterPageRouter.FLUTTER_FRAGMENT_PAGE_URL,params);
        }
    }
}