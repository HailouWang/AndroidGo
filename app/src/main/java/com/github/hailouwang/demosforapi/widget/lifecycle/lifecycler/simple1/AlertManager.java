package com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.simple1;

import android.content.Context;

public class AlertManager implements AndroidLifeCycler {

    public AlertManager(Context context) {
        // ...
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {
        // 在 onStart 生命周期中，来处理定位的相关信息
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
