package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;

public class write_DB_Diary extends AppCompatActivity {
    String mon, day;
    Integer Year, Month, Day;
    Calendar calendar;
    EditText editText_title, editText_dtext;
    TextView textView;
    RatingBar ratingBar;
    Button button_save, button;
    SQLiteDatabase diary;

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
        setContentView(R.layout.activity_write_db_diary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        editText_title = (EditText)findViewById(R.id.editText_title);
        editText_dtext = (EditText)findViewById(R.id.editText_dtext);
        textView = (TextView)findViewById(R.id.textView7);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        button = (Button)findViewById(R.id.button5);
        button_save = (Button)findViewById(R.id.button_save);


        calendar=Calendar.getInstance();
        Year=calendar.get(Calendar.YEAR);
        Month=calendar.get(Calendar.MONTH);
        Day=calendar.get(Calendar.DAY_OF_MONTH);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(write_DB_Diary.this, mDateSetListener, Year, Month, 1).show();
            }
        });


        DB_Diary helper = new DB_Diary(this);
        diary = helper.getWritableDatabase();

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=editText_title.getText().toString();
                String dtext=editText_dtext.getText().toString();
                float rating = ratingBar.getRating();
                if(Integer.toString(Month+1).length()<2)
                    mon = "0"+(Month+1);
                else
                    mon = ""+(Month+1);
                if(Integer.toString(Day).length()<2)
                    day="0"+Day;
                else
                    day = ""+Day;
                String date = Year+"-"+mon+"-"+day;

                String query="insert into dbDiary(title, dtext, rating, date) values('" +title + "', '" +dtext+ "', '"+ rating+"', '"+date+"')";

                diary.execSQL(query);
                diary.close();
                Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
                startActivity(intent);
            }
        });

    }
    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Year = year;
            Month = monthOfYear;
            Day = dayOfMonth;

            calendar.set(Calendar.YEAR, Year);
            calendar.set(Calendar.MONTH, Month);
            calendar.set(Calendar.DATE, Day);

            textView.setVisibility(View.VISIBLE);
            if(Integer.toString(Month+1).length()<2)
                mon = "0"+(Month+1);
            else
                mon = ""+(Month+1);
            if(Integer.toString(Day).length()<2)
                day="0"+Day;
            else
                day = ""+Day;
            textView.setText(String.format("%d-%s-%s", Year, mon, day));
        }
    };
}
