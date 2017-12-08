package com.example.f4cmpro.evdictionaryapp.Model.API;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface TranslateApi {
    void translateByRetrofit(String text, String language);
    interface RetrofitApi {
        @GET("/api/v1.5/tr.json/translate")
        Call<Translation> loadData(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
    }
}
