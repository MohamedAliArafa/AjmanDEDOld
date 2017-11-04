package com.zeowls.ajmanded.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.WebViewActivity;
import com.zeowls.ajmanded.models.ServiceModel;
import com.zeowls.ajmanded.utility.Localization;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.ajmanded.utility.Constants.URL_INTENT_KEY;
import static com.zeowls.ajmanded.utility.Constants.links.DOMAIN_AR;
import static com.zeowls.ajmanded.utility.Constants.links.DOMAIN_EN;
import static com.zeowls.ajmanded.utility.Constants.links.TRADE_NAME_RESERVATION;

/*
 * Created by mohamed.arafa on 8/28/2017.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    private List<ServiceModel> mList = new ArrayList<>();
    private int mLayout;
    private final Context mContext;
    Resources resources;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.service_name_tv)
        TextView mTitleTextView;

        @BindView(R.id.service_image)
        ImageView mImage;

        @BindView(R.id.service_icon)
        ImageView mIcon;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ServiceAdapter(Context context, List<ServiceModel> list, int layout) {
        mContext = context;
        mList = list;
        mLayout = layout;
        resources = context.getResources();
    }

    public void updateData(List<ServiceModel> list) {
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
        ServiceModel model = mList.get(position);
        if (Localization.getCurrentLanguageID(mContext) == Localization.ARABIC_VALUE)
            holder.mTitleTextView.setText(model.getNameAr());
        else
            holder.mTitleTextView.setText(model.getName());
        switch (model.getColor()) {
            case 1:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_dark_beige);
                break;
            case 2:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_dark_blue);
                break;
            case 3:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_light_red);
                break;
            case 4:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_light_blue);
                break;
            case 5:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_light_brown);
                break;


            case 6:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_light_brown);
                break;
            case 7:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_light_royal);
                break;
            case 8:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_dark_brown);
                break;
            case 9:
                holder.mImage.setBackgroundResource(R.drawable.bg_shape_light_royal);
                break;
        }

        try {
            final int resourceId = resources.getIdentifier(model.getIcon(), "drawable",
                    mContext.getPackageName());
            holder.mIcon.setImageResource(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(view -> {
            Intent in = new Intent(mContext, WebViewActivity.class);
            if (Localization.getCurrentLanguageID(mContext) == Localization.ARABIC_VALUE)
                in.putExtra(URL_INTENT_KEY, DOMAIN_AR + TRADE_NAME_RESERVATION);
            else
                in.putExtra(URL_INTENT_KEY, DOMAIN_EN + TRADE_NAME_RESERVATION);
            mContext.startActivity(in);
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }
}
