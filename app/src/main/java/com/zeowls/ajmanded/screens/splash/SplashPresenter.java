package com.zeowls.ajmanded.screens.splash;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Created by root on 10/2/17.
 */

class SplashPresenter implements LifecycleObserver {

    private SplashContract.ModelView view;

    SplashPresenter(SplashContract.ModelView view, Lifecycle lifecycle) {
        this.view = view;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        view.startTimer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void stop() {
        view.killTimer();
    }
}
