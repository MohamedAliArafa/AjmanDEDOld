package com.zeowls.ajmanded;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.zeowls.ajmanded.libs.AnimatedFragment;
import com.zeowls.ajmanded.models.UserModel;


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
    CardView inquiries_card;
    CardView e_services_card;
    LinearLayout cardView3;
    CardView transation_status_card;
    CardView reports_card;
    CardView other_card;
    CardView invest_card;

    RelativeLayout newLicenseBtn;
    RelativeLayout renewLicenseBtn;
    RelativeLayout tradeNameReservBtn;
    RelativeLayout newPermitBtn;
    RelativeLayout licenseInfoBtn;
    RelativeLayout businessNameBtn;
    RelativeLayout tranStatusBtn;
    RelativeLayout estLicenseFeeBtn;

    View[] views;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_services, container, false);

        scrollView = view.findViewById(R.id.main_scroll);
        inquiries_card = view.findViewById(R.id.inquiries_card);
        e_services_card = view.findViewById(R.id.e_services_card);
        transation_status_card = view.findViewById(R.id.transation_status_card);
        reports_card = view.findViewById(R.id.reports_card);
        other_card = view.findViewById(R.id.other_card);
        cardView3 = view.findViewById(R.id.card3);
        invest_card = view.findViewById(R.id.card5);

        newLicenseBtn = view.findViewById(R.id.new_license_btn);
        renewLicenseBtn = view.findViewById(R.id.renew_license_btn);
        tradeNameReservBtn = view.findViewById(R.id.trade_name_reserv_btn);
        newPermitBtn = view.findViewById(R.id.new_permit_btn);
        licenseInfoBtn = view.findViewById(R.id.license_information_inquiry_btn);
        businessNameBtn = view.findViewById(R.id.business_name_inquiry_btn);
        tranStatusBtn = view.findViewById(R.id.transaction_status_btn);
        estLicenseFeeBtn = view.findViewById(R.id.estimate_license_fee_btn);

        newLicenseBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
            startActivity(in);
        });


        newLicenseBtn.setOnClickListener(view12 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
            startActivity(in);
        });
        renewLicenseBtn.setOnClickListener(view13 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=1043782d-9d2b-403c-bfe0-9fbae0aa20e8&formName=OnlineForm_Step1&formType=createbyform&lang=en");
            startActivity(in);
        });
        tradeNameReservBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=e65a3e12-691f-448e-9d98-cacf4ba6778b&formName=OnlineForm_Step1&formType=createbyform&lang=en");
                startActivity(in);
            }
        });
        newPermitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
                startActivity(in);
            }
        });
        licenseInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
                startActivity(in);
            }
        });
        businessNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
                startActivity(in);
            }
        });
        tranStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
                startActivity(in);
            }
        });
        estLicenseFeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", "http://Site3.ajmanded.ae/extensions/OnlineServices/MobileLandingPage?defid=3d79ac09-9cf1-49cc-9fb0-f17f6ca3029e&formName=OnlineForm_Step1&formType=createbyform&lang=en");
                startActivity(in);
            }
        });


        inquiries_card.setVisibility(View.GONE);
        e_services_card.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        transation_status_card.setVisibility(View.GONE);
        reports_card.setVisibility(View.GONE);
        other_card.setVisibility(View.GONE);

        UserModel userModel = MyApplication.get(getContext()).getUser();
        if (userModel == null) {
            views = new View[]{inquiries_card, cardView3, invest_card};
        }else {
            views = new View[]{e_services_card, cardView3,
                    transation_status_card, other_card, reports_card, invest_card};
        }

        return view;
    }

    @Override
    public void startAnimation() {
        new AnimatedFragment.animate(views).execute();
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
