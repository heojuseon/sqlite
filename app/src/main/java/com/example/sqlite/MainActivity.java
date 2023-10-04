package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    EditText ename, ephone;
    ImageView user;
    Button save_btn, list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findid();

//        insertData();

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력값 변수 담기
                String name = ename.getText().toString();
                String phone = ephone.getText().toString();

                //DB 객체 생성
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                //DB 저장
                dbHelper.addPerson(name, phone);
            }
        });


        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListData.class);
                startActivity(intent);
            }
        });
    }


    private void findid() {
        ename = findViewById(R.id.name);
        ephone = findViewById(R.id.phone_number);
        user = findViewById(R.id.icon);

        save_btn = findViewById(R.id.save);
        list_btn = findViewById(R.id.list);
    }
}