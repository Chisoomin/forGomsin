package com.example.forGomsin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Diary extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DB_Diary(@Nullable Context context) {
        super(context, "diary", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL = "create table dbDiary(" +
                "_id integer primary key autoincrement," +
                "title text, "+
                "dtext text, "+
                "rating float, "+
                "date datetime default CURRENT_TIMESTAMP)";
        sqLiteDatabase.execSQL(SQL);
        SQL="insert into dbDiary(title, dtext, rating, date) values('안녕', '오늘은 행복해', 4.5, '2020-11-20')";
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1==DATABASE_VERSION){
            sqLiteDatabase.execSQL("drop table dbDiary");
            onCreate(sqLiteDatabase);
        }
    }
}
