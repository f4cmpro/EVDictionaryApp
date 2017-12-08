package com.example.f4cmpro.evdictionaryapp.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.View.AdapterDataView;
import com.example.f4cmpro.evdictionaryapp.Fragments.View.QuizFragment;
import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Question;
import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Quiz;

public class QuizAdapter extends FragmentStatePagerAdapter implements AdapterDataModel<Quiz>,
        AdapterDataView.PagerAdapterView<Question> {
    Quiz quiz;
    public QuizAdapter(FragmentManager fm) {
        super(fm);
        quiz = new Quiz();
    }

    @Override
    public Fragment getItem(int position) {
        return create(quiz.getItems().get(position));
    }

    @Override
    public int getCount() {
        if (quiz == null || quiz.getItems().isEmpty())
            return 0;
        return quiz.getItems().size();
    }

    @Override
    public int getItemPosition(Object object) {
        return quiz.getItems().indexOf(object);
    }


    @Override
    public Fragment create(Question question) {
        QuizFragment quizFragment = new QuizFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("question", question);
        bundle.putInt("number question", getItemPosition(question));
        quizFragment.setArguments(bundle);
        return quizFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = String.valueOf(position+1);
        return title;
    }


    @Override
    public Quiz getList() {
        return quiz;
    }

    @Override
    public void setList(Quiz quiz) {
        this.quiz = quiz;
    }

}

