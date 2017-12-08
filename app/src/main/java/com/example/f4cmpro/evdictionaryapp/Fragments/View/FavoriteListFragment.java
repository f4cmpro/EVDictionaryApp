package com.example.f4cmpro.evdictionaryapp.Fragments.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.f4cmpro.evdictionaryapp.Activity.View.WordActivity;
import com.example.f4cmpro.evdictionaryapp.Adapter.View.AdapterDataView;
import com.example.f4cmpro.evdictionaryapp.Adapter.RowAdapter;
import com.example.f4cmpro.evdictionaryapp.Fragments.Presenter.FavoriteListImpl;
import com.example.f4cmpro.evdictionaryapp.R;

public class FavoriteListFragment extends Fragment implements IWordListView.SwitchActivity {
    private FavoriteListImpl mPresenter;
    private AdapterDataView adapterView;
    private ListView lvFavorites;
    private RowAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        //set list view
        lvFavorites = (ListView) rootView.findViewById(R.id.lv_favorites);
        adapterView = mAdapter = new RowAdapter(getContext());
        mPresenter = new FavoriteListImpl(getContext(), mAdapter);
        mPresenter.loadData();
        lvFavorites.setAdapter(mAdapter);

        lvFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetailWord(position);
            }
        });
        return rootView;
    }

    @Override
    public void goToDetailWord(int position) {
        Intent myIntent = new Intent(getContext(), WordActivity.class);
        myIntent.putExtra("isFavorite", true);
        myIntent.putExtra("position", position);
        startActivity(myIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.disconnectData();
    }
}
