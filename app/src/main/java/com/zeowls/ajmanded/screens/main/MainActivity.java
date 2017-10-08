package com.zeowls.ajmanded.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.zeowls.ajmanded.screens.dashboard.DashBoardActivity;
import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.screens.Language.LangFragment;
import com.zeowls.ajmanded.screens.Login.LoginFragment;
import com.zeowls.ajmanded.screens.LoginMenu.LoginMenuFragment;
import com.zeowls.ajmanded.screens.splash.SplashFragment;

import static com.zeowls.ajmanded.utility.Constants.LOGIN_FRAGMENT_KEY;

public class MainActivity extends AppCompatActivity implements MainContract.ModelView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, SplashFragment.newInstance());
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.commit();
    }

    @Override
    public void launchLanding() {
        startActivity(new Intent(this, DashBoardActivity.class));
    }

    @Override
    public void launchLogin() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new LoginFragment(), LOGIN_FRAGMENT_KEY);
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void launchLoginMenu() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, LoginMenuFragment.newInstance());
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.commit();
    }

    @Override
    public void launchLanguage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new LangFragment(), LOGIN_FRAGMENT_KEY);
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void requestReadPermission() {

    }
}
