package com.example.forGomsin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_join extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DB_join(@Nullable Context context) {
        super(context, "join", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL = "create table dbjoin(" +
                "_id integer primary key autoincrement," +
                "name text, "+
                "BF_name text, "+
                "category text, "+
                "meetingdate datetime default CURRENT_TIMESTAMP,"+
                "enlistmentdate datetime default CURRENT_TIMESTAMP," +
                "password text)";
        sqLiteDatabase.execSQL(SQL);
        SQL="insert into dbjoin(name, BF_name, category, meetingdate, enlistmentdate, password) values('예시_김수한무','거북이', '육군','2020-04-12','2020-11-18', '1234')";
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1==DATABASE_VERSION){
            sqLiteDatabase.execSQL("drop table dbjoin");
            onCreate(sqLiteDatabase);
        }
    }
}
