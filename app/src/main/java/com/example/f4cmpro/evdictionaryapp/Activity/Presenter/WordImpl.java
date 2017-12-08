package com.example.f4cmpro.evdictionaryapp.Activity.Presenter;

import android.content.Context;
import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.WordPagerAdapter;
import com.example.f4cmpro.evdictionaryapp.Model.Database.DBHelper;
public class WordImpl implements IWordPres {
    private AdapterDataModel model;
    private DBHelper mHelper;
    private boolean isFavorite;

    public WordImpl(Context context, WordPagerAdapter adapter, boolean isFavorite) {
        mHelper = new DBHelper(context);
        model = adapter;
        this.isFavorite = isFavorite;
    }

    @Override
    public void loadData() {
        if (isFavorite)
            model.setList(mHelper.getFavoriteList());
        else
            model.setList(mHelper.getAll());
    }

    @Override
    public void disconnectData() {
        mHelper.close();
    }
}
