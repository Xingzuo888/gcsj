package com.example.gcsj3.tianActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText accountReg = null;
    private EditText pwdReg = null;
    private EditText idcardReg = null;
    private Button register = null;
    private Button backLogin = null;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 200){
                boolean data = (boolean) msg.obj;
                if (data){
                    Intent intent = new Intent();
                    intent.putExtra("uname", accountReg.getText().toString().trim());
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    public void init(){
        accountReg = (EditText) findViewById(R.id.accountReg);
        pwdReg = (EditText) findViewById(R.id.pwdReg);
        idcardReg = (EditText) findViewById(R.id.idcardReg);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        backLogin = (Button) findViewById(R.id.backlogin);
        backLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                register();//注册成功
                break;
            case R.id.backlogin:
                finish();
                break;
        }
    }

    public void register(){
        final String accountString = accountReg.getText().toString().trim();
        final String idcard = idcardReg.getText().toString().trim();
        final String pwdString = pwdReg.getText().toString().trim();
        if (accountString == null ||accountString.equals("") || pwdString == null || pwdString.equals("") || idcard == null || idcard.equals("")||idcard.length()!=18){
            Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 200;
                    User user = new User();
                    user.setIdcard(idcard);
                    user.setUsername(accountString);
                    user.setPwd(pwdString);
                    boolean data = SqlFactory.getSql().addUser(user);
                    msg.obj = data;
                    mHandler.sendMessage(msg);
                }
            }).start();
        }
    }
}
