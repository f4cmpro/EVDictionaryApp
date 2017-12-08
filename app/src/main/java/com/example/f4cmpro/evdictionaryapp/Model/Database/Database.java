package com.example.f4cmpro.evdictionaryapp.Model.Database;

import java.util.LinkedList;
import java.util.List;

public interface Database {
    List<Word> getAll();

    List<Word> getFavoriteList();

    boolean updateWord(Word word);

    LinkedList<Word> getRandomTenWords();

}
