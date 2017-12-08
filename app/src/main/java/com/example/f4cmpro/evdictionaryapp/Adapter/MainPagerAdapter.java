package com.example.f4cmpro.evdictionaryapp.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragList;
    public MainPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mFragList = list;
    }



    @Override
    public Fragment getItem(int position) {
        return mFragList.get(position);
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "Favorite";
            case 2:
                return "Translate";
            default:
                return null;
        }
    }




}
