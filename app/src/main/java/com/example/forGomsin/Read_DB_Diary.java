package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Read_DB_Diary extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_inside, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.main:
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                return true;
            case R.id.percentage:
                Intent intent1 = new Intent(getApplicationContext(), PercentActivity.class);
                startActivity(intent1);
                return true;
            case R.id.daliy:
                Intent intent2 = new Intent(getApplicationContext(), DailyActivity.class);
                startActivity(intent2);
                return true;
            case R.id.tip:
                Intent intent3 = new Intent(getApplicationContext(), Tips.class);
                startActivity(intent3);
                return true;
        }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__d_b__diary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String today = simpleDateFormat.format(date);

        linearLayout = (LinearLayout) findViewById(R.id.lay);

        DB_Diary rhelper = new DB_Diary(this);
        SQLiteDatabase dbDiary = rhelper.getReadableDatabase();

        Cursor cursor = dbDiary.rawQuery("select title, dtext, rating, strftime('%Y-%m-%d', date) from dbDiary where _id <> 1", null);

        while (cursor.moveToNext()) {
            TextView textView = new TextView(this);
            Typeface typeface = getResources().getFont(R.font.hoon);
            textView.setTypeface(typeface);
            textView.setTextSize(25);
            textView.setText("제목 : " + cursor.getString(0) + "\r\n일기내용 : " + cursor.getString(1) + "\r\n오늘의 평가 : " + cursor.getFloat(2)+ "\r\n오늘 날짜 : " + cursor.getString(3)+"\r\n-------------------------------------");
            linearLayout.addView(textView);
        }

        dbDiary.close();
    }
}
