package com.example.f4cmpro.evdictionaryapp.Fragments.View;

import android.widget.TextView;

public interface IWordView {
    void changeTextView(TextView view, String text);
    void changeFavoriteButton(boolean isFavorite);
}
