package com.zeowls.ajmanded;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zeowls.ajmanded.ui.CircularAction.FloatingActionButton;
import com.zeowls.ajmanded.ui.CircularAction.FloatingActionMenu;
import com.zeowls.ajmanded.ui.CircularAction.SubActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    float dX;
    float dY;
    int lastAction;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private ImageView mNotiImageView;
    private float oldX;
    private float oldY;
    private long startTime;
    private boolean isRTL;

    private Toolbar mToolbar;
    private ImageView mLogo;
    private AppBarLayout mAppBar;


    private static final int CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLogo = (ImageView) findViewById(R.id.logo);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_socialmediaicon));
        FloatingActionButton fab = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew)
                .build();
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


//    @Override
//    public boolean onTouch(final View view, MotionEvent event) {
//        switch (event.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                dX = view.getX() - event.getRawX();
//                dY = view.getY() - event.getRawY();
//                lastAction = MotionEvent.ACTION_DOWN;
//                oldX = event.getX();
//                oldY = event.getY();
//                startTime = System.currentTimeMillis();
//                view.animate().setInterpolator(new BounceInterpolator()).scaleX(1.5f).start();
//                view.animate().setInterpolator(new BounceInterpolator()).scaleY(1.5f).start();
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                view.setY(event.getRawY() + dY);
//                view.setX(event.getRawX() + dX);
//                lastAction = MotionEvent.ACTION_MOVE;
//                break;
//
//            case MotionEvent.ACTION_UP:
//                ViewGroup parent = (ViewGroup) view.getParent();
//                if (lastAction == MotionEvent.ACTION_DOWN)
//                    Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
//                int parentWidth = parent.getWidth();
//                int viewWidth = view.getWidth();
//                float distance;
//                if (isRTL) {
//                    distance = parentWidth - view.getRight();
//                } else {
//                    distance = parentWidth - view.getLeft();
//                }
//
//                view.animate().setInterpolator(new BounceInterpolator()).scaleX(1.0f).start();
//                view.animate().setInterpolator(new BounceInterpolator()).scaleY(1.0f).start();
//
//                if (isRTL) {
//                    view.animate().setInterpolator(new OvershootInterpolator()).translationX((viewWidth * .1f)).withLayer();
//                } else {
//                    view.animate().setInterpolator(new OvershootInterpolator()).translationX(distance - (viewWidth * 1.2f)).withLayer();
//                }
//                break;
//            default:
//                return false;
//        }
//        return true;
//    }

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
    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem action_lang = menu.findItem(R.id.action_lang);
        if (isRTL)
            action_lang.setIcon(getResources().getDrawable(R.drawable.top_icon2));
        else
            action_lang.setIcon(getResources().getDrawable(R.drawable.top_icon1));
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

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_lang:
                Locale locale = Locale.getDefault();
                if (locale.toString().toLowerCase().equals("ar")) {
                    Locale.setDefault(new Locale("en"));

                    Configuration conf = new Configuration();
                    conf.locale = Locale.getDefault();

                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    res.updateConfiguration(conf, dm);
                    onConfigurationChanged(conf);
                } else {
                    Locale.setDefault(new Locale("ar"));

                    Configuration conf = new Configuration();
                    conf.locale = Locale.getDefault();

                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    res.updateConfiguration(conf, dm);
                    onConfigurationChanged(conf);
                }
                return true;
        }

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
            final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
            startActivityForResult(intent, CHATHEAD_OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }
}
