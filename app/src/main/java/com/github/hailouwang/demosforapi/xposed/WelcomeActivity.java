package com.github.hailouwang.demosforapi.xposed;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.hailouwang.demosforapi.R;
import com.google.android.material.navigation.NavigationView;

public class WelcomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
    ,BlankFragment.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigatiionView;
    SharedPreferences mSharedPref;//记录上次的选择

    private Handler mDrawerHandler = new Handler();

    private int mSelectedId = 0;
    private static final String DEFAULT_FRAGMENT_VIEW_INDEX = "default_fragment_view_index";//sharedPreference 名称
    private static final String SAVEDINSTANCE_SELECTED_INDEX="saved_instance_select_index";//savedInstance中的记录Fragment View

    private static final String OPEN_FRAGMENT="open_drawer";//是否启动后打开抽屉

    private static final String EXTRAS_FRAGMENT_VIEW_INDEX = "extras_fragment_view_index";//fragment随着Intent中的Bundle来切换

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavigatiionView = (NavigationView) findViewById(R.id.navigation_view);
        assert mNavigatiionView != null;

        mNavigatiionView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,
                R.string.xposed_welcome_activity_drawer_open,
                R.string.xposed_welcome_activity_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d("hlwang","WelcomeActivity onDrawerSlide....slideOffset:"+slideOffset);
                //左上角图标动画
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.d("hlwang","WelcomeActivity onDrawerOpened....");
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        //要修改左上角图标，可以设定此属性，通过主题修改，需设定android:navigationIcon配置Drawable。
        //toolbar.setNavigationIcon(getDrawable(R.drawable.ic_nav_about));

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Menu menu = mNavigatiionView.getMenu();
        int selectedIndex = mSharedPref.getInt(DEFAULT_FRAGMENT_VIEW_INDEX,0);
        selectedIndex = savedInstanceState==null?selectedIndex:savedInstanceState.getInt(SAVEDINSTANCE_SELECTED_INDEX,selectedIndex);
        Bundle extras = getIntent().getExtras();
        selectedIndex = extras==null?selectedIndex:extras.getInt(EXTRAS_FRAGMENT_VIEW_INDEX,selectedIndex);

        mSelectedId = menu.getItem(selectedIndex).getItemId();
        //assert selectedIndex is valid

        switchFragment(mSelectedId);

        boolean openDrawer = mSharedPref.getBoolean(OPEN_FRAGMENT,false);
        if(openDrawer){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.closeDrawers();
        }
    }

    public void switchFragment(int index){
        mNavigatiionView.getMenu().findItem(mSelectedId).setChecked(true);
        mDrawerHandler.removeCallbacksAndMessages(null);
        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(mSelectedId);
            }
        },250);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("hlwang","WelcomeActivity onNavigationItemSelected menuItem:"+item);
        mSelectedId = item.getItemId();
        switchFragment(mSelectedId);
        return true;
    }

    private void navigate(final int itemId){
        final View elevation = findViewById(R.id.elevation);
        Fragment navFragment = null;
        switch(itemId){
            case R.id.nav_item_framework:
                navFragment = BlankFragment.newInstance("Installer","Installer");
                break;
            case R.id.nav_item_modules:
                navFragment = BlankFragment.newInstance("Modules","Modules");
                break;
            case R.id.nav_item_downloads:
                navFragment = BlankFragment.newInstance("Downloads","Downloads");
                break;
            case R.id.nav_item_logs:
                navFragment = BlankFragment.newInstance("Logs","Logs");
                break;
            case R.id.nav_item_settings:
                navFragment = BlankFragment.newInstance("Settings","Settings");
                break;
            case R.id.nav_item_support:
                navFragment = BlankFragment.newInstance("Support","Support");
                break;
            case R.id.nav_item_about:
                navFragment = BlankFragment.newInstance("About","About");
                break;
        }

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,dp(4));

        if(navFragment != null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);

            try {
                transaction.replace(R.id.content_frame, navFragment).commit();

                if (elevation != null) {
                    Animation a = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            super.applyTransformation(interpolatedTime, t);
                            elevation.setLayoutParams(params);
                        }
                    };

                    a.setDuration(150);
                    elevation.startAnimation(a);
                }
            }catch(IllegalStateException ignored){
                Log.d("hlwang","WelcomeActivity ...ignore :"+ignored);
            }
        }
    }

    public int dp(float value){
        float density = getApplicationContext().getResources().getDisplayMetrics().density;

        if(value == 0){
            return 0;
        }

        return (int) Math.ceil(density*value);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
