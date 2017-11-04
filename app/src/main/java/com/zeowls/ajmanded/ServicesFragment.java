package com.zeowls.ajmanded;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.ajmanded.adapters.ServiceNestedAdapter;
import com.zeowls.ajmanded.libs.AnimatedFragment;
import com.zeowls.ajmanded.models.ServiceModuleModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends AnimatedFragment {

    @BindView(R.id.service_title_recycler)
    RecyclerView mRecycler;
    private ServiceNestedAdapter mAdapter;

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        ButterKnife.bind(this, view);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ServiceModuleModel> models = Realm.getDefaultInstance().where(ServiceModuleModel.class).findAll();
        mAdapter = new ServiceNestedAdapter(getContext(), models, R.layout.list_item_service_parent);
        mRecycler.setAdapter(mAdapter);
        return view;
    }
}
