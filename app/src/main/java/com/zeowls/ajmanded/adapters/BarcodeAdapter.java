package com.zeowls.ajmanded.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by mohamed.arafa on 8/28/2017.
 */

public class BarcodeAdapter extends RecyclerView.Adapter<BarcodeAdapter.MyViewHolder> {

    private int mLayout;
    private Context mContext;

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public BarcodeAdapter(Context context, int layout) {
        mLayout = layout;
        mContext = context;
    }

//    public void updateData(List<NewsBarResultModel> list) {
//        mList = list;
//        this.notifyDataSetChanged();
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 25;
    }
}
