package com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.simple1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SimpleLifeCyclerFragment extends Fragment {

    private LocationManager locationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = new LocationManager(getContext(), () -> {
        });

        locationManager.onCreate();
    }


    @Override
    public void onStart() {
        super.onStart();

        locationManager.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        locationManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        locationManager.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        locationManager.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        locationManager.onDestroy();
    }
}
