package com.zeowls.ajmanded.utility;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by root on 10/2/17.
 */

public class Helper {

    public static int calculateNoOfColumns(Context context, int itemWidth) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / itemWidth);
    }


}
