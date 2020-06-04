package com.github.hailouwang.demosforapi.rxbus;

import androidx.lifecycle.Lifecycle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private Subject<Object> mRxBus;

    // https://blog.csdn.net/qq_35387940/article/details/85060876
    // 静态内部类，从jvm 虚拟机的角度来保证线程安全
    static class Helper {
        private static RxBus INSTANCE = new RxBus();
    }

    private RxBus() {
        mRxBus = PublishSubject.create().toSerialized();
    }

    public static final RxBus getInstance() {
        return Helper.INSTANCE;
    }

    public synchronized boolean isRegistered() {
        return mRxBus.hasObservers();
    }

    public Observable<Object> register() {
        return register(Object.class);
    }

    /**
     * - 外部需要管理 Observable<T> 注册以及资源释放
     * - 外部需要管理 Observable<T> 线程切换
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(Class<T> type) {
        // filter(Functions.isInstanceOf(clazz)).cast(clazz);
        return mRxBus.ofType(type);
    }

    public <T> ObservableSubscribeProxy<T> registerInMainThread(Lifecycle lifecycle, Class<T> type) {
        // filter(Functions.isInstanceOf(clazz)).cast(clazz);

        // 1、得到 发起时的生命周期方法 E lastEvent = provider.peekLifecycle()
        // 2、得到需要配对结束的生命周期方法 endEvent = provider.correspondingEvents().apply(lastEvent)
        // 3、得到 与 endEvent 对应的生命周期对象，生成：false、false、true(匹配的生命周期结束方法)
        // 4、如果 AutoDisponse 生成的 Maybe 发射了数据，那么，在 Maybe#onSuccess 中 就将 数据通道 Disponse，进而释放资源
        return mRxBus.ofType(type)
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)));
    }

    public <T> ObservableSubscribeProxy<T> registerInNewThread(Lifecycle lifecycle, Class<T> type) {
        // filter(Functions.isInstanceOf(clazz)).cast(clazz);

        // 1、得到 发起时的生命周期方法 E lastEvent = provider.peekLifecycle()
        // 2、得到需要配对结束的生命周期方法 endEvent = provider.correspondingEvents().apply(lastEvent)
        // 3、得到 与 endEvent 对应的生命周期对象，生成：false、false、true(匹配的生命周期结束方法)
        // 4、如果 AutoDisponse 生成的 Maybe 发射了数据，那么，在 Maybe#onSuccess 中 就将 数据通道 Disponse，进而释放资源
        return mRxBus.ofType(type)
                .observeOn(Schedulers.newThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)));
    }
}
