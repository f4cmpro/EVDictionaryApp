package com.example.f4cmpro.evdictionaryapp.Fragments.Presenter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.f4cmpro.evdictionaryapp.Fragments.View.IWordView;
import com.example.f4cmpro.evdictionaryapp.Model.Database.DBHelper;
import com.example.f4cmpro.evdictionaryapp.Model.Database.Word;

import java.util.Locale;

public class WordImpl implements IWordFPres {
    IWordView viewListener;
    Context mContext;
    DBHelper mHelper;
    TextToSpeech tts;

    public WordImpl(Fragment fragment) {
        viewListener = (IWordView) fragment;
        mContext = fragment.getContext();
        mHelper = new DBHelper(mContext);
        tts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.ERROR)
                    return;
            }
        });
    }

    @Override
    public void speak(String text, Locale locale) {
        tts.setLanguage(locale);
        tts.setPitch(1.5f);
        tts.setSpeechRate(1.0f);
        Log.e("WI", "text: " + text);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public boolean like(Word word) {
        if (word.isFavorite() == 1) {
            word.setFavorite(0);
            mHelper.updateWord(word);
            return false;
        } else {
            word.setFavorite(1);
            mHelper.updateWord(word);
            return true;
        }
    }

    @Override
    public void shutdownSpeak() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }

    }
}
