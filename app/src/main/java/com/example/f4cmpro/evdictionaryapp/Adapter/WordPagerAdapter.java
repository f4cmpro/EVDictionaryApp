package com.example.f4cmpro.evdictionaryapp.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.View.AdapterDataView;
import com.example.f4cmpro.evdictionaryapp.Fragments.View.WordFragment;
import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;

import java.util.ArrayList;
import java.util.List;

public class WordPagerAdapter<T> extends FragmentStatePagerAdapter implements
        AdapterDataModel<List<Word>>, AdapterDataView.PagerAdapterView<Word> {

    private List<Word> mWords;
    public WordPagerAdapter(FragmentManager fm) {
        super(fm);
        mWords = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return create(mWords.get(position));
    }

    @Override
    public int getCount() {
        return mWords.size();
    }




    @Override
    public Fragment create(Word word) {
        WordFragment fragment = new WordFragment();
        Bundle args = new Bundle();
        args.putParcelable("word", word);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public List<Word> getList() {
        return mWords;
    }

    @Override
    public void setList(List<Word> object) {
        mWords = object;
    }
}
