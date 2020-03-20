/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.apis.app;

import com.example.android.apis.R;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This demo shows how various action bar display option flags can be combined
 * and their effects.
 */
public class ActionBarDisplayOptions extends AppCompatActivity implements View.OnClickListener,
        ActionBar.TabListener, Spinner.OnItemSelectedListener, ActionBar.OnNavigationListener {
    private View mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_bar_display_options);

        findViewById(R.id.toggle_home_as_up).setOnClickListener(this);
        findViewById(R.id.toggle_show_home).setOnClickListener(this);
        findViewById(R.id.toggle_use_logo).setOnClickListener(this);
        findViewById(R.id.toggle_show_title).setOnClickListener(this);
        findViewById(R.id.toggle_show_custom).setOnClickListener(this);
        findViewById(R.id.cycle_custom_gravity).setOnClickListener(this);
        findViewById(R.id.toggle_visibility).setOnClickListener(this);
        findViewById(R.id.toggle_system_ui).setOnClickListener(this);

        ((Spinner) findViewById(R.id.toggle_navigation)).setOnItemSelectedListener(this);

        mCustomView = getLayoutInflater().inflate(R.layout.action_bar_display_options_custom, null);
        // Configure several action bar elements that will be toggled by display options.
        final ActionBar bar = getSupportActionBar();
        bar.setCustomView(mCustomView,
                new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        bar.addTab(bar.newTab().setText("Tab 1").setTabListener(this));
        bar.addTab(bar.newTab().setText("Tab 2").setTabListener(this));
        bar.addTab(bar.newTab().setText("Tab 3").setTabListener(this));

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        adapter.add("Item 1");
        adapter.add("Item 2");
        adapter.add("Item 3");
        bar.setListNavigationCallbacks(adapter, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_options_actions, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        final ActionBar bar = getSupportActionBar();
        int flags = 0;
        int id = v.getId();
        if (id == R.id.toggle_home_as_up) {
            flags = ActionBar.DISPLAY_HOME_AS_UP;
        } else if (id == R.id.toggle_show_home) {
            flags = ActionBar.DISPLAY_SHOW_HOME;
        } else if (id == R.id.toggle_use_logo) {
            flags = ActionBar.DISPLAY_USE_LOGO;
        } else if (id == R.id.toggle_show_title) {
            flags = ActionBar.DISPLAY_SHOW_TITLE;
        } else if (id == R.id.toggle_show_custom) {
            flags = ActionBar.DISPLAY_SHOW_CUSTOM;
        } else if (id == R.id.cycle_custom_gravity) {
            ActionBar.LayoutParams lp = (ActionBar.LayoutParams) mCustomView.getLayoutParams();
            int newGravity = 0;
            switch (lp.gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
                case Gravity.START:
                    newGravity = Gravity.CENTER_HORIZONTAL;
                    break;
                case Gravity.CENTER_HORIZONTAL:
                    newGravity = Gravity.END;
                    break;
                case Gravity.END:
                    newGravity = Gravity.START;
                    break;
            }
            lp.gravity = lp.gravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK | newGravity;
            bar.setCustomView(mCustomView, lp);
            return;
        } else if (id == R.id.toggle_visibility) {
            if (bar.isShowing()) {
                bar.hide();
            } else {
                bar.show();
            }
            return;
        } else if (id == R.id.toggle_system_ui) {
            if ((getWindow().getDecorView().getSystemUiVisibility()
                    & View.SYSTEM_UI_FLAG_FULLSCREEN) != 0) {
                getWindow().getDecorView().setSystemUiVisibility(0);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
            return;
        }

        int change = bar.getDisplayOptions() ^ flags;
        bar.setDisplayOptions(change, flags);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "ActionBarDisplayOptions : " + item, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final ActionBar bar = getSupportActionBar();
        if (parent.getId() == R.id.toggle_navigation) {
            final int mode;
            switch (position) {
                case 1:
                    mode = ActionBar.NAVIGATION_MODE_TABS;
                    break;
                case 2:
                    mode = ActionBar.NAVIGATION_MODE_LIST;
                    break;
                default:
                    mode = ActionBar.NAVIGATION_MODE_STANDARD;
            }
            bar.setNavigationMode(mode);
            return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

    }
}
