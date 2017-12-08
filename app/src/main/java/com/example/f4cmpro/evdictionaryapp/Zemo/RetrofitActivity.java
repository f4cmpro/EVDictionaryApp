package com.example.f4cmpro.evdictionaryapp.Zemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.f4cmpro.evdictionaryapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitActivity extends AppCompatActivity implements Callback<StackOverflowQuestions> {
    private ListView listView;
    private RetroAdapter adapter;
    private List<Question> questions;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_activity);
        Log.e("123", "onCreate");
        Toast.makeText(this, "dsadkassd", Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Retrofit Demo");
        listView = (ListView) findViewById(R.id.lv_retrofit);
        //Set adapter mac dinh
        adapter = new RetroAdapter(this);
        listView.setAdapter(adapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Khi lua chon load data tu menu
        setProgressBarIndeterminate(true);
        //Khoi tao Retrofit de gan TranslateApi endpoint (domain url) cho retrofit 2.0
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                // Sử dụng GSON cho việc parse và maps JSON data tới Object
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Khoi tao cac cuoc goi cho Retrofit 2.0
        StackOverFlowAPI stackOverFlowAPI = retrofit.create(StackOverFlowAPI.class);
            Call<StackOverflowQuestions> call = stackOverFlowAPI.loadQuestions("android");
        //Cuoc goi bat dong bo chay duoi background
        call.enqueue(this);
        // Nếu bạn muốn chạy đồng bồ trên main thread sử dụng phương thức execute
        // call.execute()

        // Để Cancel request:
        // call.cancel();

        // Có thể clone một cuộc gọi trước đó
        //Call<StackOverflowQuestions> c = call.clone();
        //c.enqueue(this);
        return super.onOptionsItemSelected(item);
    }

    //Phuong thuc trien khai khi implement interface Callback
    @Override
    public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
        //Visible Loading Bar
        setProgressBarIndeterminate(false);
        //Lay du lieu tra ve tu Response qua Body()
        adapter.setQuestionList(response.body().items);
    }

    @Override
    public void onFailure(Throwable t) {
        // Khi có vấn đề từ Server như các lỗi 4xx 5xx ....
        Toast.makeText(RetrofitActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
