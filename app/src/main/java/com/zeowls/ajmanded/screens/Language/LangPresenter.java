package com.zeowls.ajmanded.screens.Language;

import android.content.Context;

import com.zeowls.ajmanded.MyApplication;
import com.zeowls.ajmanded.utility.Localization;

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
        mApplication.setLocale(Localization.ARABIC_VALUE);
    }

    @Override
    public void chooseEnglish() {
        mApplication.setLocale(Localization.ENGLISH_VALUE);
    }
}
