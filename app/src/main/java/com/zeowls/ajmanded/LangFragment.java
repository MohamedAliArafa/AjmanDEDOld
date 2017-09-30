package com.zeowls.ajmanded;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rd.PageIndicatorView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LangFragment extends Fragment {


    public LangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lang_menu, container, false);
        ViewPager viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(new LangPagerAdapter(this, getFragmentManager()));
        PageIndicatorView pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
        return view;
    }

}
