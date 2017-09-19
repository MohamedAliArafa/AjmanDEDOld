package com.zeowls.ajmanded;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.rd.PageIndicatorView;
import com.zeowls.ajmanded.ui.AnimatedFragment;
import com.zeowls.ajmanded.ui.CircularAction.FloatingActionMenu;
import com.zeowls.ajmanded.ui.CircularAction.SubActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private ImageView mNotiImageView;
    private boolean isRTL;

    private Toolbar mToolbar;
    private ImageView mLogo;
    private AppBarLayout mAppBar;

    FragmentPagerAdapter adapterViewPager;
    List<Fragment> mFragments;
    String[] fragmentsTitles;
    private ViewPager vpPager;
    PageIndicatorView mPageIndicatorView;

//    private SpaceTabLayout vpPagerHeader;

    private static final int CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE = 100;
    private boolean restartService = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLogo = (ImageView) findViewById(R.id.logo);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        vpPager = (ViewPager) findViewById(R.id.pager);
        mPageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);

//        vpPagerHeader = (SpaceTabLayout) findViewById(R.id.pager_header);

        mFragments = new ArrayList<>();
        mFragments.add(new HomeTabFragment());
        mFragments.add(new AboutDEDFragment());
        mFragments.add(new AboutDEDFragment());
        mFragments.add(new OnlineServicesFragment());

        fragmentsTitles = new String[]{this.getString(R.string.online_services),
                this.getString(R.string.about_ded),
                this.getString(R.string.latest_news)};

        //init and set the adapter
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        mPageIndicatorView.setViewPager(vpPager);
        // make the pager RTL by calling the last fragment in list
        vpPager.setCurrentItem(mFragments.size() - 1);

        //we need the savedInstanceState to get the position
//        vpPagerHeader.initialize(vpPager, getSupportFragmentManager(), mFragments);
//
//        vpPagerHeader.setTabOneText(R.string.home);
//        vpPagerHeader.setTabTwoText(R.string.e_services);
//        vpPagerHeader.setTabThreeText(R.string.about_ded);
//        vpPagerHeader.setTabFourText(R.string.latest_news);
//
//        vpPagerHeader.setTabOneIcon(R.drawable.ic_home_icon);
//        vpPagerHeader.setTabTwoIcon(R.drawable.ic_serviceicon);
//        vpPagerHeader.setTabThreeIcon(R.drawable.ic_aboutusicon);
//        vpPagerHeader.setTabFourIcon(R.drawable.ic_newsicon);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                ((AnimatedFragment) mFragments.get(position)).startAnimation();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_socialmediaicon));
//        FloatingActionButton fab = new FloatingActionButton.Builder(this)
//                .setContentView(fabIconNew)
//                .build();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        int padding = ChatHeadUtils.dpToPx(this, 10);
//        fab.setPadding(padding, padding, padding, padding);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView item1 = new ImageView(this);
        ImageView item2 = new ImageView(this);
        ImageView item3 = new ImageView(this);
        ImageView item4 = new ImageView(this);

        SubActionButton button1 = itemBuilder.setContentView(item1).build();
        button1.setBackgroundResource(R.drawable.ic_facebookicon);
        SubActionButton button2 = itemBuilder.setContentView(item2).build();
        button2.setBackgroundResource(R.drawable.ic_whatsappicon);
        SubActionButton button3 = itemBuilder.setContentView(item3).build();
        button3.setBackgroundResource(R.drawable.ic_twittericon);
        SubActionButton button4 = itemBuilder.setContentView(item4).build();
        button4.setBackgroundResource(R.drawable.ic_youtubeicon);

        //attach the sub buttons
        new FloatingActionMenu.Builder(this)
                .addSubActionView(button4)
                .addSubActionView(button3)
                .addSubActionView(button2)
                .addSubActionView(button1)
                .attachTo(fab)
                .build();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

//        mNotiImageView = (ImageView) findViewById(R.id.noti_image_view);
//        mNotiImageView.setOnTouchListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        showChatHead(this, true);
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

    private class MyPagerAdapter extends FragmentPagerAdapter {
        boolean isRTL;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            Collections.reverse(Arrays.asList(mFragments));
            Collections.reverse(Arrays.asList(fragmentsTitles));
            isRTL = Locale.getDefault().toString().contains("ar");
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return mFragments.size();
        }

        // Returns the fragment to display for that page
        @Override
        public AnimatedFragment getItem(int position) {
            return (AnimatedFragment) mFragments.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitles[position];
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
//        final MenuItem item = menu.findItem(R.id.action_call);
//        item.getActionView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, FaqActivity.class));
//            }
//        });
//        MenuItem action_lang = menu.findItem(R.id.action_lang);
//        if (isRTL)
//            action_lang.setIcon(getResources().getDrawable(R.drawable.ic_topicn2));
//        else
//            action_lang.setIcon(getResources().getDrawable(R.drawable.ic_topicn1));
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_call:
                startActivity(new Intent(MainActivity.this, FaqActivity.class));
                break;
        }
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE) {
            showChatHead(this, false);
        }
    }

    @SuppressLint("NewApi")
    private void showChatHead(Context context, boolean isShowOverlayPermission) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            context.startService(new Intent(context, ChatHeadService.class));
            return;
        }

        if (Settings.canDrawOverlays(context)) {
            context.startService(new Intent(context, ChatHeadService.class));
            return;
        }

        if (isShowOverlayPermission) {
            final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

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
}
