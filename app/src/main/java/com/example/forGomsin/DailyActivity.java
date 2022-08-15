package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class DailyActivity extends AppCompatActivity {
    CalendarView calendarView;
    String date_diary;
    String sDate, sMonth;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        calendarView = (CalendarView)findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                final String[] item = {"일기 쓰기","일기 확인"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DailyActivity.this);
                builder.setTitle("Today is "+i+". "+(i1+1)+". "+i2+".");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            Intent intent = new Intent(getApplicationContext(), write_DB_Diary.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent2 = new Intent(getApplicationContext(), Read_DB_Diary.class);
                            startActivity(intent2);
                        }
                    }
                });
                builder.setPositiveButton("닫기",null);
                builder.show();
                if(Integer.toString(i2).length()<2)
                    sDate = "0"+i2;
                else
                    sDate = ""+i2;

                if(Integer.toString(i1+1).length()<2)
                    sMonth="0"+(i1+1);
                else
                    sMonth = ""+(i1+1);

                date_diary=i+"-"+sMonth+"-"+sDate;
                Toast.makeText(getApplicationContext(), date_diary,Toast.LENGTH_LONG).show();
            }
        });
    }
}
