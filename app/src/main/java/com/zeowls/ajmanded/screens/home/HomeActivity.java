package com.zeowls.ajmanded.screens.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
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
import android.widget.ImageView;

import com.roughike.bottombar.BottomBar;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;
import com.zeowls.ajmanded.FaqActivity;
import com.zeowls.ajmanded.LoginActivity;
import com.zeowls.ajmanded.MyApplication;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.adapters.CustomExpandableListAdapter;
import com.zeowls.ajmanded.adapters.ExpandableListDataSource;
import com.zeowls.ajmanded.screens.Login.LoginFragment;
import com.zeowls.ajmanded.screens.Search.SearchActivity;
import com.zeowls.ajmanded.screens.dashboard.DashBoardActivity;
import com.zeowls.ajmanded.screens.dashboard.DashboardFragment;
import com.zeowls.ajmanded.utility.SharedTool.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements DragListener {

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListData;
    private boolean isRTL;

    private Toolbar mToolbar;
    private ImageView mLogo;
    private AppBarLayout mAppBar;
    private boolean restartService = true;
    private SlidingRootNav mSlidingRootNav;

    private DashboardFragment dashboardFragment;
    private HomeFragment homeFragment;
    private LoginFragment loginFragment;
    private String FRAGMENT_TAG_DASHBOARD = "dashboard_key_fragment";
    private String FRAGMENT_TAG_HOME = "home_key_fragment";
    private String FRAGMENT_TAG_LOGIN = "login_key_fragment";
    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbar);
        mLogo = findViewById(R.id.logo);
        mAppBar = findViewById(R.id.appbar);
        mBottomBar = findViewById(R.id.bottomBar);

        mBottomBar.setOnTabSelectListener(tabId ->{
            switch(tabId) {
                case R.id.tab_share:
                    break;
                case R.id.tab_search:
                    startActivity(new Intent(this, SearchActivity.class));
                    break;

            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        FragmentManager fragmentManager = getSupportFragmentManager();

        initItems();

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(mToolbar)
                .withMenuLayout(R.layout.sliding_root_layout)
                .inject();

        mExpandableListView = findViewById(R.id.left_drawer);
        View listHeaderView = getLayoutInflater().inflate(R.layout.list_item_drawer_header, null, false);
        View listFooterView = getLayoutInflater().inflate(R.layout.list_item_drawer_footer, null, false);

        listHeaderView.findViewById(R.id.dash_btn).setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, DashBoardActivity.class));
            finish();
            mSlidingRootNav.closeMenu(true);
        });

        listHeaderView.findViewById(R.id.home_btn).setOnClickListener(view -> {
            mSlidingRootNav.closeMenu(true);
        });

        listFooterView.findViewById(R.id.lang_text).setOnClickListener(view -> {
            MyApplication.get(this).toggleLocale();
            restartActivity();
            mSlidingRootNav.closeMenu(true);
        });

        listFooterView.findViewById(R.id.logout_text).setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
            mSlidingRootNav.closeMenu(true);
            UserData.clearUser(this);
        });

        listHeaderView.findViewById(R.id.help_btn).setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, FaqActivity.class));
            finish();
            mSlidingRootNav.closeMenu(true);
            UserData.clearUser(this);
        });

        mExpandableListView.addHeaderView(listHeaderView);
        mExpandableListView.addFooterView(listFooterView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        addDrawerItems();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.commit();
    }

    private String[] items;

    private void initItems() {
        items = getResources().getStringArray(R.array.film_genre);
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
    protected void onResume() {
        super.onResume();
        isRTL = Locale.getDefault().toString().contains("ar");
        if (isRTL) {
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        } else {
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isRTL) {
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        } else {
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        restartActivity();
        restartService = false;
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
                startActivity(new Intent(HomeActivity.this, FaqActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void toggleFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_in_right, R.anim.slide_in_left);
        if (fragment instanceof LoginFragment) {
            fragmentTransaction.replace(R.id.fragment_container, loginFragment, FRAGMENT_TAG_LOGIN);
        } else if (fragment instanceof DashboardFragment){
            fragmentTransaction.replace(R.id.fragment_container, dashboardFragment, FRAGMENT_TAG_DASHBOARD);
        } else if (fragment instanceof HomeFragment) {
            fragmentTransaction.replace(R.id.fragment_container, homeFragment, FRAGMENT_TAG_HOME);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDrag(float progress) {
        if (progress == 0) {
            if (loginFragment != null){
                toggleFragment(loginFragment);
                loginFragment = null;
            }else if (dashboardFragment != null) {
                toggleFragment(dashboardFragment);
                dashboardFragment = null;
            }else if (homeFragment != null) {
                toggleFragment(homeFragment);
                homeFragment = null;
            }
        }
    }
}
