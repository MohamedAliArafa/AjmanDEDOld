package com.zeowls.ajmanded;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{

    DisplayMetrics dm;
    float px;
    int statusBarOffset;
    int originalPos[] = new int[2];
    LinearLayout container;
    ImageView avatar;
    LayoutInflater li;
    Resources r;
    TranslateAnimation anim;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        container = (LinearLayout) findViewById(R.id.chat_container);
        avatar = (ImageView) findViewById(R.id.avatar);
        li = LayoutInflater.from(this);

        r = getResources();
        px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 68, r.getDisplayMetrics());
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        statusBarOffset = dm.heightPixels - container.getMeasuredHeight();
        avatar.getLocationOnScreen(originalPos);

        long delayBetweenAnimations = 1000L;
        for (int i = 0; i < 5; i++) {
            int delay = (int) (i * delayBetweenAnimations);
            final int finalI = i;
            container.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TextView chat_item = (TextView) li.inflate(R.layout.chat_item, container, false);
                    animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_in_right);
                    animation.setAnimationListener(SplashActivity.this);
                    container.addView(chat_item);
                    chat_item.startAnimation(animation);
                }
            }, delay);
        }
//        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }

    void avatarAnim(){
        int yDest = (int) (dm.heightPixels - (avatar.getMeasuredHeight() / 2) - statusBarOffset - px);
        anim = new TranslateAnimation(0, 0, 0, yDest - originalPos[1]);
        anim.setDuration(500);
        avatar.startAnimation(anim);
        anim.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == anim){
            int yDest = (int) (dm.heightPixels - (avatar.getMeasuredHeight() / 2) - statusBarOffset - px);
            TranslateAnimation anim1 = new TranslateAnimation(0, 0, 0, yDest + originalPos[1]);
            anim1.setDuration(1000);
            avatar.startAnimation(anim1);
        }else if (animation == this.animation){
            avatarAnim();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
