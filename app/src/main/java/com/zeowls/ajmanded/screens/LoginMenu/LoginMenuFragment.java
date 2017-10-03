package com.zeowls.ajmanded.screens.LoginMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zeowls.ajmanded.HomeActivity;
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
        mGuestButton.setOnClickListener(view1 ->
                getActivity().startActivity(new Intent(getActivity(), HomeActivity.class)));
        mLoginButton.setOnClickListener(view1 ->
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class)));
        return view;
    }

}
