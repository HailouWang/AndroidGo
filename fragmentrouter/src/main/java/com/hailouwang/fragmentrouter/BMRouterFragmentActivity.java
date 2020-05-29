package com.hailouwang.fragmentrouter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public abstract class BMRouterFragmentActivity extends FragmentActivity implements PreferenceFragment.OnPreferenceStartFragmentCallback {
    public static final String BACK_STACK_PREFS = "BMRouterFragmentActivity:backstack:prefs";

    public static final String EXTRA_SHOW_FRAGMENT = "BMRouterFragmentActivity#SHOW_FRAGMENT";
    public static final String EXTRA_SHOW_FRAGMENT_ARGUMENTS = "BMRouterFragmentActivity#SHOW_FRAGMENT_ARGUMENTS";
    public static final String EXTRA_SHOW_FRAGMENT_TITLE_RESID = "BMRouterFragmentActivity#EXTRA_SHOW_FRAGMENT_TITLE_RESID";
    public static final String EXTRA_SHOW_FRAGMENT_TITLE = "BMRouterFragmentActivity#EXTRA_SHOW_FRAGMENT_TITLE";

    public static final String META_DATA_KEY_FRAGMENT_CLASS =
            "BMRouterFragmentActivity.FRAGMENT_CLASS";

    private static final String EXTRA_CHANNEL_FROM = "BMRouterFragmentActivity:channel:from";
    public static final String EXTRA_ACTIVITY_TITLE = "BMRouterFragmentActivity:activity:title";
    ;

    protected String mFragmentClass;

    private CharSequence mInitialTitle;
    private int mInitialTitleRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activityLayoutRes());

        getMetaData();
        getStartingFragmentClass(getIntent());

        if (savedInstanceState == null) {
            Bundle initialArguments = getIntent().getBundleExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS);
            switchToFragment(true, mFragmentClass, initialArguments, mInitialTitle, mInitialTitleRes, false);
        }

        String title = getIntent().getStringExtra(EXTRA_ACTIVITY_TITLE);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    protected int activityLayoutRes() {
        return R.layout.activity_page_item;
    }

    @Override
    public Intent getIntent() {
        Intent superIntent = super.getIntent();
        //super.getIntent not getIntent，As getMetaData() called before getIntent
        String startingFragment = getStartingFragmentClass(superIntent);

        // This is called from super.onCreate, isMultiPane() is not yet reliable
        // Do not use onIsHidingHeaders either, which relies itself on this method
        // wanghailu ： Fragment中可以通过getArguments()##getString(EXTRA_CHANNEL_FROM) 获取调用来源通道
        // final Bundle args = getArguments();
        // String channel = (args != null) ? args.getString(EXTRA_CHANNEL_FROM) : null;
        if (startingFragment != null) {
            Intent modIntent = new Intent(superIntent);
            modIntent.putExtra(EXTRA_SHOW_FRAGMENT, startingFragment);
            Bundle args = superIntent.getExtras();
            if (args != null) {
                args = new Bundle(args);
            } else {
                args = new Bundle();
            }

            args.putString(EXTRA_CHANNEL_FROM, superIntent.getStringExtra(EXTRA_CHANNEL_FROM));
            modIntent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, args);
            return modIntent;
        }
        return superIntent;
    }

    private String getStartingFragmentClass(Intent intent) {
        if (mFragmentClass != null) {
            return mFragmentClass;
        }

        if (intent != null) {
            mFragmentClass = intent.getStringExtra(EXTRA_SHOW_FRAGMENT);
            mInitialTitle = intent.getStringExtra(EXTRA_SHOW_FRAGMENT_TITLE);
            mInitialTitleRes = intent.getIntExtra(EXTRA_SHOW_FRAGMENT_TITLE_RESID, 0);
        }

        if (TextUtils.isEmpty(mFragmentClass)) {
            mFragmentClass = defaultFragment();
        }

        return mFragmentClass;
    }

    protected abstract boolean isValidFragment(String fragmentName);

    @SuppressLint("LongLogTag")
    private void getMetaData() {
        try {
            ActivityInfo ai = getPackageManager().getActivityInfo(getComponentName(),
                    PackageManager.GET_META_DATA);
            if (ai == null || ai.metaData == null) {
                return;
            }

            mFragmentClass = ai.metaData.getString(META_DATA_KEY_FRAGMENT_CLASS);
        } catch (PackageManager.NameNotFoundException nnfe) {
            // No recovery
            if (TransactionFragmentLogConfig.LOCAL_LOGD) {
                Log.d(Tags.TAGS_MAIN, "BMRouterFragmentActivity Cannot get Metadata for: " + getComponentName().toString());
            }
        }
    }

    protected abstract String defaultFragment();

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        Intent intent = onBuildStartFragmentIntent(this, pref.getFragment(), pref.getExtras(),
                pref.getTitle(), pref.getTitleRes());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityUtils.INSTANCE.startActivitySafely(this, intent);

        return true;
    }

    public Intent onBuildStartFragmentIntent(Context context, String fragmentName,
                                             Bundle args, CharSequence title, int titleResId) {
        Intent intent = new Intent(Intent.ACTION_MAIN);

        if (this instanceof BMRouterFragmentActivity || this instanceof PreferenceFragment.OnPreferenceStartFragmentCallback) {
            intent.setClass(context, getClass());
        } else {
            String intentClass = intent.getComponent().getClassName();
            if (BMRouterFragmentActivity.class.getName().equals(intentClass)) {
                intent.setClassName(getPackageName(), intentClass);
            }
        }
        intent.putExtra(EXTRA_SHOW_FRAGMENT, fragmentName);
        intent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, args);
        intent.putExtra(EXTRA_SHOW_FRAGMENT_TITLE_RESID, titleResId);
        intent.putExtra(EXTRA_SHOW_FRAGMENT_TITLE, title);
        return intent;
    }

    protected void switchToFragment(boolean validate, String fragmentName, Bundle args,
                                    CharSequence title, int titleResId, boolean addToBackStack) {
        if (titleResId > 0) {
            setTitle(titleResId);
        } else if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }

        // 如果因为Packagemananger无法加载MetaData导致的，fragmentName为空，此时，过滤这个切换请求
        // 此时，PackageManager可能出现了一些无法预知的情况
        if (TextUtils.isEmpty(fragmentName)) {
            return;
        }

        if (validate && !isValidFragment(fragmentName)) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "不合法的Fragment！！！", Toast.LENGTH_SHORT).show();
            }
            throw new IllegalArgumentException("Invalid fragment for router activity: "
                    + fragmentName);
        }
        Fragment f = Fragment.instantiate(this, fragmentName, args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_content, f);

        if (addToBackStack) {
            transaction.addToBackStack(BACK_STACK_PREFS);
        }

        transaction.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}