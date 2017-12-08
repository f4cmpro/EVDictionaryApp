package com.example.f4cmpro.evdictionaryapp.Fragments.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.f4cmpro.evdictionaryapp.Activity.Presenter.IQuizPres;
import com.example.f4cmpro.evdictionaryapp.Activity.Presenter.QuizImpl;
import com.example.f4cmpro.evdictionaryapp.Activity.View.IQuizView;
import com.example.f4cmpro.evdictionaryapp.Fragments.Presenter.QuizFImpl;
import com.example.f4cmpro.evdictionaryapp.Model.Quiz.Question;
import com.example.f4cmpro.evdictionaryapp.R;

public class QuizFragment extends Fragment implements View.OnClickListener {
    private TextView mTvNumberQuestion;
    private TextView mTvQuestion;
    private Button mBtnA, mBtnB, mBtnC, mBtnD, mBtnE;
    private IQuizView mListener;
    private Question question;
    private int numberQuestion;
    private QuizFImpl mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (IQuizView) context;
        question = getArguments().getParcelable("question");
        numberQuestion = getArguments().getInt("number question");
        mPresenter = new QuizFImpl(question);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        mTvNumberQuestion = (TextView) rootView.findViewById(R.id.tv_number_question);
        mTvNumberQuestion.setText(numberQuestion + 1 + "/10");
        mTvQuestion = (TextView) rootView.findViewById(R.id.tv_question);
        mTvQuestion.setText(question.getTitle());
        mBtnA = (Button) rootView.findViewById(R.id.btn_a);
        mBtnB = (Button) rootView.findViewById(R.id.btn_b);
        mBtnC = (Button) rootView.findViewById(R.id.btn_c);
        mBtnD = (Button) rootView.findViewById(R.id.btn_d);
        mBtnE = (Button) rootView.findViewById(R.id.btn_e);

        mBtnA.setText(question.getChoices()[0]);
        mBtnB.setText(question.getChoices()[1]);
        mBtnC.setText(question.getChoices()[2]);
        mBtnD.setText(question.getChoices()[3]);
        mBtnE.setText(question.getChoices()[4]);

        if(question.getChoicesUser().contains(mBtnA.getText().toString()))
            mBtnA.setBackground(getResources().getDrawable(R.color.colorAccentDark));
        if(question.getChoicesUser().contains(mBtnB.getText().toString()))
            mBtnB.setBackground(getResources().getDrawable(R.color.colorAccentDark));
        if(question.getChoicesUser().contains(mBtnC.getText().toString()))
            mBtnC.setBackground(getResources().getDrawable(R.color.colorAccentDark));
        if(question.getChoicesUser().contains(mBtnD.getText().toString()))
            mBtnD.setBackground(getResources().getDrawable(R.color.colorAccentDark));
        if(question.getChoicesUser().contains(mBtnE.getText().toString()))
            mBtnE.setBackground(getResources().getDrawable(R.color.colorAccentDark));

        mBtnA.setOnClickListener(this);
        mBtnB.setOnClickListener(this);
        mBtnC.setOnClickListener(this);
        mBtnD.setOnClickListener(this);
        mBtnE.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_a:
                mPresenter.setChoicesOfUser(mBtnA.getText().toString(), checkChose(mBtnA));
                break;
            case R.id.btn_b:
                mPresenter.setChoicesOfUser(mBtnB.getText().toString(), checkChose(mBtnB));
                break;
            case R.id.btn_c:
                mPresenter.setChoicesOfUser(mBtnC.getText().toString(), checkChose(mBtnC));
                break;
            case R.id.btn_d:
                mPresenter.setChoicesOfUser(mBtnD.getText().toString(), checkChose(mBtnD));
                break;
            case R.id.btn_e:
                mPresenter.setChoicesOfUser(mBtnE.getText().toString(), checkChose(mBtnE));
                break;
        }
    }

    private boolean checkChose(Button button){
        boolean isChose = button.getBackground().getConstantState()
                .equals(getResources().getDrawable(R.color.colorAccentDark).getConstantState());
        if(isChose){
            button.setBackgroundResource(android.R.color.darker_gray);
        }else{
            button.setBackgroundResource(R.color.colorAccentDark);
        }
        return isChose;
    }
}
