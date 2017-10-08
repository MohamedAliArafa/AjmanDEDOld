package com.zeowls.ajmanded.screens.dashboard;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booking.rtlviewpager.RtlViewPager;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.libs.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements DashInterface {

    @BindView(R.id.vp_dashboard)
    RtlViewPager vpPager;

    List<Fragment> mFragments;
    String[] fragmentsTitles;
    PagerAdapter adapterViewPager;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragments = new ArrayList<>();
        mFragments.add(new ApplicationPagerFragment());
        mFragments.add(new LicensePagerFragment());
        mFragments.add(new PermitPagerFragment());
        fragmentsTitles = new String[]{context.getString(R.string.online_services),
                context.getString(R.string.about_ded),
                context.getString(R.string.latest_news)};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        //init and set the adapter
        adapterViewPager = new MyPagerAdapter(mFragments, fragmentsTitles, getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        return view;
    }

    @Override
    public void moveNext() {
        if (vpPager.getCurrentItem() < 2)
            vpPager.setCurrentItem(vpPager.getCurrentItem() + 1);
    }

    @Override
    public void movePrev() {
        if (vpPager.getCurrentItem() > 0)
            vpPager.setCurrentItem(vpPager.getCurrentItem() - 1);
    }
}
