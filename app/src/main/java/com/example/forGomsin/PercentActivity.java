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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PercentActivity extends AppCompatActivity {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    TextView textView_B, textView_P, textView_C;
    Button button;
    Calendar enliDay = Calendar.getInstance();
    Calendar toDay = Calendar.getInstance();
    private ProgressBar progressBar;
    Integer tYear, tMonth, tDay, pastDay;
    ImageView imageView;
    Integer p;
    private long q = 24 * 60 * 60 * 1000, e, t, past;
    String cat, enli;

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
        switch (item.getItemId()) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView_B = (TextView) findViewById(R.id.Tbok);
        textView_P = (TextView)findViewById(R.id.Tpercent);
        textView_C = (TextView)findViewById(R.id.Tclass);
        button = (Button) findViewById(R.id.button6);



        DB_join helper = new DB_join(getApplicationContext());
        SQLiteDatabase dbjoin = helper.getReadableDatabase();

        Cursor cursor = dbjoin.rawQuery("select category, strftime('%Y-%m-%d', enlistmentdate) from dbjoin", null);
        // where _id <> 1
        while (cursor.moveToNext()) {
            cat = cursor.getString(0);
            enli = cursor.getString(1);
        }
        switch (cat) {
            case "육군":
                imageView.setImageResource(R.drawable.six);
                p = 545;
                break;
            case "해병":
                imageView.setImageResource(R.drawable.haebyung);
                p = 545;
                break;
            case "의무경찰":
            case "의경":
                imageView.setImageResource(R.drawable.ui);
                p = 545;
                break;
            case "공군":
                imageView.setImageResource(R.drawable.gong);
                p = 637;
                break;
            case "사회복무요원":
                imageView.setImageResource(R.drawable.six);
                p = 637;
                break;
            case "해군":
                imageView.setImageResource(R.drawable.hae);
                p = 607;
                break;
            case "해양의무경찰":
                imageView.setImageResource(R.drawable.ui);
                p = 607;
                break;
            case "의무소방원":
                imageView.setImageResource(R.drawable.six);
                p = 607;
                break;
        }
        try {
            Date date = dateFormat.parse(enli);
            enliDay.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tYear = toDay.get(Calendar.YEAR);
        tMonth = toDay.get(Calendar.MONTH);
        tDay = toDay.get(Calendar.DAY_OF_MONTH);

        e = enliDay.getTimeInMillis();
        t = toDay.getTimeInMillis();
        past = (t - e) / (24 * 60 * 60 * 1000);

        pastDay = (int) past + 1;

        progressBar.setMax(p);

        if (pastDay >= p) {
            progressBar.setVisibility(View.INVISIBLE);
            textView_B.setVisibility(View.INVISIBLE);
            textView_P.setVisibility(View.INVISIBLE);
            textView_C.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_C.setVisibility(View.VISIBLE);
                textView_P.setVisibility(View.VISIBLE);
                textView_B.setVisibility(View.VISIBLE);

                Double percent = (double)pastDay/p*100.0;
                if(pastDay<=60){
                    textView_C.setText("계급은 이병입니다.");
                }
                else if(pastDay>60 && pastDay<=242){
                    textView_C.setText("계급은 일병입니다.");
                }
                else if(pastDay>242 && pastDay<=424){
                    textView_C.setText("계급은 상병입니다.");
                }
                else {
                    textView_C.setText("계급은 병장입니다.");
                }
                textView_P.setText(String.format("현재 진행률 : %.2f",percent));
                ThreadToProgress threadToProgress = new ThreadToProgress();
                textView_B.setText(String.format("군대간지 %d일 째..", pastDay));
                threadToProgress.start();
            }
        });

        dbjoin.close();
    }

    private class ThreadToProgress extends Thread {
        public void run() {
            for (int i = 0; i <= p; i++) {
                progressBar.setProgress(i);
                if (i == pastDay) {
                    break;
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
