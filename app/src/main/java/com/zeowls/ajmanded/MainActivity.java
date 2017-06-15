package com.zeowls.ajmanded;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private ImageView mNotiImageView;
    float dX;
    float dY;
    int lastAction;
    private float oldX;
    private float oldY;
    private long startTime;
    private long timerTime;
    private boolean isRTL;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mNotiImageView = (ImageView) findViewById(R.id.noti_image_view);
        mNotiImageView.setOnTouchListener(this);
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

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRTL = Locale.getDefault().toString().contains("ar");
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    @Override
    public boolean onTouch(final View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                oldX = event.getX();
                oldY = event.getY();
                startTime = System.currentTimeMillis();
                view.animate().setInterpolator(new BounceInterpolator()).scaleX(1.5f).start();
                view.animate().setInterpolator(new BounceInterpolator()).scaleY(1.5f).start();
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP:
                ViewGroup parent = (ViewGroup) view.getParent();
                if (lastAction == MotionEvent.ACTION_DOWN)
                    Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                int parentWidth = parent.getWidth();
                int viewWidth = view.getWidth();
                float distance;
                if (isRTL){
                    distance = parentWidth - view.getRight();
                }else {
                    distance = parentWidth - view.getLeft();
                }

                //long timerTime = getTime between two event down to Up
                float newX = event.getX();
                float newY = event.getY();

                timerTime = System.currentTimeMillis() - startTime;
                float distance2 = (float) Math.sqrt((newX-oldX) * (newX-oldX) + (newY-oldY) * (newY-oldY));
                view.animate().setInterpolator(new BounceInterpolator()).scaleX(1.0f).start();
                view.animate().setInterpolator(new BounceInterpolator()).scaleY(1.0f).start();

                if (isRTL){
                    view.animate().setInterpolator(new OvershootInterpolator()).translationX((viewWidth * .1f)).withLayer();
                } else {
                    view.animate().setInterpolator(new OvershootInterpolator()).translationX(distance - (viewWidth * 1.2f)).withLayer();
                }
                break;
            default:
                return false;
        }
        return true;
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
                if (locale.toString().toLowerCase().equals("ar")){
                    Locale.setDefault(new Locale("en"));

                    Configuration conf = new Configuration();
                    conf.locale = Locale.getDefault();

                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    res.updateConfiguration(conf, dm);
                    onConfigurationChanged(conf);
                }else {
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
}
