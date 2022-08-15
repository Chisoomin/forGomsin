package com.example.forGomsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn_join, btn_login, btn_confirm;
    View dialogView;
    Cursor cursor;
    String pwd;
    Integer id;
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
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        btn_join = (Button)findViewById(R.id.btn_join);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_confirm=(Button)findViewById(R.id.btn_confirm);

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), write_DB_join.class);
                startActivity(intent);
            }
        });

        DB_join helper = new DB_join(getApplicationContext());
        SQLiteDatabase dbjoin = helper.getReadableDatabase();

        cursor = dbjoin.rawQuery("select password, _id from dbjoin", null);

        while (cursor.moveToNext()) {
            pwd = cursor.getString(0);
            id = cursor.getInt(1);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(getApplicationContext(), R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("비빌번호입력");
                dlg.setView(dialogView);
                dlg.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText pw = (EditText)dialogView.findViewById(R.id.editText);
                        if(pw.getText().toString().equals(pwd)){
                            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(intent);
                        }
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        if(id>1){
            btn_join.setVisibility(View.INVISIBLE);
            btn_confirm.setVisibility(View.VISIBLE);
        }
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(getApplicationContext(), R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("비빌번호입력");
                dlg.setView(dialogView);
                dlg.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText pw = (EditText)dialogView.findViewById(R.id.editText);
                        if(pw.getText().toString().equals(pwd)){
                            Intent intent = new Intent(getApplicationContext(), Read_DB_join.class);
                            startActivity(intent);
                        }
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }
}
