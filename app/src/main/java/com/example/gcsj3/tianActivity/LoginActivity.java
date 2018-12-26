package com.example.gcsj3.tianActivity;

import android.content.Intent;
import android.net.Uri;
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

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button zuce;
    private Button login;
    private EditText account;
    private EditText pwd;
    private Button forgetpwd;
    private Button KF;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100){//根据用户名查找用户
                Bundle bundle = msg.getData();
                int i = 0;//判断是否正确登录
                String account = bundle.getString("account");
                String pwd = bundle.getString("pwd");
                List<User> userList = (List<User>) msg.obj;
                for (User user:userList){
                    if (user.getUsername().equals(account) && user.getPwd().equals(pwd)){
                        i = 1;
                    }
                }
                if (i == 1){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setAction("hideLogin");
                    sendBroadcast(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public void init(){
        zuce = (Button) findViewById(R.id.zuce);//变量初始化
        zuce.setOnClickListener(this);//设置监听
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        account = (EditText) findViewById(R.id.account);
        pwd = (EditText) findViewById(R.id.pwd);
        forgetpwd = (Button) findViewById(R.id.forgetpws);
        forgetpwd.setOnClickListener(this);
        KF = (Button) findViewById(R.id.KF);
        KF.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zuce:
                Intent intentZC = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intentZC, 1);//
                //点击注册后的操作
                break;
            case R.id.login:
                loginMain();
                //点击登录后的操作
                break;
            case R.id.forgetpws:
                Intent intentFP = new Intent(LoginActivity.this, ForgetPWD.class);
                startActivity(intentFP);
                //点击忘记密码的操作
                break;
            case R.id.KF:
                //点击客服的操作
                Intent intentKF = new Intent(Intent.ACTION_DIAL);
                intentKF.setData(Uri.parse("tel:10086"));
                startActivity(intentKF);
                break;
        }
    }
    public void loginMain(){
        final String accountString = account.getText().toString().trim();
        final String pwdString = pwd.getText().toString().trim();
        if (accountString == null ||accountString.equals("") || pwdString == null || pwdString.equals("")){
            Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {//进行耗时操作。开始一个线程
                    Message msg = new Message();
                    msg.what = 100;
                    List<User> userList = SqlFactory.getSql().findByName(accountString);
                    Bundle bundle = new Bundle();
                    bundle.putString("account", accountString);
                    bundle.putString("pwd", pwdString);
                    msg.setData(bundle);
                    msg.obj = userList;
                    mHandler.sendMessage(msg);
                }
            }).start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String accountReg = data.getStringExtra("uname");
                    account.setText(accountReg);
                    account.setSelection(accountReg.length());
                }
                break;
            default:
                break;
        }
    }
}
