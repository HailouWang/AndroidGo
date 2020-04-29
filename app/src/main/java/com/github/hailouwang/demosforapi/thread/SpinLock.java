package com.github.hailouwang.demosforapi.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁是指当一个线程尝试获取某个锁时，如果该锁已被其他线程占用，就一直循环检测锁是否被释放，而不是进入线程挂起或睡眠状态。
 * <p>
 * https://www.jianshu.com/p/824b2e4f1eed
 *
 */
public class SpinLock implements Lock {

    private AtomicReference<Thread> owner = new AtomicReference();

    private int count;

    @Override
    public void lock() {
        Thread thread = Thread.currentThread();

        // 重入，增加计数
        if (thread == owner.get()) {
            count++;
        }

        // 自旋
        while (owner.compareAndSet(null, thread)) {

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();

        // 只有当前线程可以解锁
        if (thread == owner.get()) {
            if (count > 0) {
                count--;
            } else {
                owner.set(null);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
