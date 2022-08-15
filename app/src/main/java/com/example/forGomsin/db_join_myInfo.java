package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class db_join_myInfo extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_join_my_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        DB_join helper = new DB_join(getApplicationContext());
        SQLiteDatabase dbjoin = helper.getReadableDatabase();

        Cursor cursor = dbjoin.rawQuery("select name, BF_name, category, strftime('%Y-%m-%d', meetingdate),strftime('%Y-%m-%d', enlistmentdate), password from dbjoin where _id <> 1", null);

        while (cursor.moveToNext()) {
            TextView textView = new TextView(this);
            textView.setTextSize(20);
            textView.setText("본인 이름 : " + cursor.getString(0) + "\r\n남자친구 이름 : " + cursor.getString(1) + "\r\n복무형태 : " + cursor.getString(2)+ "\r\n만난날 : " + cursor.getString(3)+ "\r\n입대일 : " + cursor.getString(4) + "\r\n비밀번호 : "+cursor.getInt(5)+"\r\n");
            linearLayout.addView(textView);

        }

        dbjoin.close();
    }
}
