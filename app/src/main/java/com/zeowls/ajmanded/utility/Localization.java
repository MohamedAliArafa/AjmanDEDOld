package com.zeowls.ajmanded.utility;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;

import com.zeowls.ajmanded.screens.home.HomeActivity;
import com.zeowls.ajmanded.utility.SharedTool.UserData;

import java.util.Locale;

/**
 * Created by halima.reda on 3/12/2016.
 */
public class Localization {

    public static final int ARABIC_VALUE = 1;
    public static final int ENGLISH_VALUE = 2;

    public static void setLanguage(Context context, int langaugeId) {
        if (langaugeId == ARABIC_VALUE) {
            //arabic
            changeLocale(context, new Locale("ar"), Localization.ARABIC_VALUE);
        } else if (langaugeId == ENGLISH_VALUE) {
            //english
            changeLocale(context, new Locale("en"), Localization.ENGLISH_VALUE);
        }
        UserData.saveLocalization(context, langaugeId);
    }

    private static void changeLocale(Context context, Locale locale, int langaugeId) {
        Configuration conf = context.getResources().getConfiguration();
        conf.locale = locale;
        Locale.setDefault(locale);
        UserData.saveLocalization(context, langaugeId);
        conf.setLayoutDirection(conf.locale);
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
    }

    public static int getDefaultLocal(Context context) {
        Configuration config = context.getResources().getConfiguration();
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            Log.e("LayoutDirection", "RTL");
            return ARABIC_VALUE;
        } else {
            Log.e("LayoutDirection", "LTR");
            return ENGLISH_VALUE;
        }
    }

    public static void changeLanguage(String language, Context context) {

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    // return current Language ID
    public static int getCurrentLanguageID(Context context) {
        /*
        0 = arabic
         1 = english */
        int languageID = UserData.getLocalization(context);
        Log.i("languageID", "" + languageID);
        return languageID;
    }
}
