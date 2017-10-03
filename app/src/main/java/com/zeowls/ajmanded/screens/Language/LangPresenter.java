package com.zeowls.ajmanded.screens.Language;

import android.content.Context;

import com.zeowls.ajmanded.MyApplication;

/**
 * Created by root on 10/2/17.
 */

public class LangPresenter implements LangContract.UserActions {

    Context mContext;
    MyApplication mApplication;

    public LangPresenter(Context mContext) {
        this.mContext = mContext;
        mApplication = MyApplication.get(mContext);
    }

    @Override
    public void chooseArabic() {
        mApplication.setLocale(1);
    }

    @Override
    public void chooseEnglish() {
        mApplication.setLocale(0);
    }
}
