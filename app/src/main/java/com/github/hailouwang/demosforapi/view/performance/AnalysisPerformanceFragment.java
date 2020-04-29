package com.github.hailouwang.demosforapi.view.performance;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/**
 * https://blog.csdn.net/zhaizu/article/details/78008465
 * https://developer.android.google.cn/studio/profile/
 * https://www.jianshu.com/p/c2da3e0d5dd8
 */
public class AnalysisPerformanceFragment extends Fragment implements Handler.Callback {

    private final Handler frameMetricsHandler = new Handler();
    @RequiresApi(24)
    private final Window.OnFrameMetricsAvailableListener frameMetricsAvailableListener = new Window.OnFrameMetricsAvailableListener() {
        @Override
        public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int dropCountSinceLastInvocation) {
            long costDuration = frameMetrics.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION);
            Log.d("hlwang", "layoutMeasureDurationNs: " + costDuration);
        }
    };
    private static final int TOTAL = 100;
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private int index;

    @Override
    public void onStart() {
        super.onStart();

        Toast.makeText(getContext(), "看日志", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getActivity().getWindow().addOnFrameMetricsAvailableListener(frameMetricsAvailableListener, frameMetricsHandler);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getActivity().getWindow().removeOnFrameMetricsAvailableListener(frameMetricsAvailableListener);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performance_ll_02, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        super.onCreate(savedInstanceState);
        final TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setOnClickListener(v -> {
            tv.setText(" index --> " + index);
            index++;
        });
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        Log.d("hlwang", "PerformanceRelativeLayoutFragment handleMessage message : " + msg);
        return false;
    }
}
