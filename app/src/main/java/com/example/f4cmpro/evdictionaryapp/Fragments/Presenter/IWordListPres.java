package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;

public interface IWordListPres {
    interface SearchOperations {
        void searchWord(CharSequence searchText);
    }
    void loadData();
    void disconnectData();
}
