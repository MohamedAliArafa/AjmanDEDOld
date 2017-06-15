package com.zeowls.ajmanded;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zeowls.ajmanded.ui.AnimatedFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineServicesFragment extends AnimatedFragment {

    public OnlineServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    ScrollView scrollView;
    CardView cardView;
    CardView cardView2;
    LinearLayout cardView3;
    CardView cardView5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_services, container, false);
        scrollView = (ScrollView) view.findViewById(R.id.main_scroll);
        cardView = (CardView) view.findViewById(R.id.card1);
        cardView2 = (CardView) view.findViewById(R.id.card2);
        cardView3 = (LinearLayout) view.findViewById(R.id.card3);
        cardView5 = (CardView) view.findViewById(R.id.card5);

        cardView.setVisibility(View.INVISIBLE);
        cardView2.setVisibility(View.INVISIBLE);
        cardView3.setVisibility(View.INVISIBLE);
        cardView5.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void startAnimation() {
        new AnimatedFragment.animate(new View[]{cardView, cardView2, cardView3, cardView5}).execute();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAnimation();
    }

    public static AnimatedFragment newInstance(int i, String s) {
        return new OnlineServicesFragment();
    }
}
