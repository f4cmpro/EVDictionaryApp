package com.example.f4cmpro.evdictionaryapp.Model.Database;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {

    }

    public static class WordEntry implements BaseColumns {
        public static final String TABLE_NAME = "word";
        public static final String ID_COL = "id";
        public static final String NAME_COL = "name";
        public static final String TYPE_COL = "type";
        public static final String PHONETIC_COL = "phonetic";
        public static final String MEAN_COL = "meaning";
        public static final String IS_FAVORITE_COL = "isFavorite";
    }
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + WordEntry.TABLE_NAME + " ("
            + WordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WordEntry.NAME_COL + " TEXT, " + WordEntry.TYPE_COL + " TEXT, "
            + WordEntry.PHONETIC_COL + " TEXT, " + WordEntry.MEAN_COL + " TEXT);";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + WordEntry.TABLE_NAME;
    public static final int DB_VERSION = 1;
}
