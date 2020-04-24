package com.github.hailouwang.demosforapi.router.page.testactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.hailouwang.demosforapi.R;

/**
 * 自动注入的测试用例
 */
@Route(path = "/test/activity3")
public class Test3Activity extends AppCompatActivity {

    @Autowired
    String name;

    @Autowired
    int age;

    @Autowired(name = "boy")
    boolean girl;

    // 这个字段没有注解，是不会自动注入的
    private long high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        ARouter.getInstance().inject(this);

        String params = String.format("name=%s, age=%s, girl=%s, high=%s", name, age, girl, high);

        ((TextView)findViewById(R.id.test)).setText("I am " + Test3Activity.class.getName());
        ((TextView)findViewById(R.id.test2)).setText(params);
    }
}
