package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.EventListener;

public class write_DB_join extends AppCompatActivity {
    EditText editText_name, editText_BF, editText_cat, editText_meet, editText_enli, editText_pwd;
    Button button_insert;
    SQLiteDatabase joindb;
    String[] items = {"육군","해병","의무경찰","공군","사회복무요원","해군","해양의무경찰","의무소방원"};
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
                Intent intent0 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent0);
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
        setContentView(R.layout.activity_write__db_join);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        editText_name = (EditText)findViewById(R.id.editText_name);
        editText_BF = (EditText)findViewById(R.id.editText_BF);
        editText_cat = (EditText)findViewById(R.id.editText_cat);
        editText_meet = (EditText)findViewById(R.id.editText_meet);
        editText_enli = (EditText)findViewById(R.id.editText_enli);
        editText_pwd = (EditText)findViewById(R.id.editText_pwd);
        button_insert =(Button)findViewById(R.id.button_insert);

        final AutoCompleteTextView auto = (AutoCompleteTextView)findViewById(R.id.editText_cat);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        auto.setAdapter(adapter);

        editText_cat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editText_cat.setText("");
                return false;
            }
        });

        DB_join helper = new DB_join(this);
        joindb = helper.getWritableDatabase();

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText_name.getText().toString();
                String BF_name=editText_BF.getText().toString();
                String cat=editText_cat.getText().toString();
                String meet = editText_meet.getText().toString();
                String enli = editText_enli.getText().toString();
                String pwd = editText_pwd.getText().toString();

                String query = "insert into dbjoin(name, BF_name, category, meetingdate, enlistmentdate, password) values('" +name + "', '" +BF_name+ "', '"+ cat+"','" +meet + "','" +enli +"','" +pwd +"')";

                joindb.execSQL(query);
                joindb.close();
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
