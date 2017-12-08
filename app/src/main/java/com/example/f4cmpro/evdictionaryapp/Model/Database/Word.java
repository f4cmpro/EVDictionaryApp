package com.example.f4cmpro.evdictionaryapp.Model.Database;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable{
    private int id;
    private String name;
    private String meaning;
    private String phonetic;
    private String type;
    private int isFavorite;

    public Word(int id, String name, String type, String phonetic, String meaning, int isFavorite) {
        this.id = id;
        this.name = name;
        this.meaning = meaning;
        this.type = type;
        this.phonetic = phonetic;
        this.isFavorite = isFavorite;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        name = in.readString();
        meaning = in.readString();
        phonetic = in.readString();
        type = in.readString();
        isFavorite = in.readInt();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFavorite(int isFavorite){
        this.isFavorite = isFavorite;
    }
    public int isFavorite(){
        return this.isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(meaning);
        dest.writeString(phonetic);
        dest.writeString(type);
        dest.writeInt(isFavorite);
    }
}
