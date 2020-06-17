package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.github.hailouwang.demosforapi.R;
import com.trello.rxlifecycle3.components.RxActivity;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxLifecycle：https://github.com/trello/RxLifecycle
 * uber：https://uber.github.io/AutoDispose/
 */
public class AutoisponseDemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("hlwang", "RxLifeCycleDemoFragment Observable onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rxjava_auto_disponse_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.autodisponse).setOnClickListener(v -> {
            Disposable disposable = Observable.interval(1, TimeUnit.MILLISECONDS)
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.d("hlwang", "RxLifeCycleDemoFragment Observable aLong : " + aLong);
                        }
                    });
        });

        view.findViewById(R.id.autodisponseWithEvent).setOnClickListener(v -> {
            Disposable disposable = Observable.interval(1, TimeUnit.MILLISECONDS)
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)))
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
