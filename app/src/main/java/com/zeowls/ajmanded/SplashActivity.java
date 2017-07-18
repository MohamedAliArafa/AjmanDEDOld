package com.zeowls.ajmanded;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final LinearLayout container = (LinearLayout) findViewById(R.id.chat_container);
        final ImageView avatar = (ImageView) findViewById(R.id.avatar);
        final LayoutInflater li = LayoutInflater.from(this);

        Resources r = getResources();
        final float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 66, r.getDisplayMetrics());

        long delayBetweenAnimations = 1000L;
        for (int i = 0; i <= 5; i++) {
            int delay = (int) (i * delayBetweenAnimations);
            final int finalI = i;
            container.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TextView chat_item = (TextView) li.inflate(R.layout.chat_item, container, false);
                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                    container.addView(chat_item);
                    chat_item.startAnimation(animation);
                    float y = avatar.getY();
                    if (finalI !=0) {
                        avatar.setPadding(16, 0, 0, (int) (avatar.getPaddingBottom() + px));
                        avatar.setPadding(16, 0, 0, (int) (avatar.getPaddingBottom() - px));
                    }
//                    avatar.setTranslationY(-px);
                }
            }, delay);
            if (finalI == 5){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

    }

    public class animate extends AsyncTask {

        View[] views;

        public animate(View[] views) {
            this.views = views;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            for (View view : views) {
                view.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected Void doInBackground(Object[] params) {
            long delayBetweenAnimations = 200L;
            for (int i = 0; i < views.length; i++) {
                final View view = views[i];
                // We calculate the delay for this Animation, each animation starts 100ms
                // after the previous one
                int delay = (int) (i * delayBetweenAnimations);
                try {
                    switch (i) {
                        case 0:
                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(animation);
                                }
                            }, delay);
                        case 1:
                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(animation);
                                }
                            }, delay);
                        case 2:
                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_up);
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(animation);
                                }
                            }, delay);
                        case 3:
                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(animation);
                                }
                            }, delay);
                        case 4:
                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(animation);
                                }
                            }, delay);
                        case 5:
                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(animation);
                                }
                            }, delay);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }
}
