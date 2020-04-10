package com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.simple1;

import android.content.Context;

public class LocationManager implements AndroidLifeCycler {

    interface Callback {
        void onLocationSuccess();
    }

    public LocationManager(Context context, Callback callback) {
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
