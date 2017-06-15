package com.zeowls.ajmanded;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.ajmanded.ui.AnimatedFragment;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.M;

/**
 * A placeholder fragment containing a simple view.
 */
@RequiresApi(api = M)
public class MainActivityFragment extends Fragment {

    FragmentPagerAdapter adapterViewPager;
    AnimatedFragment[] fragments;
    String[] fragmentsTitles;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager vpPager = (ViewPager) view.findViewById(R.id.pager);
        PagerTabStrip vpPagerHeader = (PagerTabStrip) view.findViewById(R.id.pager_header);

        //init the pager fragments
        fragments = new AnimatedFragment[]{OnlineServicesFragment.newInstance(0, "Page # 1"),
                AboutDEDFragment.newInstance(0, "Page # 1"),
                OnlineServicesFragment.newInstance(0, "Page # 1")};
        fragmentsTitles = new String[]{this.getString(R.string.online_services),
                this.getString(R.string.about_ded),
                this.getString(R.string.latest_news)};

        //init and set the adapter
        adapterViewPager = new MyPagerAdapter(getFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        // make the pager RTL by calling the last fragment in list
        vpPager.setCurrentItem(fragments.length - 1);

        //set the indicator color to the color of the application theme
        vpPagerHeader.setTabIndicatorColor(getActivity().getResources().getColor(R.color.bg_tab_selected));

        //styling the header
        vpPagerHeader.setPadding(0, 0, 0, 15);
        vpPagerHeader.setTextSpacing(10);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                fragments[position].startAnimation();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        boolean isRTL;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            Collections.reverse(Arrays.asList(fragments));
            Collections.reverse(Arrays.asList(fragmentsTitles));
            isRTL = Locale.getDefault().toString().contains("ar");
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return fragments.length;
        }

        // Returns the fragment to display for that page
        @Override
        public AnimatedFragment getItem(int position) {
            return fragments[position];
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitles[position];
        }
    }
}
