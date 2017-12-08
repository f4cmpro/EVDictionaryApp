package com.example.f4cmpro.evdictionaryapp.Model.API;

import android.util.Log;

import com.example.f4cmpro.evdictionaryapp.Fragments.Presenter.ITranslatePres;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class TranslateApiHelper implements TranslateApi {
    private static final String API_KEY = "trnsl.1.1.20171106T100948Z.5e09de3631b85faf." +
            "efb3adce730e6a084f3d8396007cd585be778a00";
    private static final String URL_BASE = "https://translate.yandex.net";
    private ITranslatePres mListener;

    public TranslateApiHelper(ITranslatePres listener) {
        mListener = listener;
    }

    @Override
    public void translateByRetrofit(String text, String language) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<Translation> call = retrofitApi.loadData(API_KEY, text, language);
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Response<Translation> response, Retrofit retrofit) {
                Log.e("123", "onResponse");
                Log.e("123", response.body().text.get(0) + "");
                mListener.setTranslateText(response.body().text.get(0));
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
