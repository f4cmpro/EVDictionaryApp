package com.example.f4cmpro.evdictionaryapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import com.example.f4cmpro.evdictionaryapp.Model.Database.DBContract.WordEntry;


public class DBHelper extends SQLiteOpenHelper implements Database {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_ver3.db";
    private static String DB_PATH;
    private SQLiteDatabase mDB;
    private Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
        DB_PATH = "data/data/" + mContext.getPackageName() + "/databases/";
        createDataBase();
    }

    private void createDataBase() {
        if (!checkDBExits()) {
            mDB = this.getReadableDatabase();
            copyDataBase();
        } else {
            openDataBase();
        }
    }

    private boolean checkDBExits() {
        boolean isExits;
        String myPath = DB_PATH + DB_NAME;
        File dbFile = new File(myPath);
        isExits = dbFile.exists();
        return isExits;
    }

    private void copyDataBase() {
        try {
            InputStream input = mContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream output = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            input.close();
            Log.i("TAG1", "Copied!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG1", "Can not copy!");
        }
    }

    private void deleteDataBase() {
        String myPath = DB_PATH + DB_NAME;
        File dbFile = new File(myPath);
        if (dbFile.exists()) {
            dbFile.delete();
            Log.i("TAG1", "db file is deleted!");
        } else
            Log.i("TAG1", "db file not exists");
    }

    private void openDataBase() {
        if (mDB != null && mDB.isOpen())
            return;
        String myPath = DB_PATH + DB_NAME;
        mDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.i("TAG1", "opened!");
    }

    @Override
    public synchronized void close() {
        if (mDB != null)
            mDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.i("TAG1", "Database version higher than old");
            deleteDataBase();
            createDataBase();
        }
    }


                                    /*THE METHODS ACCESS DATA IN DATABASE*/

    //SELECT * FROM word;
    @Override
    public ArrayList<Word> getAll() {
        openDataBase();
        ArrayList<Word> words = new ArrayList<>();
        String query = "SELECT * FROM word WHERE 1";
        if (mDB == null) {
            mDB = this.getReadableDatabase();
        }
        Cursor cs = mDB.rawQuery(query, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String type = cs.getString(2);
            String phonetic = cs.getString(3);
            String meaning = cs.getString(4);
            int isFavorite = cs.getInt(5);
            Word word = new Word(id, name, type, phonetic, meaning, isFavorite);
            words.add(word);
            cs.moveToNext();
        }
        cs.close();
        this.close();
        return words;
    }


    //SELECT * FROM word WHERE isFavorit = 1;
    @Override
    public ArrayList<Word> getFavoriteList() {
        ArrayList<Word> words = new ArrayList<>();
        String sql = "SELECT * FROM " + WordEntry.TABLE_NAME + " WHERE " + WordEntry.IS_FAVORITE_COL + " = 1";
        Cursor cs = mDB.rawQuery(sql, null);
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String type = cs.getString(2);
            String phonetic = cs.getString(3);
            String meaning = cs.getString(4);
            int isFavorite = cs.getInt(5);
            Word word = new Word(id, name, type, phonetic, meaning, isFavorite);
            words.add(word);
        }
        cs.close();
        this.close();
        return words;
    }

    //SELECT * FROM word WHERE id = myId;
    public Word getWordById(int id) {
        Word myWord = null;
        String query = "SELECT * FROM word WHERE id = " + id;
        Cursor cs = mDB.rawQuery(query, null);
        if (cs.moveToNext()) {
            String name = cs.getString(cs.getColumnIndexOrThrow(WordEntry.NAME_COL));
            String type = cs.getString(cs.getColumnIndexOrThrow(WordEntry.TYPE_COL));
            String phonetic = cs.getString(cs.getColumnIndexOrThrow(WordEntry.PHONETIC_COL));
            String meaning = cs.getString(cs.getColumnIndexOrThrow(WordEntry.MEAN_COL));
            int isFavorite = cs.getInt(cs.getColumnIndexOrThrow(WordEntry.IS_FAVORITE_COL));
            myWord = new Word(id, name, type, phonetic, meaning, isFavorite);
        }
        return myWord;
    }

    //UPDATE INTO word SET name = myName, phonetic = myPhonetic, type = myType,
    // meaning = myMeaning, isFavorite = isFavorite WHERE id = myId;
    @Override
    public boolean updateWord(Word word) {
        //New value for one column
        ContentValues cv = new ContentValues();
        cv.put(WordEntry.NAME_COL, word.getName());
        cv.put(WordEntry.PHONETIC_COL, word.getPhonetic());
        cv.put(WordEntry.TYPE_COL, word.getType());
        cv.put(WordEntry.MEAN_COL, word.getMeaning());
        cv.put(WordEntry.IS_FAVORITE_COL, word.isFavorite());
        String projection = WordEntry.ID_COL + " = ?";
        String[] projectionArgs = {String.valueOf(word.getId())};
        int isUpdate = mDB.update(WordEntry.TABLE_NAME, cv, projection, projectionArgs);
        return isUpdate != 0 ? true : false;
    }

    //GET RANDOM 10 WORDS
    @Override
    public LinkedList<Word> getRandomTenWords() {
        LinkedList<Word> randomWords = new LinkedList<>();
        if (mDB == null) {
            mDB = this.getReadableDatabase();
        }
        // Select random 10 words
        String sql = "SELECT * FROM word WHERE id IN (SELECT id FROM word GROUP BY RANDOM() LIMIT 10)";
        Cursor cs = mDB.rawQuery(sql, null);
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String type = cs.getString(2);
            String phonetic = cs.getString(3);
            String meaning = cs.getString(4);
            int isFavorite = cs.getInt(5);
            Word word = new Word(id, name, type, phonetic, meaning, isFavorite);
            randomWords.add(word);
        }
        cs.close();
        return randomWords;
    }


}
