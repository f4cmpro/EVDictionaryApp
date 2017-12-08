package com.example.f4cmpro.evdictionaryapp.Activity.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.f4cmpro.evdictionaryapp.Adapter.MainPagerAdapter;
import com.example.f4cmpro.evdictionaryapp.Fragments.View.FavoriteListFragment;
import com.example.f4cmpro.evdictionaryapp.Fragments.View.TranslateFragment;
import com.example.f4cmpro.evdictionaryapp.Fragments.View.WordListFragment;
import com.example.f4cmpro.evdictionaryapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ViewPager mPager;
    private MainPagerAdapter mAdapter;
    private TabLayout mTab;
    private FragmentManager manager;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //set view pager
        List<Fragment> fragments = getFragments();
        mPager = (ViewPager) findViewById(R.id.pager_main);
        manager = getSupportFragmentManager();
        mAdapter = new MainPagerAdapter(manager, fragments);
        mPager.setAdapter(mAdapter);

        //set tab layout
        mTab = (TabLayout) findViewById(R.id.tab_main);
        mTab.setupWithViewPager(mPager);

        //set drawer layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        //set navigation view
        mNavigationView = (NavigationView) findViewById(R.id.left_drawer);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                switchPager(0);
                break;
            case R.id.item_favorites:
                switchPager(1);
                break;
            case R.id.item_translate:
                switchPager(2);
                break;
            case R.id.item_quiz:
                switchActivity(QuizActivity.class);
                break;
            case R.id.item_setting:
                switchActivity(AlarmActivity.class);
                break;
            case R.id.item_about_us:
                Toast.makeText(this, "F4cmpro designs it!", Toast.LENGTH_SHORT).show();
                break;
            default:
                switchPager(0);
        }
        mDrawerLayout.closeDrawer(mNavigationView);
        return true;
    }

    @Override
    public void switchPager(int position) {
        mPager.setCurrentItem(position);
        getSupportActionBar().setTitle(getResources().getStringArray(R.array.drawer_items)[position]);
    }

    @Override
    public void switchActivity(Class<?> activity) {
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WordListFragment());
        fragments.add(new FavoriteListFragment());
        fragments.add(new TranslateFragment());
        return fragments;
    }


}
