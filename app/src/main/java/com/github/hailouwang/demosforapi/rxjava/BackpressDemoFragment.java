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

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlinx.coroutines.flow.Flow;

/**
 * https://www.jianshu.com/p/2c4799fa91a4
 * https://www.jianshu.com/p/a75ecf461e02
 * <p>
 * 背压策略的一个前提是异步环境，也就是说，被观察者和观察者处在不同的线程环境中。
 *
 * // 警告：连续发送事件的流，需要在退出页面后，关掉，否则，会出现内存耗尽等异常
 * // 线程被多次创建
 */
public class BackpressDemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rxjava_backpress_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.pull).setOnClickListener(v -> {
            //被观察者将产生100000个事件
            Flowable.range(1, 100000)
                    .doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            Log.d("hlwang", "BackpressDemoFragment 上游发送数据 s : " + integer);
                        }
                    })
                    .subscribe(new MySubscriber());
        });

        view.findViewById(R.id.observable_drop).setOnClickListener(v -> {
            // 缓存 128 个，之后的顺序就不连续了
            Flowable.interval(1, TimeUnit.MILLISECONDS)
                    .onBackpressureDrop()
                    .observeOn(Schedulers.newThread())
                    .subscribe(new MySubscriber01());
        });

        view.findViewById(R.id.cache).setOnClickListener(v -> {
            Disposable disposable = Observable.interval(1, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.newThread())
                    //这个操作符简单理解就是把100毫秒内的事件打包成list发送
                    .buffer(100, TimeUnit.MILLISECONDS)
                    .subscribe(new Consumer<List<Long>>() {
                        @Override
                        public void accept(List<Long> longs) throws Exception {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.w("hlwang", "longs : ---->" + longs.size());
                        }
                    });
        });

        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class MySubscriber implements Subscriber<Integer> {
        private Subscription subscription;
        private int index;

        @Override
        public void onSubscribe(Subscription s) {
            subscription = s;
            //一定要在onStart中通知被观察者先发送一个事件
            s.request(1);
        }

        @Override
        public void onNext(Integer s) {
            Log.d("hlwang", "BackpressDemoFragment 收到数据，接着再拉下一个 s : " + s);
            if (subscription != null) {
                if (++index <= 10) {
                    //处理完毕之后，在通知被观察者发送下一个事件
                    subscription.request(1);
                }
            }
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    }

    class MySubscriber01 implements Subscriber<Long> {
        private Subscription subscription;

        @Override
        public void onError(Throwable e) {
            Log.e("ERROR", e.toString());
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onSubscribe(Subscription s) {
            Log.w("hlwang", "onSubscribe ---->" + s);
            subscription = s;
            s.request(1);
        }

        @Override
        public void onNext(Long aLong) {
            Log.w("hlwang", "onSubscribe ---->" + aLong);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (subscription != null) {
                subscription.request(1);
            }
        }
    }

}
