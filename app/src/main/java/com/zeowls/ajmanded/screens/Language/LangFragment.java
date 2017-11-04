package com.zeowls.ajmanded.screens.Language;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.screens.main.MainActivity;
import com.zeowls.ajmanded.utility.Localization;
import com.zeowls.ajmanded.utility.SharedTool.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LangFragment extends Fragment {

    @BindView(R.id.btn_arabic)
    Button mArabicButton;

    @BindView(R.id.btn_english)
    Button mEnglishButton;

    @BindView(R.id.lang_container_ll)
    LinearLayout mLangContainer;

    @BindView(R.id.iv_logo)
    ImageView mLogoImage;

    @BindView(R.id.iv_logo_colored)
    ImageView mLogoColoredImage;

    public LangFragment() {
        // Required empty public constructor
    }

    LangContract.UserActions mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LangPresenter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lang_menu, container, false);
        ButterKnife.bind(this, view);
        Animation slideInUpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_up);
        Animation slideInRightAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
        Animation slideInDownAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_down);
        mLangContainer.startAnimation(slideInRightAnimation);
        mLogoColoredImage.startAnimation(slideInUpAnimation);
        mLogoImage.startAnimation(slideInDownAnimation);
        mArabicButton.setOnClickListener(v -> {
            Localization.setLanguage(getContext(), Localization.ARABIC_VALUE);
            if (UserData.getUserObject(getContext()) != null)
                ((MainActivity) getActivity()).launchLanding();
            else
                ((MainActivity) getActivity()).launchLoginMenu();
        });

        mEnglishButton.setOnClickListener(v -> {
            Localization.setLanguage(getContext(), Localization.ENGLISH_VALUE);
            if (UserData.getUserObject(getContext()) != null)
                ((MainActivity) getActivity()).launchLanding();
            else
                ((MainActivity) getActivity()).launchLoginMenu();
        });
        return view;
    }

}
