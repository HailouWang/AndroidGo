package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ObservableLifeCycle extends Fragment {
    private static final String TAG = "hlwang";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.observable_lifecycle, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.observable_lifecycle).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    Observable.just("生命周期测试")
//                    .subscribeOn(Schedulers.io())
                            .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 doOnNext1 : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            })
//                    .subscribeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(Disposable disposable) throws Exception {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 doOnSubscribe : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            })
                            .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 doOnNext2 : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            })
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(Schedulers.io())
                            .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 observeOn1 之后 : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            })
                            .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 observeOn2 之后 : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            })
                            .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 observeOn3 之后 : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            })
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 onSubscribe : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }

                                @Override
                                public void onNext(String s) {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 onNext : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 onError : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }

                                @Override
                                public void onComplete() {
                                    Log.d(TAG, "ObservableLifeCycle 生命周期测试 onComplete : "
                                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                            + ", thread : " + Thread.currentThread());
                                }
                            });
                }
            }, "ObservableLifeCycle # New").start();
        });

        view.findViewById(R.id.subscribeOn).setOnClickListener(v -> {
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    Log.d(TAG, "ObservableLifeCycle 多次subscribeOn，主题的线程 : "
                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                            + ", thread : " + Thread.currentThread());
                    emitter.onNext("主题的线程测试");
                    emitter.onComplete();
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe();
        });

        view.findViewById(R.id.thread_switch).setOnClickListener(v -> {
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    // 这里 1
                    //
                    Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试 : "
                            + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                            + ", thread : " + Thread.currentThread());
                    emitter.onNext("RxJava subscribeOn线程切换测试！");
                    emitter.onComplete();
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            // 这里 2
                            Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试！ doOnSubscribe : "
                                    + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                    + ", thread : " + Thread.currentThread());
                        }
                    })
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Exception {
                            Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试！map : "
                                    + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                    + ", thread : " + Thread.currentThread());
                            return "Maped!!!";
                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试！ onSubscribe : "
                                    + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                    + ", thread : " + Thread.currentThread());
                        }

                        @Override
                        public void onNext(String s) {
                            Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试！ onNext : "
                                    + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                    + ", thread : " + Thread.currentThread());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试！ onError : "
                                    + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                    + ", thread : " + Thread.currentThread());
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "ObservableLifeCycle RxJava subscribeOn线程切换测试！ onComplete : "
                                    + ", isMainThread : " + (Thread.currentThread() == Looper.getMainLooper().getThread())
                                    + ", thread : " + Thread.currentThread());
                        }
                    });
        });

        view.findViewById(R.id.doOnSubscribeOn).setOnClickListener(v -> {
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<String> emitter) {
                    Log.d("MuXi", "MainActivity" + " : ObservableEmitter: " + Thread.currentThread());
                    emitter.onNext("sss");
                    emitter.onComplete();
                }
            })
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) {

                            Log.d("MuXi", "MainActivity" + " :  3: " + Thread.currentThread());
                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) {

                            Log.d("MuXi", "MainActivity" + " :  2: " + Thread.currentThread());
                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) {

                            Log.d("MuXi", "MainActivity" + " :  1: " + Thread.currentThread());
                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            Log.d("MuXi", "MainActivity" + " :  onSubscribe: " + Thread.currentThread());
                        }

                        @Override
                        public void onNext(@NonNull String s) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
