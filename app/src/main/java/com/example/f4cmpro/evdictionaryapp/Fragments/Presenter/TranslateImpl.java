package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.f4cmpro.evdictionaryapp.Fragments.View.ITranslateView;
import com.example.f4cmpro.evdictionaryapp.Model.API.TranslateApiHelper;
import com.example.f4cmpro.evdictionaryapp.Model.Network.NetWork;

public class TranslateImpl implements ITranslatePres {
    private static final String VIETNAMESE = "vi";
    private static final String ENGLISH = "en";
    Context mContext;
    String mTransLang;
    NetWork mNetWork;
    TranslateApiHelper mApiHelper;
    private ITranslateView mListener;


    public TranslateImpl(Context context, Fragment fragment) {
        mContext = context;
        mTransLang = VIETNAMESE + "-" + ENGLISH;
        mNetWork = new NetWork(context);
        mApiHelper = new TranslateApiHelper(this);
        mListener = (ITranslateView) fragment;
    }

    @Override
    public void translate(String text) {
        mApiHelper.translateByRetrofit(text, mTransLang);
    }

    @Override
    public void swapLanguage() {
        if (mTransLang.equals(VIETNAMESE + "-" + ENGLISH))
            mTransLang = ENGLISH + "-" + VIETNAMESE;
        else
            mTransLang = VIETNAMESE + "-" + ENGLISH;
    }

    @Override
    public void setTranslateText(String text) {
        mListener.setTransTextView(text);
    }
}
