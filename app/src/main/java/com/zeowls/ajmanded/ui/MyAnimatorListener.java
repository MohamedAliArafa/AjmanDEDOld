package com.zeowls.ajmanded.ui;

import android.animation.Animator;
import android.view.View;

/**
 * Created by root on 5/25/17.
 */

public class MyAnimatorListener implements Animator.AnimatorListener {

    View mView;

    public MyAnimatorListener() {
    }

    public MyAnimatorListener(View mView) {
        this.mView = mView;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        mView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
