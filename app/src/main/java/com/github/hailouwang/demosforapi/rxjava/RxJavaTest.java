package com.github.hailouwang.demosforapi.rxjava;

import io.reactivex.Observable;

public class RxJavaTest {
    public void test() {
        Observable.just("Hello world")
                .subscribe(word -> {
                    System.out.println("got " + word + " @ "
                            + Thread.currentThread().getName());
                });
    }
}
