package com.zeowls.ajmanded;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Runnable mRunnable;
    private Handler mHandler;
    private long SPLASH_DISPLAY_LENGTH = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);
        startTimer();
    }
    public void startTimer() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                startActivity(new Intent(SplashActivity.this, WelcomeActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        };
        mHandler = new Handler();

        mHandler.postDelayed(mRunnable, SPLASH_DISPLAY_LENGTH);
    }
}
