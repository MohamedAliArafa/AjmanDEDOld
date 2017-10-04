package com.zeowls.ajmanded.screens.splash;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.screens.main.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment implements SplashContract.ModelView {

    private static final long SPLASH_DISPLAY_LENGTH = 0;
    SplashPresenter splashPresenter;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashPresenter = new SplashPresenter(this, getLifecycle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    Runnable mRunnable;
    Handler mHandler;

    @Override
    public void startTimer() {
        mRunnable = () -> {
            /* Create an Intent that will start the Menu-Activity. */
            ((MainActivity) getActivity()).launchLanguage();
        };
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void killTimer() {
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

}
