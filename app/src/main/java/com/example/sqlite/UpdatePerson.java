package com.example.sqlite;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class UpdatePerson extends AppCompatActivity {
    EditText update_name, update_phone;
    String name, phone;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);

        update_name = findViewById(R.id.update_name);
        update_phone = findViewById(R.id.update_phone_number);

        //PersonAdapter 클래스 에서 intent 로 전달 받은 데이터 가져오기
        getIntentData();


        findViewById(R.id.person_update).setOnClickListener(view -> {
            //입력값 변수 담기
            name = update_name.getText().toString();
            phone = update_phone.getText().toString();

            //DB 객체 생성
            DBHelper dbHelper = new DBHelper(UpdatePerson.this);
            //DB 저장(수정)
            dbHelper.updatePerson(id, name, phone); //id값을 이용하여 업데이트

            //변경된 데이터 intent로 LisData 클래스 전달후 종료
            Intent intent = new Intent(getApplicationContext(), ListData.class);
            startActivity(intent);
            finish();

        });
    }

    private void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("phone")){

//        id = getIntent().getStringExtra("id");
        id = getIntent().getIntExtra("id", id);
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");

        //updatePerson 화면에 받은 데이터 EditText 에 다시 넣기
        update_name.setText(name);
        update_phone.setText(phone);

        }
    }

}