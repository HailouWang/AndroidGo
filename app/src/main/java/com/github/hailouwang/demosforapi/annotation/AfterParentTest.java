package com.github.hailouwang.demosforapi.annotation;

import android.util.Log;

public class AfterParentTest {
    @AfterPermissionGranted(0x123456)
    private void checkPermission() {
        Log.d("hlwang", "Parent AfterText checkPermission");
    }

    @AfterPermissionGranted(0x123456)
    private void checkPermissionParent(String custormText) {
        Log.d("hlwang", "Parent custormText : " + custormText);
    }
}
