package com.zeowls.ajmanded.screens.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.libs.AnimatedFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutDEDFragment extends AnimatedFragment {

    CardView cardView;
    CardView cardView1;
    CardView cardView2;

    public AboutDEDFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_ded, container, false);
        cardView = (CardView) view.findViewById(R.id.card);
        cardView1 = (CardView) view.findViewById(R.id.card1);
        cardView2 = (CardView) view.findViewById(R.id.card2);

        cardView.setVisibility(View.INVISIBLE);
        cardView1.setVisibility(View.INVISIBLE);
        cardView2.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAnimation();
    }

    @Override
    public void startAnimation(){
        new AnimatedFragment.animate(new View[] { cardView, cardView1, cardView2 }).execute();
    }
}
