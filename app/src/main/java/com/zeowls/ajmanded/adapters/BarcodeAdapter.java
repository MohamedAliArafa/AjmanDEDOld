package com.zeowls.ajmanded.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.ajmanded.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohamed.arafa on 8/28/2017.
 */

public class BarcodeAdapter extends RecyclerView.Adapter<BarcodeAdapter.MyViewHolder> {

    private int mLayout;
    private Context mContext;

    @BindView(R.id.tv_name)
    TextView mName;

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View view) {
            super(view);
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
        ButterKnife.bind(this, itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (position) {
            case 0:
                mName.setText("مشاريع عكاظ للتجارة ذ.م.م");
                break;
            case 1:
                mName.setText("المكتب الوطني للخدمات المهنية");
                break;
            case 2:
                mName.setText("الاصابع الذهبية للصيانة");
                break;
            case 3:
                mName.setText("معرض وائل للخياطة");
                break;
            case 4:
                mName.setText("مؤسسة الساعدي للصيانة");
                break;
            case 5:
                mName.setText("ورشة الشرق الاوسط للكهرباء");
                break;
            case 6:
                mName.setText("مكتب فراس للطباعة");
                break;
            case 7:
                mName.setText("سوبرماركت الشحي");
                break;
            case 8:
                mName.setText("مكتبة المعارف");
                break;
            case 9:
                mName.setText("يافا للخياطة");
                break;
            case 10:
                mName.setText("شركة جدة لمقاولات البناء ذ.م.م");
                break;
            default:
                mName.setText("شركة جدة لمقاولات البناء ذ.م.م");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 11;
    }
}
