package com.example.f4cmpro.evdictionaryapp.Model.Quiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question implements Parcelable{
    private String title;
    private LinkedList<String> answers;
    private String[] choices;
    private List<String> choicesUser;

    protected Question(Parcel in) {
        title = in.readString();
        choices = in.createStringArray();
        choicesUser = in.createStringArrayList();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Question(){
        choicesUser = new ArrayList<>();
    }
    public List<String> getChoicesUser() {
        return choicesUser;
    }

    public void setChoicesUser(List<String> choicesUser) {
        this.choicesUser.addAll(choicesUser);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(LinkedList<String> answers) {
        this.answers = new LinkedList(answers);
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringArray(choices);
        dest.writeStringList(choicesUser);
    }
}
