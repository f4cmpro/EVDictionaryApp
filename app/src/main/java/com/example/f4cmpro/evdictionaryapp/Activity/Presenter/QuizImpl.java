package com.example.f4cmpro.evdictionaryapp.Activity.Presenter;


import android.app.NotificationManager;
import android.content.Context;

import com.example.f4cmpro.evdictionaryapp.Activity.View.IQuizView;
import com.example.f4cmpro.evdictionaryapp.Adapter.Model.AdapterDataModel;
import com.example.f4cmpro.evdictionaryapp.Adapter.QuizAdapter;
import com.example.f4cmpro.evdictionaryapp.Model.Database.DBHelper;
import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Question;
import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Quiz;
import com.example.f4cmpro.evdictionaryapp.Model.Quiz.QuizHelper;
import com.example.f4cmpro.evdictionaryapp.R;
import com.example.f4cmpro.evdictionaryapp.Receiver.AlarmReceiver;

import java.util.List;

public class QuizImpl implements IQuizPres {
    private Context mContext;
    private DBHelper mDBHelper;
    private AdapterDataModel<Quiz> mModelListener;
    private QuizHelper mQuizHelper;
    private IQuizView mViewListenter;

    public QuizImpl(Context context, QuizAdapter adapter) {
        mContext = context;
        mDBHelper = new DBHelper(context);
        mModelListener = adapter;
        mQuizHelper = new QuizHelper(this);
        mViewListenter = (IQuizView) context;
    }


    @Override
    public void cancelNotification() {
        NotificationManager nm = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(AlarmReceiver.NOTIFICATION_ID);
    }

    @Override
    public void displayQuiz() {
        Quiz quiz = mQuizHelper.getQuiz(mDBHelper.getRandomTenWords());
        mModelListener.setList(quiz);
    }

    @Override
    public void notifyResult(int position, boolean isTrue) {
        if (isTrue)
            mViewListenter.setBackgroundTab(position, R.color.colorTrueAnswer);
        else{
            mViewListenter.setBackgroundTab(position, R.color.colorFalseAnswer);
        }
    }

    @Override
    public void checkAnswerOfUser() {
        mQuizHelper.checkAnswer();
    }

}
