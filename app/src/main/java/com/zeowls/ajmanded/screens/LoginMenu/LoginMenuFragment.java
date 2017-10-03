package com.zeowls.ajmanded.screens.LoginMenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rd.PageIndicatorView;
import com.zeowls.ajmanded.adapters.LoginPagerAdapter;
import com.zeowls.ajmanded.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginMenuFragment extends Fragment {


    public LoginMenuFragment() {
        // Required empty public constructor
    }

    public static LoginMenuFragment newInstance() {
        return new LoginMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_menu, container, false);
        ViewPager viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(new LoginPagerAdapter(this, getFragmentManager()));
        PageIndicatorView pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
        return view;
    }

}
