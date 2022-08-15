package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    Button button_ok, button_w, button_w2, button_wat, button_tip;
    TextView textView_T, textView_M, textView_D, textView_E, tv_home, tv_percent, tv_tip, tv_today,tv_today2, tv_write, tv_read;
    ImageView imageView;
    LinearLayout linearLayout;
    String cat, enli, meet, date;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar meetDay = Calendar.getInstance();
    Calendar toDay = Calendar.getInstance();
    Calendar enliDay = Calendar.getInstance();
    private int tYear;
    private int tMonth;
    private int tDay;

    private int dYear=1;
    private int dMonth=1;
    private int dDay=1;

    private long d;
    private long t;
    private long r;
    private long p=24*60*60*1000;
    private long m;
    private long mr;

    private int resultNumber=0;
    private int resultNumber2=0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

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
                drawerLayout.openDrawer(drawerView);
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
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        tv_today = (TextView)findViewById(R.id.tv_today);
        tv_today2 = (TextView)findViewById(R.id.tv_today2);
        tv_write = (TextView)findViewById(R.id.tv_write);
        tv_read = (TextView)findViewById(R.id.tv_read);
        tv_home = (TextView)findViewById(R.id.tv_home);
        tv_percent = (TextView)findViewById(R.id.tv_percent);
        tv_tip = (TextView)findViewById(R.id.tv_tip);
        button_ok = (Button)findViewById(R.id.button_ok);
        button_w = (Button)findViewById(R.id.button_w);
        button_w2 = (Button)findViewById(R.id.button_w2);
        button_wat = (Button)findViewById(R.id.button_wat);
        button_tip = (Button)findViewById(R.id.button_tip);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout3);
        textView_T = (TextView)findViewById(R.id.Ttoday);
        textView_M =(TextView)findViewById(R.id.Tmeet);
        textView_D = (TextView)findViewById(R.id.Tdday);
        textView_E = (TextView)findViewById(R.id.Tend);
        imageView = (ImageView)findViewById(R.id.cat_char);

        drawerLayout.setDrawerListener(listen);

        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });
        tv_percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PercentActivity.class);
                startActivity(intent);
            }
        });
        tv_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Tips.class);
                startActivity(intent);
            }
        });
        tv_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
                startActivity(intent);
            }
        });
        tv_today2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Read_today_Activity.class);
                startActivity(intent);
            }
        });
        tv_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), write_DB_Diary.class);
                startActivity(intent);
            }
        });
        tv_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Read_DB_Diary.class);
                startActivity(intent);
            }
        });
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PercentActivity.class);
                startActivity(intent);
            }
        });
        button_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), write_DB_Diary.class);
                startActivity(intent);
            }
        });
        button_w2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), write_DB_Diary.class);
                startActivity(intent);
            }
        });
        button_wat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Read_DB_Diary.class);
                startActivity(intent);
            }
        });
        button_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Tips.class);
                startActivity(intent);
            }
        });

        DB_join helper = new DB_join(getApplicationContext());
        SQLiteDatabase dbjoin = helper.getReadableDatabase();

        Cursor cursor = dbjoin.rawQuery("select name, BF_name, category, strftime('%Y-%m-%d', meetingdate),strftime('%Y-%m-%d', enlistmentdate), password from dbjoin where _id <> 1", null);
        //where _id <> 1
        while (cursor.moveToNext()) {
            cat = cursor.getString(2);
            meet = cursor.getString(3);
            enli = cursor.getString(4);
        }
        switch (cat){
            case "육군" :
                imageView.setImageResource(R.drawable.six);
                p = p * 545;
                break;
            case "해병" :
                imageView.setImageResource(R.drawable.haebyung);
                p = p * 545;
                break;
            case "의무경찰" :
            case "의경" :
                imageView.setImageResource(R.drawable.ui);
                p = p * 545;
                break;
            case "공군" :
                imageView.setImageResource(R.drawable.gong);
                p = p * 637;
                break;
            case "사회복무요원" :
                imageView.setImageResource(R.drawable.six);
                p = p * 637;
                break;
            case "해군" :
                imageView.setImageResource(R.drawable.hae);
                p = p * 607;
                break;
            case "해양의무경찰" :
                imageView.setImageResource(R.drawable.ui);
                p = p * 607;
                break;
            case "의무소방원" :
                imageView.setImageResource(R.drawable.six);
                p = p * 607;
                break;
        }


        try {
            Date date = dateFormat.parse(enli);
            enliDay.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Date date = dateFormat.parse(meet);
            meetDay.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tYear = toDay.get(Calendar.YEAR);
        tMonth = toDay.get(Calendar.MONTH);
        tDay = toDay.get(Calendar.DAY_OF_MONTH);

        d = enliDay.getTimeInMillis();
        t = toDay.getTimeInMillis();
        m = meetDay.getTimeInMillis();
        r=((d+p)-t)/(24*60*60*1000);
        mr=(t-m)/(24*60*60*1000);

        final Long EndD = d+p;

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = (String)simpleDateFormat.format(new Timestamp(EndD));

        resultNumber=(int)r+1;
        resultNumber2 = (int)mr+1;
        updateDisplay();

        dbjoin.close();
    }

    private void updateDisplay(){

        textView_T.setText(String.format("오늘은 %d년 %d월 %d일",tYear, tMonth+1,tDay));
        textView_M.setText(String.format("우리 만난지 %d일", resultNumber2));

        if(resultNumber>=0){
            textView_D.setText(String.format("꽃신 신으려면 아직\r\nD-%d", resultNumber));
            textView_E.setText(String.format("전역일은\r\n%s",date));
        }
        else{
            int absR=Math.abs(resultNumber);
            textView_D.setText(String.format("꽃신 신은지 \r\nD+%d", absR+1));
            textView_E.setText(String.format("전역일은\r\n%s",date));

        }
    }

    DrawerLayout.DrawerListener listen = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };


}

