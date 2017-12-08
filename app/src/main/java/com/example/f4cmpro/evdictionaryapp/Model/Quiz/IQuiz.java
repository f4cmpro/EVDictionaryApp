package com.example.f4cmpro.evdictionaryapp.Model.Quiz;

import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;

import java.util.List;

public interface IQuiz {
    Quiz getQuiz(List<Word> words);
    void checkAnswer();
}
