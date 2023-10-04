package com.example.sqlite;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<Person> person_list = new ArrayList<>();
    RecyclerView recyclerView;
    PersonAdapter personAdapter;


    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        //리스트 보여줄 화면
        recyclerView = findViewById(R.id.recyclerview);

        //어뎁터 연결
        personAdapter = new PersonAdapter(ListData.this);

        //어뎁터 등록
        recyclerView.setAdapter(personAdapter);

        //레이아웃 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //DB 생성
        dbHelper = new DBHelper(this);

        //데이터 가져오기
        selectData();

    }


    @SuppressLint("NotifyDataSetChanged")
    private void selectData() {
        Cursor cursor = dbHelper.readAllData();
        while (cursor.moveToNext()) {
            Person person = new Person(cursor.getString(1), cursor.getString(2), cursor.getInt(0));

            //데이터 등록
            person_list.add(person);
            personAdapter.addItem(person);

            //적용
            personAdapter.notifyDataSetChanged();

        }
        cursor.close();
    }

}