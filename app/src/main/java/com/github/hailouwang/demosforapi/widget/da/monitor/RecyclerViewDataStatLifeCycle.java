package com.github.hailouwang.demosforapi.widget.da.monitor;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import java.lang.ref.WeakReference;

public class RecyclerViewDataStatLifeCycle {
    private boolean isShowToUser; // 是否展示给用户
    private boolean isDataStatWorking = true; // 数据采集状态是否处于工作中
    private WeakReference<LifecycleOwner> activityWeakReference;

    private RecyclerViewDataStatLifeCycle(LifecycleOwner activity) {
        activityWeakReference = new WeakReference<LifecycleOwner>(activity);
        if(activityWeakReference != null && activityWeakReference.get() != null){
        }
    }

    public static RecyclerViewDataStatLifeCycle newDefaultRecyclerViewDataStatStrategy(LifecycleOwner activity) {
        return new RecyclerViewDataStatLifeCycle(activity);
    }

    public boolean isShowToUser() {
        return isShowToUser;
    }

    public RecyclerViewDataStatLifeCycle setShowToUser(boolean showToUser) {
        isShowToUser = showToUser;
        return this;
    }

    public boolean isDataStatWorking() {
        return isDataStatWorking;
    }

    public RecyclerViewDataStatLifeCycle setDataStatWorking(boolean dataStatWorking) {
        isDataStatWorking = dataStatWorking;
        return this;
    }
}
