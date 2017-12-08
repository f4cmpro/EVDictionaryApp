package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;


import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;

import java.util.Locale;

public interface IWordFPres {
    void speak(String text, Locale locale);
    boolean like(Word word);
    void shutdownSpeak();
}
