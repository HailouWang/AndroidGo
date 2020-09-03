package com.github.hailouwang.demosforapi.rxjava.certtest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.router.page.MainLooper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * publish 与 refcount、share
 */
public class RxJavaCertTestShanDongFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_common_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.test1);
        textView.setText("点击开始测试");
        textView.setOnClickListener(v -> {
            rxJavaExamination1();
            Log.d(Tags.HLWANG_TAG, "-----------------------");
            rxJavaExamination2();
            Log.d(Tags.HLWANG_TAG, "-----------------------");
            rxJavaExamination3();
        });
    }

    // 核心：主题多次subscribe
    private void rxJavaExamination1() {
        Observable<Long> observable = Observable.interval(5, TimeUnit.MILLISECONDS)
                .take(Integer.MAX_VALUE);

        // 观察者1
        Consumer<Long> subscriber1 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者1 ：value ： " + value);
            }
        };

        // 观察者2
        Consumer<Long> subscriber2 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者2 ：value ： " + value);
            }
        };

        // 观察者1与主题建立关联
        Disposable disposable1 = observable.subscribe(subscriber1);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 观察者2与主题建立关联
        Disposable disposable2 = observable.subscribe(subscriber2);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable1.dispose();
        disposable2.dispose();
    }

    // 核心：ObservablePublish
    private void rxJavaExamination2() {
        Observable<Long> observable = Observable.interval(5, TimeUnit.MILLISECONDS)
                .take(Integer.MAX_VALUE);

        // 观察者3
        Consumer<Long> subscriber3 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者3 ：value ： " + value);
            }
        };

        // 观察者4
        Consumer<Long> subscriber4 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者4 ：value ： " + value);
            }
        };

        ConnectableObservable connectableObservable = observable.publish();
        connectableObservable.connect();

        // 观察者1与主题建立关联
        Disposable disposable3 = connectableObservable.subscribe(subscriber3);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 观察者2与主题建立关联
        Disposable disposable4 = connectableObservable.subscribe(subscriber4);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable3.dispose();
        disposable4.dispose();
    }

    // 核心：ObservableRefCount
    private void rxJavaExamination3() {
        Observable<Long> observable = Observable.interval(5, TimeUnit.MILLISECONDS)
                .take(Integer.MAX_VALUE);

        // 观察者5
        Consumer<Long> subscriber5 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者5 ：value ： " + value);
            }
        };

        // 观察者6
        Consumer<Long> subscriber6 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者6 ：value ： " + value);
            }
        };

        // 观察者7
        Consumer<Long> subscriber7 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者7 ：value ： " + value);
            }
        };

        // 观察者8
        Consumer<Long> subscriber8 = new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long value) throws Exception {
                Log.d(Tags.HLWANG_TAG, "观察者8 ：value ： " + value);
            }
        };

        ConnectableObservable connectableObservable = observable.publish();
        connectableObservable.connect();

        observable = connectableObservable.refCount();

        // 观察者1与主题建立关联
        Disposable disposable5 = observable.subscribe(subscriber5);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 观察者2与主题建立关联
        Disposable disposable6 = observable.subscribe(subscriber6);
        disposable5.dispose();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable6.dispose();

        Log.d(Tags.HLWANG_TAG, "-----------------------");

        // 观察者1与主题建立关联
        Disposable disposable7 = observable.subscribe(subscriber7);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 观察者2与主题建立关联
        Disposable disposable8 = observable.subscribe(subscriber8);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable7.dispose();
        disposable8.dispose();
    }
}
