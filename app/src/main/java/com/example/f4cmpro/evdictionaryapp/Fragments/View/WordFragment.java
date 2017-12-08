package com.example.f4cmpro.evdictionaryapp.Fragments.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.f4cmpro.evdictionaryapp.Fragments.Presenter.WordImpl;
import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;
import com.example.f4cmpro.evdictionaryapp.R;

import java.util.Locale;


public class WordFragment extends Fragment implements View.OnClickListener, IWordView {
    private TextView tvName;
    private TextView tvPhonetic;
    private TextView tvType;
    private TextView tvMeaning;
    private ImageButton btnUK;
    private ImageButton btnUS;
    private ImageButton btnLike;
    private ImageButton btnEdit;
    private Word mWord;

    private WordImpl mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWord = getArguments().getParcelable("word");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_word_page, container, false);
        //Set text view
        tvName = (TextView) rootView.findViewById(R.id.tv_name);
        changeTextView(tvName, mWord.getName());
        tvType = (TextView) rootView.findViewById(R.id.tv_type);
        changeTextView(tvType, mWord.getType());
        tvPhonetic = (TextView) rootView.findViewById(R.id.tv_phonetic);
        changeTextView(tvPhonetic, mWord.getPhonetic());
        tvMeaning = (TextView) rootView.findViewById(R.id.tv_meaning);
        changeTextView(tvMeaning, mWord.getMeaning());

        //Set Button
        btnUK = (ImageButton) rootView.findViewById(R.id.btn_uk);
        btnUS = (ImageButton) rootView.findViewById(R.id.btn_us);
        btnLike = (ImageButton) rootView.findViewById(R.id.btn_like);
        changeFavoriteButton(mWord.isFavorite() == 1 ? true : false);
        btnEdit = (ImageButton) rootView.findViewById(R.id.btn_edit);

        btnUK.setOnClickListener(this);
        btnUS.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        mPresenter = new WordImpl(this);

        //set TextToSpeech

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.shutdownSpeak();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_uk:
                mPresenter.speak(mWord.getName(), Locale.UK);
                break;
            case R.id.btn_us:
                mPresenter.speak(mWord.getName(), Locale.US);
                break;
            case R.id.btn_like:
                changeFavoriteButton(mPresenter.like(mWord));
                break;
            case R.id.btn_edit:
                break;
        }
    }

    @Override
    public void changeTextView(TextView view, String text) {
        view.setText(text);
    }

    @Override
    public void changeFavoriteButton(boolean isFavorite) {
        if (isFavorite) {
            btnLike.setBackgroundResource(R.drawable.ic_like_pressed);
        } else {
            btnLike.setBackgroundResource(R.drawable.ic_like_default);
        }
    }
}
