package com.github.hailouwang.demosforapi.flutter.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.hailouwang.demosforapi.flutter.view.PlatformNativeViewFactory;

import io.flutter.app.FlutterActivity;

public class FlutterCustomActivity extends FlutterActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PlatformNativeViewFactory.registerWith(this);
    }
}
