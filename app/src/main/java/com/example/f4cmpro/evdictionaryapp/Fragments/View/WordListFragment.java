package com.example.f4cmpro.evdictionaryapp.Fragments.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.f4cmpro.evdictionaryapp.Adapter.View.AdapterDataView;
import com.example.f4cmpro.evdictionaryapp.Adapter.RowAdapter;
import com.example.f4cmpro.evdictionaryapp.Fragments.Presenter.WordListsImpl;
import com.example.f4cmpro.evdictionaryapp.R;
import com.example.f4cmpro.evdictionaryapp.Activity.View.WordActivity;

public class WordListFragment extends Fragment implements IWordListView.SwitchActivity {
    private ListView mWordsLV;
    private RowAdapter mAdapter;
    private AdapterDataView mAdapterView;
    private EditText mEdtSearch;
    private WordListsImpl mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_words, container, false);
        //Set list view
        mWordsLV = (ListView) rootView.findViewById(R.id.lv_words);
        mAdapterView = mAdapter = new RowAdapter(getContext());
        mPresenter =  new WordListsImpl(getContext(), mAdapter);
        mPresenter.loadData();
        mWordsLV.setAdapter(mAdapter);

        //implements callback
        mWordsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetailWord(position);
            }
        });

        //set Search edit text
        mEdtSearch = (EditText) rootView.findViewById(R.id.edt_search);
        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.searchWord(s);
                mAdapterView.refresh();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.disconnectData();
    }

    @Override
    public void goToDetailWord(int position) {
        Intent myIntent = new Intent(getContext(), WordActivity.class);
        myIntent.putExtra("isFavorite", false);
        myIntent.putExtra("position", position);
        startActivity(myIntent);
    }
}
