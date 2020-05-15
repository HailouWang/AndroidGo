package com.github.hailouwang.demosforapi.rxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;
import com.trello.rxlifecycle3.components.RxActivity;
import com.trello.rxlifecycle3.components.RxFragment;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxLifecycle：https://github.com/trello/RxLifecycle
 * uber：https://uber.github.io/AutoDispose/
 */
public class RxLifeCycleDemoActivity extends RxActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_lifecycle_demo);
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onCreate");

        findViewById(R.id.rxLifeCycle).setOnClickListener(v -> {
            Disposable disposable = Observable.interval(1, TimeUnit.MILLISECONDS)
                    .compose(bindToLifecycle())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.d("hlwang", "RxLifeCycleDemoFragment Observable aLong : " + aLong);
                        }
                    });
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onDestroy");
    }
}
