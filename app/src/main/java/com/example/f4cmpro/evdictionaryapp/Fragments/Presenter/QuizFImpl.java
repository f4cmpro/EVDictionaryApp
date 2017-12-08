package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;

import android.util.Log;

import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Question;


public class QuizFImpl implements IQuizFPres {
    private Question question;
    public QuizFImpl(Question question) {
        this.question = question;
    }

    @Override
    public void setChoicesOfUser(String choice, boolean isChoose) {
        if (isChoose) {
            question.getChoicesUser().remove(choice);
        } else
            question.getChoicesUser().add(choice);
        Log.e("QH", question.getTitle() + ": " + question.getChoicesUser().size());
    }
}
