package com.zeowls.ajmanded.screens.splash;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

import com.google.gson.Gson;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.models.ServiceModuleModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;

/**
 * Created by root on 10/2/17.
 */

class SplashPresenter implements LifecycleObserver {

    private final Context context;
    private SplashContract.ModelView view;

    SplashPresenter(SplashContract.ModelView view, Context context, Lifecycle lifecycle) {
        this.view = view;
        this.context = context;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        InputStream raw = context.getResources().openRawResource(R.raw.e_service);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        ServiceModuleModel[] list = gson.fromJson(rd, ServiceModuleModel[].class);
        List<ServiceModuleModel> models = new ArrayList<>();
        models.addAll(Arrays.asList(list));
        Realm.getDefaultInstance().beginTransaction();
        Realm.getDefaultInstance().copyToRealmOrUpdate(models);
        Realm.getDefaultInstance().commitTransaction();
        view.startTimer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void stop() {
        view.killTimer();
    }
}
