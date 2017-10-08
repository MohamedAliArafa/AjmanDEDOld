package com.zeowls.ajmanded.libs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 10/5/17.
 */

public class MyPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private boolean isRTL;
    private List<Fragment> mFragments;
    private String[] fragmentsTitles;

    public MyPagerAdapter(List<Fragment> mFragments, String[] fragmentsTitles, FragmentManager fragmentManager) {
        super(fragmentManager);
        Collections.reverse(Arrays.asList(mFragments));
        Collections.reverse(Arrays.asList(fragmentsTitles));
        isRTL = Locale.getDefault().toString().contains("ar");
        this.mFragments = mFragments;
        this.fragmentsTitles = fragmentsTitles;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return mFragments.size();
    }

    // Returns the fragment to display for that page
    @Override
    public AnimatedFragment getItem(int position) {
        return (AnimatedFragment) mFragments.get(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitles[position];
    }
}