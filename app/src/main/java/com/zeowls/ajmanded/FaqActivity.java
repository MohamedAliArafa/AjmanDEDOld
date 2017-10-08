package com.zeowls.ajmanded;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.zeowls.ajmanded.adapters.CustomExpandableListAdapter;
import com.zeowls.ajmanded.adapters.ExpandableListDataSource;
import com.zeowls.ajmanded.libs.ChatSplash.Message;
import com.zeowls.ajmanded.libs.ChatSplash.Option;
import com.zeowls.ajmanded.libs.ChatSplash.ResultModel;
import com.zeowls.ajmanded.libs.ChatSplash.SplashChatAdapter;
import com.zeowls.ajmanded.screens.dashboard.DashBoardActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class FaqActivity extends AppCompatActivity {

    RecyclerView rv;
    LinearLayout linearLayout;
    SplashChatAdapter adapter;
    ResultModel[] mModel;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListData;
    private SlidingRootNav mSlidingRootNav;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        mToolbar = findViewById(R.id.toolbar);
//        initWebView();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        rv = findViewById(R.id.recycler_view);
        linearLayout = findViewById(R.id.linearLayout);
        adapter = new SplashChatAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new SlideInUpAnimator());
        rv.setAdapter(adapter);

        InputStream raw = getResources().openRawResource(R.raw.json);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        mModel = gson.fromJson(rd, ResultModel[].class);
        loadModel(0);

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(mToolbar)
                .withMenuLayout(R.layout.sliding_root_layout)
                .inject();

//        mDrawerLayout = findViewById(R.id.drawer_layout);
        mExpandableListView = findViewById(R.id.left_drawer);
        View listHeaderView = getLayoutInflater().inflate(R.layout.list_item_drawer_header, null, false);
        View listFooterView = getLayoutInflater().inflate(R.layout.list_item_drawer_footer, null, false);

        listHeaderView.findViewById(R.id.dash_btn).setOnClickListener(view -> {
            startActivity(new Intent(FaqActivity.this, DashBoardActivity.class));
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
            startActivity(new Intent(FaqActivity.this, HomeActivity.class));
            mSlidingRootNav.closeMenu(true);
        });

        listFooterView.findViewById(R.id.logout_text).setOnClickListener(view -> {
            startActivity(new Intent(FaqActivity.this, LoginActivity.class));
            finish();
            mSlidingRootNav.closeMenu(true);
            MyApplication.get(this).removeUser();
        });

        listFooterView.findViewById(R.id.lang_text).setOnClickListener(view -> {
            MyApplication.get(this).toggleLocale();
            restartActivity();
            mSlidingRootNav.closeMenu(true);
        });

        listHeaderView.findViewById(R.id.help_btn).setOnClickListener(view -> {
            startActivity(new Intent(FaqActivity.this, FaqActivity.class));
            finish();
            mSlidingRootNav.closeMenu(true);
            MyApplication.get(this).removeUser();
        });

        mExpandableListView.addHeaderView(listHeaderView);
        mExpandableListView.addFooterView(listFooterView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        addDrawerItems();
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
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    void loadModel(int modelID) {

        if (modelID == -1) {
            startActivity(new Intent(this, HomeActivity.class));
            return;
        }

        Handler handler = new Handler();
        final List<Message> messages = mModel[modelID].getMessages();
        for (int i = 0; i < messages.size() * 2; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalI % 2 == 0) {
                        adapter.add(adapter.getItemCount(), "...", null, false);
                    } else {
                        adapter.delete(adapter.getItemCount() - 1);
                        adapter.add(adapter.getItemCount(), messages.get(finalI / 2).getText(), null, false);
                    }
                    rv.scrollToPosition(adapter.getItemCount() - 1);
                }
            }, 1000 * i);
        }

        final List<Option> options = mModel[modelID].getOptions();
        final TextView[] textViews = new TextView[options.size()];
        for (int i = 0; i < options.size(); i++) {
            TextView newTextView = new TextView(this);
            newTextView.setText(options.get(i).getMessage());
            newTextView.setId(View.generateViewId());
            newTextView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            newTextView.setBackgroundResource(R.drawable.bg_rounded_left);
            newTextView.setTextColor(getResources().getColor(android.R.color.black));
            float scale = getResources().getDisplayMetrics().density;
            int dp = (int) (10 * scale + 0.5f);
            newTextView.setPadding(dp, dp, dp, dp);

            final int finalI = i;
            newTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.add(adapter.getItemCount(), options.get(finalI).getMessage(), null, true);
                    rv.scrollToPosition(adapter.getItemCount() - 1);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fad_in_out);
                    view.startAnimation(animation);
                    linearLayout.removeAllViews();
                    loadModel(options.get(finalI).getCode());
                }
            });

            textViews[i] = newTextView;
        }

        handler.postDelayed(new Runnable() {
            public void run() {
                for (int i = 0; i < options.size(); i++) {
                    linearLayout.addView(textViews[i]);
                }
            }
        }, 1000 * messages.size() * 2);
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
//                startActivity(new Intent(DashBoardActivity.this, FaqActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
