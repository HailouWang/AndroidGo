package com.hailouwang.routemoduletest.page;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hailouwang.routemoduletest.R;
import com.hailouwang.routemoduletest.constants.ARouterModuleConstants;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = ARouterModuleConstants.MODULE_SECOND_TEST_ACTIVITY)
public class SecondARouterTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview);

        TextView textView = findViewById(R.id.tv);
        textView.setText(getClass().getCanonicalName());
    }
}
