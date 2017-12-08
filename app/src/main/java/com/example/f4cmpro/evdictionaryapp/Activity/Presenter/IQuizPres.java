package com.example.f4cmpro.evdictionaryapp.Activity.Presenter;

import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Question;

public interface IQuizPres {
    void cancelNotification();
    void displayQuiz();
    void notifyResult(int position, boolean isTrue);
    void checkAnswerOfUser();
}
