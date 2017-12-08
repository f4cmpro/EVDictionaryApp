package com.example.f4cmpro.evdictionaryapp.Activity.View;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.f4cmpro.evdictionaryapp.Activity.Presenter.QuizImpl;
import com.example.f4cmpro.evdictionaryapp.Adapter.QuizAdapter;
import com.example.f4cmpro.evdictionaryapp.Adapter.View.AdapterDataView;
import com.example.f4cmpro.evdictionaryapp.R;

public class QuizActivity extends AppCompatActivity implements IQuizView, AdapterDataView {
    private Toolbar mToolbar;
    private ViewPager mPager;
    private QuizAdapter mAdapter;
    private QuizImpl mPresenter;
    private TabLayout mTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("QUIZ");

        mPager = (ViewPager) findViewById(R.id.pager_main);
        mAdapter = new QuizAdapter(getSupportFragmentManager());
        mPresenter = new QuizImpl(this, mAdapter);
        mPresenter.displayQuiz();
        mPager.setAdapter(mAdapter);

        mTab = (TabLayout) findViewById(R.id.tab_quiz);
        mTab.setupWithViewPager(mPager);
        for(int i = 0; i<10; i++){
            View v = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            TextView tv = (TextView) v.findViewById(R.id.tv_tab);
            tv.setText(String.valueOf(i+1));
            mTab.getTabAt(i).setCustomView(v);
        }

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.checkAnswerOfUser();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBackgroundTab(int position, int colorId) {
        View v = mTab.getTabAt(position).getCustomView();
        TextView textTab = (TextView) v.findViewById(R.id.tv_tab);
        textTab.setTextColor(getResources().getColor(colorId));
    }
}
