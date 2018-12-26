package com.example.gcsj3.tianActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gcsj3.R;
import com.example.gcsj3.tianActivity.SQL.SqlFactory;
import com.example.gcsj3.tianActivity.bean.User;

public class ForgetPWD extends AppCompatActivity implements View.OnClickListener{
    private EditText idcardFor = null;
    private EditText pwdFor1 = null;
    private EditText pwdFor2 = null;
    private Button confirm = null;
    private Button backlogin = null;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 300){
                boolean data = (boolean) msg.obj;
                if (data){
                    Toast.makeText(ForgetPWD.this, "更改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ForgetPWD.this, "更改失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        init();
    }
    public void init(){
        idcardFor = (EditText) findViewById(R.id.idcardFor);
        pwdFor1 = (EditText) findViewById(R.id.pwdFor1);
        pwdFor2 = (EditText) findViewById(R.id.pwdFor2);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
        backlogin = (Button) findViewById(R.id.backloginF);
        backlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:
                ForgetPWD();
                break;
            case R.id.backloginF:
                finish();
                break;
        }
    }
    public void ForgetPWD(){
        final String idcard = idcardFor.getText().toString().trim();
        final String pwd1 = pwdFor1.getText().toString().trim();
        final String pwd2 = pwdFor2.getText().toString().trim();
        if (idcard == null ||idcard.equals("") || pwd1 == null || pwd1.equals("") || pwd2 == null || pwd2.equals("")||!pwd1.equals(pwd2) || idcard.length() != 18){
            Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 300;
                    User user = new User();
                    user.setIdcard(idcard);
                    user.setPwd(pwd1);
                    boolean data = SqlFactory.getSql().updatePwd(user);
                    msg.obj = data;
                    mHandler.sendMessage(msg);
                }
            }).start();
        }
    }
}
