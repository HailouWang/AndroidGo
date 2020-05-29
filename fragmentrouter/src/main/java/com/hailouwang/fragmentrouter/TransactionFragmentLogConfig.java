package com.hailouwang.fragmentrouter;

import android.util.Log;

public class TransactionFragmentLogConfig {
    private static final String LOG_TAG = "print_log_debug";
    public static boolean LOCAL_LOGD;

    static {
        LOCAL_LOGD = BuildConfig.DEBUG ||
                Log.isLoggable(LOG_TAG, Log.VERBOSE);
    }
}
