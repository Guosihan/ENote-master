package com.example.gsh.enote;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gsh on 2017/5/10.
 */

public class AddActivity extends AppCompatActivity {
    private String val;
    private Button btnSave;
    private Button btnDelect;
    private EditText edText;
    private ImageView addImgv;
    private VideoView addVideo;
    private NoteDB noteDB;
    private SQLiteDatabase dbWriter;
    private File phoneFile;
    private File  videoFile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        noteDB = new NoteDB(this);
        dbWriter = noteDB.getWritableDatabase();
        edText= (EditText) findViewById(R.id.ettext);
        val=getIntent().getStringExtra("flag");
        btnSave= (Button) findViewById(R.id.save);
        btnDelect= (Button) findViewById(R.id.delete);
        addImgv= (ImageView) findViewById(R.id.add_img);
        addVideo= (VideoView) findViewById(R.id.add_video);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDB();
                finish();
            }
        });
        btnDelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

        initView();
    }

    public void initView() {
        if (val.equals("1")) { // 文字
            addImgv.setVisibility(View.GONE);
            addVideo.setVisibility(View.GONE);
        }

    }



    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT, edText.getText().toString());
        cv.put(NoteDB.TIME, getTime());

        dbWriter.insert(NoteDB.TABLE_NAME, null, cv);
    }

    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);

        return str;
    }


}
