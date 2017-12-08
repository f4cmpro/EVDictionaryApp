package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;

import android.content.Context;
import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.RowAdapter;
import com.example.f4cmpro.evdictionaryapp.Model.Database.DBHelper;

public class FavoriteListImpl implements IWordListPres {
    private DBHelper mHelper;
    private AdapterDataModel model;

    public FavoriteListImpl(Context context, RowAdapter adapter){
        mHelper = new DBHelper(context);
        model = adapter;
    }


    @Override
    public void loadData() {
        model.setList(mHelper.getFavoriteList());
    }

    @Override
    public void disconnectData() {
        mHelper.close();
    }
}
