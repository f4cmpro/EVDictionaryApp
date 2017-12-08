package com.example.f4cmpro.evdictionaryapp.Model.Quiz;

import java.util.LinkedList;
import java.util.List;

public class Quiz {
    List<Question> items;

    public Quiz() {
        items = new LinkedList<>();
    }

    public List<Question> getItems() {
        return items;
    }
}
