package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * https://www.jianshu.com/p/45309538ad94
 *
 * 使用场景：https://blog.csdn.net/ming_147/article/details/70279217
 */
public class SingleDemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_1_rxjava_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.single).setOnClickListener(v -> {
            Disposable disposable = Single.create(new SingleOnSubscribe<String>() {

                @Override
                public void subscribe(@NonNull SingleEmitter<String> e) throws Exception {
                    e.onSuccess("test");
                }
            }).subscribe(new Consumer<String>() {
                @Override
                public void accept(@NonNull String s) throws Exception {
                    Log.d("hlwang", "SingleDemoFragment Single onSuccess s : " + s
                            + ", thread : " + Thread.currentThread());
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    Log.d("hlwang", "SingleDemoFragment Single onError : " + throwable.getMessage()
                            + ", thread : " + Thread.currentThread());
                }
            });
        });

        view.findViewById(R.id.completable).setOnClickListener(v -> {
            Disposable disposable = Completable.create(new CompletableOnSubscribe() {
                @Override
                public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {

                    try {
                        TimeUnit.SECONDS.sleep(1);
                        emitter.onComplete();
                    } catch (InterruptedException e) {
                        emitter.onError(e);
                    }
                }
            }).andThen(Observable.range(1, 10))
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            Log.d("hlwang", "SingleDemoFragment Completable accept : " + integer
                                    + ", thread : " + Thread.currentThread());
                        }
                    });
        });

        view.findViewById(R.id.maybe).setOnClickListener(v -> {
            Disposable disposable = Maybe.create(new MaybeOnSubscribe<String>() {

                @Override
                public void subscribe(@NonNull MaybeEmitter<String> e) throws Exception {
                    e.onSuccess("testA");
                }
            }).subscribe(new Consumer<String>() {

                @Override
                public void accept(@NonNull String s) throws Exception {
                    Log.d("hlwang", "SingleDemoFragment Maybe accept : " + s
                            + ", thread : " + Thread.currentThread());
                }
            });
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
