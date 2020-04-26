package com.github.hailouwang.demosforapi.annotation;

import android.util.Log;

public class AfterTest extends AfterParentTest {

    @AfterPermissionGranted(0x123456)
    private void checkPermission() {
        Log.d("hlwang", "Child AfterText checkPermission");
    }
}
