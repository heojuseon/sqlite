package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DBNAME = "person";
    public static final String TABLENAME = "person_list";
    public static final int VER = 1;

    public DBHelper(Context context) {
        super(context, DBNAME, null, VER);
        this.context = context;
    }


    //Table 생성 작업
    @Override
    public void onCreate(SQLiteDatabase db) {
        //table 생성 쿼리문
        String query = "create table " + TABLENAME + "(id integer primary key, name text, phone text)";
        db.execSQL(query);
    }

    //Table update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLENAME + "";    // 동일한 테이블 이름 존재시 삭제 쿼리
        db.execSQL(query);
    }

    //전체 목록 select 쿼리
    @SuppressLint("Recycle")
    public Cursor readAllData() {
        String query = "select * from " + TABLENAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //insert 쿼리
    public void addPerson(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //insert 문 사용시 생성

        cv.put("name", name);
        cv.put("phone", phone);
//        cv.put("icon", icon);

        long result = db.insert(TABLENAME, null, cv);   //insert 문 관련 해서 학습
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    //update 쿼리
    public void updatePerson(int id, String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //수정할 값
        cv.put("name", name);
        cv.put("phone", phone);

        long result = db.update(TABLENAME, cv, "id=?", new String[]{String.valueOf(id)});
        if (result == -1) {
            Toast.makeText(context, "업데이트 실패", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "업데이트 성공", Toast.LENGTH_SHORT).show();
        }
    }


    public void deletePerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLENAME, "id=?", new String[]{String.valueOf(id)});
        if (result == -1) {
            Toast.makeText(context, "삭제 실패", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "삭제 성공", Toast.LENGTH_SHORT).show();
        }
    }
}
