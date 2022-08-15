package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tips extends AppCompatActivity {
    ImageView button1,button2,button3, button4;
    LinearLayout mainContainer;
    FragmentManager fr;
    TextView textView;
    OneFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;
    FourFragment fourFragment;
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
        setContentView(R.layout.activity_tips);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        button1 = (ImageView) findViewById(R.id.button);
        button2 = (ImageView) findViewById(R.id.button2);
        button3 = (ImageView) findViewById(R.id.button3);
        button4 = (ImageView)findViewById(R.id.button4);

        fr = getSupportFragmentManager();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fr.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, oneFragment);
                ft.commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fr.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, twoFragment);
                ft.commit();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fr.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, threeFragment);
                ft.commit();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fr.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, fourFragment);
                ft.commit();            }
        });
    }
}
