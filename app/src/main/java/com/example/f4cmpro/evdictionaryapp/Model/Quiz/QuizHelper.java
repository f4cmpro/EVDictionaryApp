package com.example.f4cmpro.evdictionaryapp.Model.Quiz;
import android.util.Log;

import com.example.f4cmpro.evdictionaryapp.Activity.Presenter.IQuizPres;
import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuizHelper implements IQuiz {
    private Quiz quiz;
    private IQuizPres mPresenter;

    public QuizHelper(IQuizPres presenter) {
        quiz = new Quiz();
        mPresenter = presenter;
    }

    @Override
    public Quiz getQuiz(List<Word> words) {
        //Create 10 questions
        for (int i = 0; i < 10; i++) {
            Question ques = new Question();
            ques.setTitle(words.get(i).getName());

            LinkedList<String> answers = new LinkedList();
            for(String item : Arrays.asList(words.get(i).getMeaning().split(","))){
                answers.add(item.trim());
            }
            ques.setAnswers(answers);

            LinkedList<Word> tempList = new LinkedList(words);
            tempList.remove(words.get(i));
            ques.setChoices(this.createChoices(tempList, answers));

            quiz.items.add(ques);
        }
        return quiz;
    }

    @Override
    public void checkAnswer() {
        for(Question ques : quiz.getItems()){
            inner:
            if(!ques.getChoicesUser().isEmpty()){
                for(String answer : ques.getAnswers())
                    if(!ques.getChoicesUser().contains(answer)){
                        mPresenter.notifyResult(quiz.getItems().indexOf(ques), false);
                        break inner;
                    }
                mPresenter.notifyResult(quiz.getItems().indexOf(ques), true);
            }
        }
    }

    private String[] createChoices(List<Word> words, LinkedList<String> answers) {
        String[] choices = new String[5];
        int count = 0;
        while (count < choices.length) {
            int pos1 = new Random().nextInt(5);
            if (choices[pos1] == null) {
                if (!answers.isEmpty()) {
                    choices[pos1] = answers.getLast();
                    answers.removeLast();
                } else {
                    int pos2 = new Random().nextInt(words.size());
                    String[] means = words.get(pos2).getMeaning().split(",");
                    choices[pos1] = means[new Random().nextInt(means.length)].trim();
                    words.remove(pos2);
                }
                count++;
            }
        }
        return choices;
    }
}
