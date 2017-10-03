package com.zeowls.ajmanded.adapters;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.ajmanded.LoginMenuActivity;
import com.zeowls.ajmanded.MyApplication;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.screens.Language.LangFragment;

import java.util.Locale;

/*
 * Created by root on 9/29/17.
 */

public class LangPagerAdapter extends PagerAdapter {
    private FragmentManager mFragmentManager;
    private LangFragment mContext;

    public LangPagerAdapter(LangFragment context, FragmentManager fragmentManager) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mResources = mContext.getResources();
        mConfiguration = mResources.getConfiguration();
        mDisplayMetrics = mResources.getDisplayMetrics();
        mEnLocale = new Locale("en");
        mArLocale = new Locale("ar");
    }

    private Locale mEnLocale;
    private Locale mArLocale;
    private Configuration mConfiguration;
    private DisplayMetrics mDisplayMetrics;
    private Resources mResources;

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext.getContext());
        ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
        layout.findViewById(R.id.lang_btn).setOnClickListener(view -> {
            if (position == 1) {
                Locale.setDefault(mArLocale);
                mConfiguration.setLocale(mArLocale);

            } else {
                Locale.setDefault(mEnLocale);
                mConfiguration.setLocale(mEnLocale);
            }
            mResources.updateConfiguration(mConfiguration, mDisplayMetrics);
            ((MyApplication) mContext.getContext().getApplicationContext()).onConfigurationChanged(mConfiguration);
            mContext.getContext().getApplicationContext().createConfigurationContext(mConfiguration);
            mContext.getActivity().startActivity(new Intent(mContext.getActivity(), LoginMenuActivity.class));
            mContext.getActivity().finish();
        });
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }

    enum CustomPagerEnum {

        EN(R.string.english, R.layout.lang_en_view_item),
        AR(R.string.arabic, R.layout.lang_ar_view_item);

        private int mTitleResId;
        private int mLayoutResId;

        CustomPagerEnum(int titleResId, int layoutResId) {
            mTitleResId = titleResId;
            mLayoutResId = layoutResId;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public int getLayoutResId() {
            return mLayoutResId;
        }

    }


}
