package com.example.gsh.enote;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private NoteDB mNoteDB;
    private SQLiteDatabase mSQLiteDatabase;
    private Button textbtn;
    private Button btn_user;
    private ListView lv;
    private Intent intent;
    private MyAdapter mMyAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_user= (Button) findViewById(R.id.btn_login);
        Bmob.initialize(this, "78401053e1640ec9ea828aa14005d352");
        mNoteDB=new NoteDB(this);
        mSQLiteDatabase=mNoteDB.getWritableDatabase();
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        //addDB();
        initView();
    }

    private void initView() {
        lv= (ListView) findViewById(R.id.list);
        textbtn= (Button) findViewById(R.id.text);

        textbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });

    }
    public void selectDB() {
        cursor = mSQLiteDatabase.query(NoteDB.TABLE_NAME, null, null, null, null,
                null, null);
        mMyAdapter = new MyAdapter(this, cursor);
        lv.setAdapter(mMyAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

}




