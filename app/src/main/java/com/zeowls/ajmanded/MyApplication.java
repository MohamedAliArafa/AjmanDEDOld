package com.zeowls.ajmanded;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.zeowls.ajmanded.libs.LocaleHelper;
import com.zeowls.ajmanded.models.UserModel;

import java.util.Locale;

/**
 * Created by root on 7/24/17.
 */

public class MyApplication extends Application {
    Locale mEnLocale;
    Locale mArLocale;
    Configuration mConfiguration;
    DisplayMetrics mDisplayMetrics;
    Resources mResources;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mResources = getResources();
        mConfiguration = mResources.getConfiguration();
        mDisplayMetrics = mResources.getDisplayMetrics();
        mEnLocale = new Locale("en");
        mArLocale = new Locale("ar");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
    }

    public UserModel getUser() {
        String username = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext())
                .getString("UserName", "-1");
        String password = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext())
                .getString("Password", "-1");
        UserModel user = null;
        if (!username.equals("-1") || !password.equals("-1")) {
            user = new UserModel();
            user.setUserName(username);
            user.setPassword(password);
        }
        return user;
    }

    public void addUser(String username, String password) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext()).edit();
        editor.putString("UserName", username);
        editor.putString("Password", password);
        editor.apply();
    }

    public void setLocale(int lang) {
        if (lang == 1) {
            Locale.setDefault(mArLocale);
            mConfiguration.setLocale(mArLocale);
            mResources.updateConfiguration(mConfiguration, mDisplayMetrics);
        } else {
            Locale.setDefault(mEnLocale);
            mConfiguration.setLocale(mEnLocale);
            mResources.updateConfiguration(mConfiguration, mDisplayMetrics);
        }
        onConfigurationChanged(mConfiguration);
        getApplicationContext().createConfigurationContext(mConfiguration);
    }
}
