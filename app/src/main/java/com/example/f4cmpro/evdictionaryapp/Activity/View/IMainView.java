package com.example.f4cmpro.evdictionaryapp.Activity.View;

import android.support.v4.app.Fragment;

import java.util.List;

public interface IMainView {
    void switchPager(int position);
    void switchActivity(Class<?> activity);
    List<Fragment> getFragments();
}
