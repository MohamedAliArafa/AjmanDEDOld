package com.zeowls.ajmanded.screens.Language;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zeowls.ajmanded.MyApplication;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.screens.main.MainActivity;

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
        mArabicButton.setOnClickListener(v -> {
            mPresenter.chooseArabic();
            if (((MyApplication) getContext().getApplicationContext()).getUser() != null)
                ((MainActivity) getActivity()).launchLanding();
            else
                ((MainActivity) getActivity()).launchLoginMenu();
        });

        mEnglishButton.setOnClickListener(v -> {
            mPresenter.chooseEnglish();
            if (((MyApplication) getContext().getApplicationContext()).getUser() != null)
                ((MainActivity) getActivity()).launchLanding();
            else
                ((MainActivity) getActivity()).launchLoginMenu();
        });
        return view;
    }

}
