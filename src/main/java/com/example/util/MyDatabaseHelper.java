package com.example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
//    public static final String CREATE_HS="create table wordsbuilder("
//            +"id integer ,"
//            +"createdtime TimeStamp NOT NULL DEFAULT (datetime('now','localtime')),"
//            +"wbenglish text,"
//            +"wbchinese text,"
//            +"list text)";

    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_HS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(CREATE_HS);
    }
}

