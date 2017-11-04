package com.zeowls.ajmanded.screens.dashboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.zeowls.ajmanded.FaqActivity;
import com.zeowls.ajmanded.screens.home.HomeActivity;
import com.zeowls.ajmanded.LoginActivity;
import com.zeowls.ajmanded.MyApplication;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.adapters.CustomExpandableListAdapter;
import com.zeowls.ajmanded.adapters.ExpandableListDataSource;
import com.zeowls.ajmanded.utility.SharedTool.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashBoardActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private ArrayList<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListData;
    private SlidingRootNav mSlidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(mToolbar)
                .withMenuLayout(R.layout.sliding_root_layout)
                .inject();

        mExpandableListView = findViewById(R.id.left_drawer);
        View listHeaderView = getLayoutInflater().inflate(R.layout.list_item_drawer_header, null, false);
        View listFooterView = getLayoutInflater().inflate(R.layout.list_item_drawer_footer, null, false);

        listHeaderView.findViewById(R.id.home_btn).setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
//            MyApplication.get(this).removeUser();
            finish();
            mSlidingRootNav.closeMenu(true);
        });

       listHeaderView.findViewById(R.id.dash_btn).setOnClickListener(view -> {
//            startActivity(new Intent(this, HomeActivity.class));
//            MyApplication.get(this).removeUser();
//            finish();
           mSlidingRootNav.closeMenu(true);
        });

        listFooterView.findViewById(R.id.logout_text).setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            UserData.clearUser(this);
            finish();
            mSlidingRootNav.closeMenu(true);
        });

        listFooterView.findViewById(R.id.lang_text).setOnClickListener(view -> {
            MyApplication.get(this).toggleLocale();
            restartActivity();
            mSlidingRootNav.closeMenu(true);
        });

        listHeaderView.findViewById(R.id.help_btn).setOnClickListener(view -> {
            startActivity(new Intent(this, FaqActivity.class));
//            MyApplication.get(this).removeUser();
            finish();
            mSlidingRootNav.closeMenu(true);
        });

        mExpandableListView.addHeaderView(listHeaderView);
        mExpandableListView.addFooterView(listFooterView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList<>(mExpandableListData.keySet());
        addDrawerItems();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new DashboardFragment());
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.commit();
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            mSlidingRootNav.closeMenu();
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        restartActivity();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_call:
                startActivity(new Intent(DashBoardActivity.this, FaqActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
