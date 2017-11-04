package com.zeowls.ajmanded.screens.LoginMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zeowls.ajmanded.screens.home.HomeActivity;
import com.zeowls.ajmanded.LoginActivity;
import com.zeowls.ajmanded.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginMenuFragment extends Fragment {

    @BindView(R.id.btn_login)
    Button mLoginButton;

    @BindView(R.id.btn_guest)
    Button mGuestButton;

    @BindView(R.id.lang_container_ll)
    LinearLayout mLangContainer;

    @BindView(R.id.iv_logo)
    ImageView mLogoImage;

    @BindView(R.id.iv_logo_colored)
    ImageView mLogoColoredImage;

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
        ButterKnife.bind(this, view);
        Animation slideInUpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_up);
        Animation slideInRightAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        Animation slideInDownAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_down);
        mLangContainer.startAnimation(slideInRightAnimation);
        mLogoColoredImage.startAnimation(slideInUpAnimation);
        mLogoImage.startAnimation(slideInDownAnimation);
        mGuestButton.setOnClickListener(view1 -> {
            getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
            getActivity().finish();
        });
        mLoginButton.setOnClickListener(view1 ->{
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
        return view;
    }

}
