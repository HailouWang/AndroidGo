package com.github.hailouwang.demosforapi.thread;

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

/**
 * 安全的停止一个线程
 * <p>
 * https://www.cnblogs.com/dsj2016/p/7678553.html
 *
 * https://www.jianshu.com/p/0cf9cadd25a7
 *
 * https://blog.csdn.net/zhangliangzi/article/details/52484302
 *
 *
 *
 *
 *
 * <p>
 * 为什么要安全停掉一个任务：
 * 1、释放Cpu调度资源
 * 2、管理好线程锁，不会引起其他线程异常
 * <p>
 * 方法：
 * 1、很长的一个run方法：调整结构
 * 2、长时间Sleep：
 * 3、死循环，轮询任务
 */
public class SafeStopThreadFragment extends Fragment {

    Thread sleepThread;

    private TextView startThread, huanxingThread;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.safe_thread_test, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startThread = view.findViewById(R.id.startThread);
        startThread.setOnClickListener(v -> {
            sleepThreadWeakupTest();
        });

        huanxingThread = view.findViewById(R.id.huanxingThread);
        huanxingThread.setOnClickListener(v -> {
            weakupSleepThread();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void sleepThreadWeakupTest() {
        if (sleepThread == null) {
            sleepThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d("hlwang", "睡眠开始, active : " + sleepThread.isAlive());
                        TimeUnit.DAYS.sleep(1L);
                        Log.d("hlwang", "睡眠结束");
                    } catch (Exception e) {
                        boolean isInterrupted = sleepThread.isInterrupted();
                        Log.d("hlwang", "异常:" + e
                                + ", isInterrupted : " + isInterrupted);

                        /**
                         * 抛出，java.lang.InterruptedException，但Sleep会清楚标记位，此时需要再次interrupted
                         */
                        if (!isInterrupted) {
                            Thread.interrupted();
                        }
                    } finally {
                        Log.d("hlwang", "finally块被执行");
                    }
                }
            });
        }

        if (!sleepThread.isAlive()) {
            sleepThread.start();
        }
    }

    private void weakupSleepThread() {
        if (sleepThread != null) {
            // Thread.interrupt()方法并不会像Thread.stop()方法一样立即结束线程,它只是设置了一个中断标志,需要在代码实现中去手动判断这个标志并且推出。
            boolean isInterrupted = sleepThread.isInterrupted();
            Log.d("hlwang", "SafeStopThreadFragment weakupSleepThread isInterrupted : " + isInterrupted);

            if (!isInterrupted) {
                sleepThread.interrupt();
            }
        }
    }
}
