package com.example.gcsj3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gcsj3.TangyActivity.Scenic_Spot;
import com.example.gcsj3.tianActivity.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button spot = null;
    private Button hotel = null;
    private ImageButton loginButton = null;
    private BroadcastReceiver mBrodcaseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("hideLogin")){
                loginButton.setVisibility(View.GONE);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //注册广播接收登录成功
        IntentFilter filter = new IntentFilter();
        filter.addAction("hideLogin");
        registerReceiver(mBrodcaseReceiver, filter);
    }

    public void init(){
        spot = (Button) findViewById(R.id.spot);
        spot.setOnClickListener(this);
        hotel = (Button) findViewById(R.id.Hotel);
        hotel.setOnClickListener(this);
        loginButton = (ImageButton) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.spot:
                Intent intent = new Intent(MainActivity.this, Scenic_Spot.class);
                startActivity(intent);
                break;
            case R.id.Hotel:
                //跳转到酒店
                break;
            case R.id.loginButton:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBrodcaseReceiver);
    }
}
