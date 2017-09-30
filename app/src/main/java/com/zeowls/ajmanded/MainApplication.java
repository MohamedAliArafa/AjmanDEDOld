package com.zeowls.ajmanded;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.zeowls.ajmanded.ui.LocaleHelper;

/**
 * Created by root on 7/24/17.
 */

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
    }
}
