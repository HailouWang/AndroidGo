package com.alibaba.android.arouter.demo.kotlin;

import java.lang.System;

@com.alibaba.android.arouter.facade.annotation.Route(path = "/kotlin/test")
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/alibaba/android/arouter/demo/kotlin/KotlinTestActivity;", "Landroid/app/Activity;", "()V", "age", "", "Ljava/lang/Integer;", "name", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "module-kotlin_debug"})
public final class KotlinTestActivity extends android.app.Activity {
    @org.jetbrains.annotations.Nullable()
    @com.alibaba.android.arouter.facade.annotation.Autowired()
    public java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    @com.alibaba.android.arouter.facade.annotation.Autowired()
    public java.lang.Integer age;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public KotlinTestActivity() {
        super();
    }
}