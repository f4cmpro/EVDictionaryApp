package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.RowAdapter;
import com.example.f4cmpro.evdictionaryapp.Model.Database.DBHelper;
import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;
import java.util.ArrayList;
import java.util.List;

public class WordListsImpl implements IWordListPres, IWordListPres.SearchOperations {
    private DBHelper mHelper;
    private AdapterDataModel model;

    public WordListsImpl(@NonNull Context context, RowAdapter adapter) {
        model = adapter;
        mHelper = new DBHelper(context);
    }

    @Override
    public void searchWord(CharSequence searchText) {
        List<Word> words = mHelper.getAll();
        ArrayList<Word> searchWords = new ArrayList();
        String searchStr = searchText.toString().trim().toLowerCase();
        if (searchStr.isEmpty()) {
            searchWords.addAll(words);
        } else {
            for (Word item : words) {
                //Neu do dai cua tu trong mWords ngan hon searchStr;
                //thi hok dc loc
                if (searchStr.length() > item.getName().length())
                    continue;
                //Kiem tra do lech cua tu trong ds vs tu can tim
                //Neu do lech = 0 -> them vao ds da loc
                int delta = searchStr.length();
                for (int i = 0; i < searchStr.length(); i++) {
                    if (item.getName().toLowerCase().charAt(i) != searchStr.charAt(i))
                        break;
                    delta--;
                }
                if (delta == 0)
                    searchWords.add(item);
            }
        }
        model.setList(searchWords);
    }

    @Override
    public void loadData() {
        model.setList(mHelper.getAll());
    }

    @Override
    public void disconnectData() {
        mHelper.close();
    }
}
