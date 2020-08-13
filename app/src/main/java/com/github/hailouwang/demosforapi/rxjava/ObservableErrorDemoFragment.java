package com.github.hailouwang.demosforapi.rxjava;

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

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 *
 */
public class ObservableErrorDemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.observable_onerror_rxjava_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = view.findViewById(R.id.action0);
        tv.setText("抛出空指针异常，不设定捕获器");
        tv.setOnClickListener(v -> {
            Thread.currentThread().setUncaughtExceptionHandler(null);
            Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    emitter.onError(new NullPointerException("就是抛空指针111！！！"));
                }
            })
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Log.d("hlwang", "ObservableErrorDemoFragment consume ... value : " + s);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d("hlwang", "ObservableErrorDemoFragment throwable ... value : " + throwable.getMessage());

                            Thread currentThread = Thread.currentThread();
                            Thread.UncaughtExceptionHandler handler = currentThread.getUncaughtExceptionHandler();
                            handler.uncaughtException(currentThread, throwable);
                        }
                    });
        });

        TextView tv01 = view.findViewById(R.id.action1);
        tv01.setText("抛出空指针异常，设定捕获器");
        tv01.setOnClickListener(v -> {
            Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                    Log.d("hlwang", "ObservableErrorDemoFragment uncaughtException throwable ... value : " + e.getMessage());
                }
            });
            Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    emitter.onError(new NullPointerException("就是抛空指针222！！！"));
                }
            })
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Log.d("hlwang", "ObservableErrorDemoFragment consume ... value : " + s);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d("hlwang", "ObservableErrorDemoFragment 222 throwable ... value : " + throwable.getMessage());

                            Thread currentThread = Thread.currentThread();
                            Thread.UncaughtExceptionHandler handler = currentThread.getUncaughtExceptionHandler();
                            handler.uncaughtException(currentThread, throwable);
                        }
                    });
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
