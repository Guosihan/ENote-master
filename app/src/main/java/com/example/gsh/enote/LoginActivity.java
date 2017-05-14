package com.example.gsh.enote;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by gsh on 2017/5/14.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText edPhone;
    private EditText edCode;
    private Button btngetCode;
    private Button btnSure;
    private Button btnnoSure;
    private String phone;
    private String code;
    private Handler handler;
    private Runnable runnable;
    private int num=60;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edPhone= (EditText) findViewById(R.id.ed_phone);
        edCode= (EditText) findViewById(R.id.ed_phone);
        btngetCode= (Button) findViewById(R.id.btn_getCode);
        btnSure= (Button) findViewById(R.id.btn_ok);
        btnnoSure= (Button) findViewById(R.id.btn_no);

        code=edCode.getText().toString().trim();
        btngetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=edPhone.getText().toString().trim();
                //initHandler();
                restart(v);
                BmobSMS.requestSMSCode(phone,"九阴真经", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId,BmobException ex) {
                        if(ex==null){//验证码发送成功
                            Toast.makeText(LoginActivity.this,"hahahha",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public void oncancel(View v) {
        timer.cancel();
    }

    /**
     * 开始倒计时
     * @param v
     */
    public void restart(View v) {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(10000, 1000) {


        public void onTick(long millisUntilFinished) {
            btngetCode.setText((millisUntilFinished / 1000) + "秒后可重发");
        }


        public void onFinish() {
            btngetCode.setEnabled(true);
            btngetCode.setText("获取验证码");
        }
    };
}
