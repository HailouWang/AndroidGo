package com.github.hailouwang.demosforapi.widget.lifecycle.lifecycler.demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * 日志查看命令：
 * <p>
 * adb logcat -v time -s hlwang
 * <p>
 * 打印：
 * 04-09 16:28:35.519 D/hlwang  (18851): 生命周期 onCreate....
 * 04-09 16:28:35.520 D/hlwang  (18851): 生命周期 onStart....
 * 04-09 16:28:35.521 D/hlwang  (18851): 生命周期 onResume....
 * <p>
 * 04-09 16:28:42.543 D/hlwang  (18851): 生命周期 onPause....
 * 04-09 16:28:43.000 D/hlwang  (18851): 生命周期 onStop....
 * 04-09 16:28:43.004 D/hlwang  (18851): 生命周期 onDestory...
 */
public class LifeCyclerDemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Toast.makeText(getContext(), "看 Logcat 日志", Toast.LENGTH_SHORT).show();

                if (event == Lifecycle.Event.ON_CREATE) {
                    Log.d("hlwang", "onCreate ---> 生命周期 onCreate....");
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    Log.d("hlwang", "onCreate ---> 生命周期 onResume....");
                } else if (event == Lifecycle.Event.ON_START) {
                    Log.d("hlwang", "onCreate ---> 生命周期 onStart....");
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.d("hlwang", "onCreate ---> 生命周期 onPause....");
                } else if (event == Lifecycle.Event.ON_STOP) {
                    Log.d("hlwang", "onCreate ---> 生命周期 onStop....");
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    Log.d("hlwang", "onCreate ---> 生命周期 onDestory....");
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Toast.makeText(getContext(), "看 Logcat 日志", Toast.LENGTH_SHORT).show();

                if (event == Lifecycle.Event.ON_CREATE) {
                    Log.d("hlwang", "onStart ---> 生命周期 onCreate....");
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    Log.d("hlwang", "onStart ---> 生命周期 onResume....");
                } else if (event == Lifecycle.Event.ON_START) {
                    Log.d("hlwang", "onStart ---> 生命周期 onStart....");
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.d("hlwang", "onStart ---> 生命周期 onPause....");
                } else if (event == Lifecycle.Event.ON_STOP) {
                    Log.d("hlwang", "onStart ---> 生命周期 onStop....");
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    Log.d("hlwang", "onStart ---> 生命周期 onDestory....");
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Toast.makeText(getContext(), "看 Logcat 日志", Toast.LENGTH_SHORT).show();

                if (event == Lifecycle.Event.ON_CREATE) {
                    Log.d("hlwang", "onPause ---> 生命周期 onCreate....");
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    Log.d("hlwang", "onPause ---> 生命周期 onResume....");
                } else if (event == Lifecycle.Event.ON_START) {
                    Log.d("hlwang", "onPause ---> 生命周期 onStart....");
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.d("hlwang", "onPause ---> 生命周期 onPause....");
                } else if (event == Lifecycle.Event.ON_STOP) {
                    Log.d("hlwang", "onPause ---> 生命周期 onStop....");
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    Log.d("hlwang", "onPause ---> 生命周期 onDestory....");
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Toast.makeText(getContext(), "看 Logcat 日志", Toast.LENGTH_SHORT).show();

                if (event == Lifecycle.Event.ON_CREATE) {
                    Log.d("hlwang", "onStop ---> 生命周期 onCreate....");
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    Log.d("hlwang", "onStop ---> 生命周期 onResume....");
                } else if (event == Lifecycle.Event.ON_START) {
                    Log.d("hlwang", "onStop ---> 生命周期 onStart....");
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.d("hlwang", "onStop ---> 生命周期 onPause....");
                } else if (event == Lifecycle.Event.ON_STOP) {
                    Log.d("hlwang", "onStop ---> 生命周期 onStop....");
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    Log.d("hlwang", "onStop ---> 生命周期 onDestory....");
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Toast.makeText(getContext(), "看 Logcat 日志", Toast.LENGTH_SHORT).show();

                if (event == Lifecycle.Event.ON_CREATE) {
                    Log.d("hlwang", "onDestroy ---> 生命周期 onCreate....");
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    Log.d("hlwang", "onDestroy ---> 生命周期 onResume....");
                } else if (event == Lifecycle.Event.ON_START) {
                    Log.d("hlwang", "onDestroy ---> 生命周期 onStart....");
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.d("hlwang", "onDestroy ---> 生命周期 onPause....");
                } else if (event == Lifecycle.Event.ON_STOP) {
                    Log.d("hlwang", "onDestroy ---> 生命周期 onStop....");
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    Log.d("hlwang", "onDestroy ---> 生命周期 onDestory....");
                }
            }
        });
    }
}
