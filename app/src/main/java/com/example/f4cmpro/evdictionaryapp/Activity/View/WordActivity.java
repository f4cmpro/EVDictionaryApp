package com.example.f4cmpro.evdictionaryapp.Activity.View;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.f4cmpro.evdictionaryapp.Activity.Presenter.WordImpl;
import com.example.f4cmpro.evdictionaryapp.Adapter.WordPagerAdapter;
import com.example.f4cmpro.evdictionaryapp.R;

public class WordActivity extends AppCompatActivity {
    private ViewPager mPager;
    private WordPagerAdapter mAdapter;
    private Toolbar mToolbar;

    private WordImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("WORD DETAIL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Which fragment call to this Activity
        boolean isFavorite = getIntent().getBooleanExtra("isFavorite", false);
        int position = getIntent().getIntExtra("position", 0);
        mPager = (ViewPager) findViewById(R.id.pager_word_detail);
        mAdapter = new WordPagerAdapter(getSupportFragmentManager());
        mPresenter = new WordImpl(this, mAdapter, isFavorite);
        mPresenter.loadData();
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(position);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
                Log.d("WDA", "on page selected: " + position);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disconnectData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_detail_word, menu);

        //enable 'previous' button if current item > 0
        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        //add either a'next' or 'finish' button to the action bar, depending on which page
        // is currently selected
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mAdapter.getCount() - 1 ? "finish" : "next"));
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;
            case R.id.action_previous:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;
            case R.id.action_next:
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
