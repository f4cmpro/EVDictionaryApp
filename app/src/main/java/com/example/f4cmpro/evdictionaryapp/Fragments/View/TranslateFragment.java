package com.example.f4cmpro.evdictionaryapp.Fragments.View;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f4cmpro.evdictionaryapp.Fragments.Presenter.TranslateImpl;
import com.example.f4cmpro.evdictionaryapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class TranslateFragment extends Fragment implements View.OnClickListener, ITranslateView {
    private ImageView mImvTrans1;
    private ImageView mImvTrans2;
    private EditText mEdtTrans;
    private TextView mTvTrans;
    private Button mBtnTrans;
    private ImageButton mBtnSwap;

    private TranslateImpl mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);

        mImvTrans1 = (ImageView) rootView.findViewById(R.id.imv_trans_1);
        mImvTrans2 = (ImageView) rootView.findViewById(R.id.imv_trans_2);
        mEdtTrans = (EditText) rootView.findViewById(R.id.edt_trans);
        mTvTrans = (TextView) rootView.findViewById(R.id.tv_trans);
        mBtnTrans = (Button) rootView.findViewById(R.id.btn_trans);
        mBtnSwap = (ImageButton) rootView.findViewById(R.id.btn_swap);

        mBtnSwap.setOnClickListener(this);
        mBtnTrans.setOnClickListener(this);

        mPresenter = new TranslateImpl(getContext(), this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_swap:
                mPresenter.swapLanguage();
                changeFlagImageView();
                break;
            case R.id.btn_trans:
                mPresenter.translate(mEdtTrans.getText().toString());
                break;
        }
    }

    @Override
    public void changeFlagImageView() {
        if (mImvTrans1.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.vietnam_icon).getConstantState())){
            mImvTrans1.setImageResource(R.drawable.uk_icon);
            mImvTrans2.setImageResource(R.drawable.vietnam_icon);
        }else{
            mImvTrans1.setImageResource(R.drawable.vietnam_icon);
            mImvTrans2.setImageResource(R.drawable.uk_icon);
        }
    }

    @Override
    public void setTransTextView(String text) {
        mTvTrans.setText(text);
    }
}
