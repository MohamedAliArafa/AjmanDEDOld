package com.zeowls.ajmanded.screens.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rd.PageIndicatorView;
import com.zeowls.ajmanded.AboutDEDFragment;
import com.zeowls.ajmanded.HomeTabFragment;
import com.zeowls.ajmanded.OnlineServicesFragment;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.libs.AnimatedFragment;
import com.zeowls.ajmanded.libs.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    FragmentPagerAdapter adapterViewPager;
    List<Fragment> mFragments;
    String[] fragmentsTitles;

    @BindView(R.id.pager)
    ViewPager vpPager;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView mPageIndicatorView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeTabFragment());
        mFragments.add(new AboutDEDFragment());
        mFragments.add(new OnlineServicesFragment());
        fragmentsTitles = new String[]{context.getString(R.string.online_services),
                context.getString(R.string.about_ded),
                context.getString(R.string.latest_news)};
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterViewPager.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        //init and set the adapter
        adapterViewPager = new MyPagerAdapter(mFragments, fragmentsTitles, getFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        mPageIndicatorView.setViewPager(vpPager);
        // make the pager RTL by calling the last fragment in list
//        vpPager.setCurrentItem(mFragments.size() - 1);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                ((AnimatedFragment) mFragments.get(position)).startAnimation();
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
        return view;
    }

}
