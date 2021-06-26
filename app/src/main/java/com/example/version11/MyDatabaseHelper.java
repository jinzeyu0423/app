package com.example.version11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
//    public static final String CREATE_SCORE="create table score("
//            +"id integer,"
//            +"stime text primary key ,"
//            +"score integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //db.execSQL(CREATE_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(CREATE_SCORE);
    }
}

