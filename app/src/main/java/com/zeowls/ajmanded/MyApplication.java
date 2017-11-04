package com.zeowls.ajmanded;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.zeowls.ajmanded.utility.Localization;

import io.realm.Realm;

/**
 * Created by root on 7/24/17.
 */

public class MyApplication extends Application {
    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
    }

    public void removeUser() {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext()).edit();
        editor.remove("UserName");
        editor.remove("Password");
        editor.apply();
    }

    public void addUser(String username, String password) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext()).edit();
        editor.putString("UserName", username);
        editor.putString("Password", password);
        editor.apply();
    }

    public void toggleLocale() {
        if (Localization.getCurrentLanguageID(this) == Localization.ARABIC_VALUE){
            setLocale(Localization.ENGLISH_VALUE);
        }else {
            setLocale(Localization.ARABIC_VALUE);
        }
    }

    public void setLocale(int lang) {
        Localization.setLanguage(this, lang);
    }
}
