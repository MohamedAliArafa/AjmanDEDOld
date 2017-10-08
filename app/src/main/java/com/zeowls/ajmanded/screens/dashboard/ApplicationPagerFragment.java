package com.zeowls.ajmanded.screens.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zeowls.ajmanded.HomeActivity;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.libs.AnimatedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationPagerFragment extends AnimatedFragment {

    View[] views;
    @BindView(R.id.frameLayout)
    FrameLayout counterFrame;

    @BindView(R.id.dash_panel)
    ConstraintLayout dashPanel;

    @BindView(R.id.menu_panel)
    LinearLayout menuPanel;

    @BindView(R.id.btn_home)
    LinearLayout mHomeBtn;

    @BindView(R.id.btn_next)
    ImageView mNextBtn;

    public ApplicationPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if (views != null)
            startAnimation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_pager, container, false);
        ButterKnife.bind(this, view);
        views = new View[]{counterFrame, dashPanel, menuPanel};
        mHomeBtn.setOnClickListener(view1 -> {
            getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
            getActivity().finish();
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DashboardFragment fragment = ((DashboardFragment) getParentFragment());
        mNextBtn.setOnClickListener(view1 -> {
            fragment.moveNext();
        });
        startAnimation();
    }

    @Override
    public void startAnimation() {
        new AnimatedFragment.animate(views, R.anim.expand_in).execute();
    }

}
