package com.github.hailouwang.demosforapi.router.page;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.router.constants.ARouterConstants;

@Route(path = ARouterConstants.SECOND_TEST_ACTIVITY)
public class SecondARouterTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview);

        String test = getIntent().getStringExtra("test");

        TextView textView = findViewById(R.id.tv);
        textView.setText(getClass().getCanonicalName() + "--argument : " + test);
    }
}
