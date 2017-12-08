package com.example.f4cmpro.evdictionaryapp.Zemo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface StackOverFlowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);
}
