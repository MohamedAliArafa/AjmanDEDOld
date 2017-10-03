package com.zeowls.ajmanded;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.zeowls.ajmanded.libs.AnimatedFragment;
import com.zeowls.ajmanded.models.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.ajmanded.utility.Constants.URL_INTENT_KEY;
import static com.zeowls.ajmanded.utility.Constants.links.BUSINESS_ACTIVITY_EXTERNAL_APPROVAL;
import static com.zeowls.ajmanded.utility.Constants.links.CERTIFICATE_REQUEST;
import static com.zeowls.ajmanded.utility.Constants.links.CHECK_INSPECTION_STATUS;
import static com.zeowls.ajmanded.utility.Constants.links.DOMAIN_EN;
import static com.zeowls.ajmanded.utility.Constants.links.ESTIMATE_NEW_PERMIT_FEES;
import static com.zeowls.ajmanded.utility.Constants.links.INITIAL_APPROVAL;
import static com.zeowls.ajmanded.utility.Constants.links.ISSUE_NEW_PERMIT;
import static com.zeowls.ajmanded.utility.Constants.links.LINK_EXTERNAL_LICENSE;
import static com.zeowls.ajmanded.utility.Constants.links.MATCHED_ACTIVITIES;
import static com.zeowls.ajmanded.utility.Constants.links.PERMIT_TERMS_AND_CONDITIONS;
import static com.zeowls.ajmanded.utility.Constants.links.RENEW_TRADE_LICENSE;
import static com.zeowls.ajmanded.utility.Constants.links.TRADE_LICENSE_ISSAUASE;
import static com.zeowls.ajmanded.utility.Constants.links.TRADE_NAME_RESERVATION;


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

    @BindView(R.id.main_scroll)
    ScrollView scrollView;
    @BindView(R.id.inquiries_card)
    CardView inquiries_card;
    @BindView(R.id.e_services_card)
    CardView e_services_card;
    @BindView(R.id.transation_status_card)
    CardView transation_status_card;
    @BindView(R.id.reports_card)
    CardView reports_card;
    @BindView(R.id.other_card)
    CardView other_card;

    @BindView(R.id.trade_name_avaliability_btn)
    RelativeLayout tradeNameAvailabilityBtn;
    @BindView(R.id.check_application_btn)
    RelativeLayout checkAppStatusBtn;
    @BindView(R.id.check_inspection_btn)
    RelativeLayout checkInsStatusBtn;
    @BindView(R.id.matched_activities_btn)
    RelativeLayout matchedActivitesBtn;
    @BindView(R.id.business_approval_btn)
    RelativeLayout businessActiviteExternalApprovalBtn;
    @BindView(R.id.estimate_permit_fee_btn)
    RelativeLayout estPermitFeeBtn;
    @BindView(R.id.pemit_terms_btn)
    RelativeLayout permitTermsBtn;
    @BindView(R.id.estimate_license_fees_btn)
    RelativeLayout estRenewLicenseFeeBtn;
    @BindView(R.id.trade_license_inqury_btn)
    RelativeLayout tradeLicenseInquiryBtn;

    @BindView(R.id.trade_name_reserv_btn)
    RelativeLayout tradeNameReservBtn;
    @BindView(R.id.intial_approval_btn)
    RelativeLayout initApprovalBtn;
    @BindView(R.id.new_license_btn)
    RelativeLayout newLicenseBtn;
    @BindView(R.id.renew_license_btn)
    RelativeLayout renewLicenseBtn;
    @BindView(R.id.link_external_btn)
    RelativeLayout linkExternalBtn;
    @BindView(R.id.new_permit_btn)
    RelativeLayout newPermitBtn;
    @BindView(R.id.new_certificate_btn)
    RelativeLayout newCertificateBtn;
    @BindView(R.id.print_license_btn)
    RelativeLayout printLicenseBtn;
    @BindView(R.id.link_owner_btn)
    RelativeLayout linkOwnerBtn;

    @BindView(R.id.transation_process_btn)
    RelativeLayout transInProcessBtn;
    @BindView(R.id.all_transaction_btn)
    RelativeLayout allTransBtn;
    @BindView(R.id.transaction_review_btn)
    RelativeLayout transUnderReviewBtn;
    @BindView(R.id.completed_transations_btn)
    RelativeLayout completedTransBtn;

    @BindView(R.id.list_of_licenses_btn)
    RelativeLayout listLicensesBtn;
    @BindView(R.id.valid_permits_btn)
    RelativeLayout validPermitReportBtn;
    @BindView(R.id.expired_permits_btn)
    RelativeLayout expiredPermitReportBtn;

    @BindView(R.id.external_approval_btn)
    RelativeLayout externalApprovalBtn;
    @BindView(R.id.transaction_status_btn)
    RelativeLayout tranStatusBtn;
    @BindView(R.id.business_name_inquiry_btn)
    RelativeLayout businessNameBtn;
    @BindView(R.id.license_information_inquiry_btn)
    RelativeLayout licenseInfoBtn;
    @BindView(R.id.estimate_license_fee_btn)
    RelativeLayout estLicenseFeeBtn;

    View[] views;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_services, container, false);
        ButterKnife.bind(this, view);

        initAnonymousServices();
        initAuthenticatedServices();

        inquiries_card.setVisibility(View.GONE);
        e_services_card.setVisibility(View.GONE);
        transation_status_card.setVisibility(View.GONE);
        reports_card.setVisibility(View.GONE);
        other_card.setVisibility(View.GONE);

        UserModel userModel = MyApplication.get(getContext()).getUser();
        if (userModel == null) {
            views = new View[]{inquiries_card};
        } else {
            views = new View[]{e_services_card,
                    transation_status_card, other_card, reports_card};
        }

        return view;
    }

    private void initAuthenticatedServices(){
        tradeNameReservBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + TRADE_NAME_RESERVATION);
            startActivity(in);
        });

        initApprovalBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + INITIAL_APPROVAL);
            startActivity(in);
        });

        newLicenseBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + TRADE_LICENSE_ISSAUASE);
            startActivity(in);
        });

        renewLicenseBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + RENEW_TRADE_LICENSE);
            startActivity(in);
        });

        newPermitBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + ISSUE_NEW_PERMIT);
            startActivity(in);
        });

        linkExternalBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + LINK_EXTERNAL_LICENSE);
            startActivity(in);
        });

        newCertificateBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + CERTIFICATE_REQUEST);
            startActivity(in);
        });
    }

    private void initAnonymousServices(){
        tradeNameAvailabilityBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + TRADE_NAME_RESERVATION);
            startActivity(in);
        });

        checkAppStatusBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + CHECK_INSPECTION_STATUS);
            startActivity(in);
        });

        checkInsStatusBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + CHECK_INSPECTION_STATUS);
            startActivity(in);
        });

        matchedActivitesBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + MATCHED_ACTIVITIES);
            startActivity(in);
        });


        businessActiviteExternalApprovalBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + BUSINESS_ACTIVITY_EXTERNAL_APPROVAL);
            startActivity(in);
        });


        estPermitFeeBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + ESTIMATE_NEW_PERMIT_FEES);
            startActivity(in);
        });

        permitTermsBtn.setOnClickListener(view1 -> {
            Intent in = new Intent(getActivity(), WebViewActivity.class);
            in.putExtra(URL_INTENT_KEY, DOMAIN_EN + PERMIT_TERMS_AND_CONDITIONS);
            startActivity(in);
        });
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
