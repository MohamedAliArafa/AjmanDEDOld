package com.zeowls.ajmanded;

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

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;
import com.zeowls.ajmanded.adapters.CustomExpandableListAdapter;
import com.zeowls.ajmanded.adapters.ExpandableListDataSource;
import com.zeowls.ajmanded.screens.Login.LoginFragment;
import com.zeowls.ajmanded.screens.dashboard.DashBoardActivity;
import com.zeowls.ajmanded.screens.dashboard.DashboardFragment;
import com.zeowls.ajmanded.screens.home.HomeFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbar);
        mLogo = findViewById(R.id.logo);
        mAppBar = findViewById(R.id.appbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        FragmentManager fragmentManager = getSupportFragmentManager();

//        ImageView fabIconNew = new ImageView(this);
//        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_socialmediaicon));
//        FloatingActionButton fab = findViewById(R.id.fab);
//        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
//
//        ImageView item1 = new ImageView(this);
//        ImageView item2 = new ImageView(this);
//        ImageView item3 = new ImageView(this);
//        ImageView item4 = new ImageView(this);
//
//        SubActionButton button1 = itemBuilder.setContentView(item1).build();
//        button1.setBackgroundResource(R.drawable.ic_facebookicon);
//        SubActionButton button2 = itemBuilder.setContentView(item2).build();
//        button2.setBackgroundResource(R.drawable.ic_whatsappicon);
//        SubActionButton button3 = itemBuilder.setContentView(item3).build();
//        button3.setBackgroundResource(R.drawable.ic_twittericon);
//        SubActionButton button4 = itemBuilder.setContentView(item4).build();
//        button4.setBackgroundResource(R.drawable.ic_youtubeicon);
//
//        //attach the sub buttons
//        new FloatingActionMenu.Builder(this)
//                .addSubActionView(button4)
//                .addSubActionView(button3)
//                .addSubActionView(button2)
//                .addSubActionView(button1)
//                .attachTo(fab)
//                .build();

        initItems();

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(mToolbar)
                .withMenuLayout(R.layout.sliding_root_layout)
                .inject();

//        mDrawerLayout = findViewById(R.id.drawer_layout);
        mExpandableListView = findViewById(R.id.left_drawer);
        View listHeaderView = getLayoutInflater().inflate(R.layout.list_item_drawer_header, null, false);
        View listFooterView = getLayoutInflater().inflate(R.layout.list_item_drawer_footer, null, false);

        listHeaderView.findViewById(R.id.dash_btn).setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, DashBoardActivity.class));
            finish();
//            dashboardFragment = (DashboardFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG_DASHBOARD);
//            if (dashboardFragment == null) {
//                dashboardFragment = new DashboardFragment();
//            }
//            toggleFragment(dashboardFragment);
            mSlidingRootNav.closeMenu(true);
        });

        listHeaderView.findViewById(R.id.home_btn).setOnClickListener(view -> {
//            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG_HOME);
//            if (homeFragment == null) {
//                homeFragment = new HomeFragment();
//            }
//            toggleFragment(homeFragment);
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
//            loginFragment = (LoginFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG_LOGIN);
//            if (loginFragment == null) {
//                loginFragment = new LoginFragment();
//            }
//            toggleFragment(loginFragment);
            mSlidingRootNav.closeMenu(true);
            MyApplication.get(this).removeUser();
        });

        listHeaderView.findViewById(R.id.help_btn).setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, FaqActivity.class));
            finish();
//            loginFragment = (LoginFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG_LOGIN);
//            if (loginFragment == null) {
//                loginFragment = new LoginFragment();
//            }
//            toggleFragment(loginFragment);
            mSlidingRootNav.closeMenu(true);
            MyApplication.get(this).removeUser();
        });

        mExpandableListView.addHeaderView(listHeaderView);
        mExpandableListView.addFooterView(listFooterView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        addDrawerItems();
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                R.string.drawer_open, R.string.drawer_close) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//        };
//        showChatHead(this, true);
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
//            mDrawerLayout.closeDrawer(GravityCompat.START);
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
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
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
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }

        //noinspection SimplifiableIfStatement
//        switch (id) {
//            case R.id.action_lang:
//                Locale locale = Locale.getDefault();
//                if (locale.toString().toLowerCase().equals("ar")) {
//                    Locale.setDefault(new Locale("en"));
//                    Resources res = getResources();
//                    Configuration conf = res.getConfiguration();
//                    conf.locale = Locale.getDefault();
//                    DisplayMetrics dm = res.getDisplayMetrics();
//                    res.updateConfiguration(conf, dm);
//                    onConfigurationChanged(conf);
//                } else {
//                    Locale.setDefault(new Locale("ar"));
//                    Resources res = getResources();
//                    Configuration conf = res.getConfiguration();
//                    conf.locale = Locale.getDefault();
//                    DisplayMetrics dm = res.getDisplayMetrics();
//                    res.updateConfiguration(conf, dm);
//                    onConfigurationChanged(conf);
//                }
//                return true;
//        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    @TargetApi(Build.VERSION_CODES.M)
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE) {
//            showChatHead(this, false);
//        }
//    }

//    @SuppressLint("NewApi")
//    private void showChatHead(Context context, boolean isShowOverlayPermission) {
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
//            context.startService(new Intent(context, ChatHeadService.class));
//            return;
//        }
//
//        if (Settings.canDrawOverlays(context)) {
//            context.startService(new Intent(context, ChatHeadService.class));
//            return;
//        }
//
//        if (isShowOverlayPermission) {
//            final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (restartService) {
                stopService(new Intent(this, ChatHeadService.class));
                restartService = true;
            }

        } catch (Exception e) {

        }
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
