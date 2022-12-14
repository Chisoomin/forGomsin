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

public class Read_DB_join extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.start:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.app_info:
                Intent intent1 = new Intent(getApplicationContext(), AppInfo.class);
                startActivity(intent1);
                return true;
            case R.id.inquiry:
                Intent intent2 = new Intent(getApplicationContext(), Inquiry.class);
                startActivity(intent2);
                return true;
        }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db_join);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        DB_join helper = new DB_join(getApplicationContext());
        SQLiteDatabase dbjoin = helper.getReadableDatabase();

        Cursor cursor = dbjoin.rawQuery("select name, BF_name, category, strftime('%Y-%m-%d', meetingdate),strftime('%Y-%m-%d', enlistmentdate), password from dbjoin where _id <> 1", null);

        while (cursor.moveToNext()) {
            TextView textView = new TextView(this);
            Typeface typeface = getResources().getFont(R.font.hoon);
            textView.setTypeface(typeface);
            textView.setTextSize(25);
            textView.setText("?????? ?????? : " + cursor.getString(0) + "\r\n???????????? ?????? : " + cursor.getString(1) + "\r\n???????????? : " + cursor.getString(2)+ "\r\n????????? : " + cursor.getString(3)+ "\r\n????????? : " + cursor.getString(4) + "\r\n???????????? : "+cursor.getString(5)+"\r\n");
            linearLayout.addView(textView);

        }

        dbjoin.close();
    }
}
