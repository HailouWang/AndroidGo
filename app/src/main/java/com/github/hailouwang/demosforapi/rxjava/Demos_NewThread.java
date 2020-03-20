package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.Tags;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.NewThreadScheduler;

public class Demos_NewThread extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(Tags.HLWANG_TAG,"Main Thread id:"+Thread.currentThread().getId());
        //线程变换
        Observable.just(1,2,3,4,5)
                .subscribeOn(new NewThreadScheduler())//指定subscribe所在的线程
                .observeOn(new NewThreadScheduler())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.d(Tags.HLWANG_TAG,"Map Thread Id:"+Thread.currentThread().getId());
                        return integer+10;
                    }
                })
                .observeOn(new NewThreadScheduler())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(Tags.HLWANG_TAG,"onSubscribe Thread Id:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(Tags.HLWANG_TAG,"onNext Thread Id:"+Thread.currentThread().getId()+",number:"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(Tags.HLWANG_TAG,"onError Thread Id:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(Tags.HLWANG_TAG,"onComplete Thread Id:"+Thread.currentThread().getId());
                    }
                });
    }
}
