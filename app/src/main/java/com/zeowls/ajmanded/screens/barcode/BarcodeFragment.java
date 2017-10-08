package com.zeowls.ajmanded.screens.barcode;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.adapters.BarcodeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarcodeFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecycler;
    private BarcodeAdapter mAdapter;

    public BarcodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);
        ButterKnife.bind(this, view);
        mAdapter = new BarcodeAdapter(getContext(), R.layout.list_item_barcode);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}
