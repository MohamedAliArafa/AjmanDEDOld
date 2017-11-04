package com.zeowls.ajmanded.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.models.ServiceModuleModel;
import com.zeowls.ajmanded.utility.Helper;
import com.zeowls.ajmanded.utility.Localization;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohamed.arafa on 8/28/2017.
 */

public class ServiceNestedAdapter extends RecyclerView.Adapter<ServiceNestedAdapter.MyViewHolder> {

    private final Context mContext;
    private List<ServiceModuleModel> mList = new ArrayList<>();
    private int mLayout;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.service_recycler)
        RecyclerView mRecycler;

        @BindView(R.id.service_title_tv)
        TextView mTitle;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ServiceNestedAdapter(Context context, List<ServiceModuleModel> list, int layout) {
        mContext = context;
        mList = list;
        mLayout = layout;
    }

    public void updateData(List<ServiceModuleModel> list) {
        mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ServiceModuleModel model = mList.get(position);
        ServiceAdapter adapter = new ServiceAdapter(mContext, model.getList(), R.layout.list_item_service_child);
        int spanCount = Helper.calculateNoOfColumns(mContext, 75);
        holder.mRecycler.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        holder.mRecycler.setAdapter(adapter);
        if (Localization.getCurrentLanguageID(mContext) == Localization.ARABIC_VALUE)
            holder.mTitle.setText(model.getNameAr());
        else
            holder.mTitle.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }
}
