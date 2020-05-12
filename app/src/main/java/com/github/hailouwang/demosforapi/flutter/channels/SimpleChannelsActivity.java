package com.github.hailouwang.demosforapi.flutter.channels;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

/**
 * https://flutterchina.club/flutter-for-android/
 */
public class SimpleChannelsActivity extends FlutterActivity {
    String sharedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GeneratedPluginRegistrant.registerWith(this);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        } else {
            sharedText = "1234567890";
        }

        if (!TextUtils.isEmpty(sharedText)) {
            new MethodChannel(getFlutterEngine().getDartExecutor(), "app.channel.shared.data").setMethodCallHandler(new MethodChannel.MethodCallHandler() {
                @Override
                public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                    if (methodCall.method.contentEquals("getSharedText")) {
                        result.success(sharedText);
                        sharedText = null;
                    }
                }
            });

//            Intent flutterIntent = io.flutter.embedding.android.FlutterActivity
//                    .withNewEngine()
//                    .initialRoute("/e")
//                    .build(this);
//
//            startActivity(flutterIntent);
        }
    }


    void handleSendText(Intent intent) {
        sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
    }
}