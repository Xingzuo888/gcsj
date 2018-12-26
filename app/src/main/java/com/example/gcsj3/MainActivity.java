package com.example.gcsj3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.gcsj3.TangyActivity.Scenic_Spot;
import com.example.gcsj3.hotel.HotelActivity;
import com.example.gcsj3.tianActivity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public LocationClient mLocationClient;
    private String city;
    private String province;
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
        //初始化定位及注册定位监听
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_main);

        //获取权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permissions,1);
        }else {
            requestLocation();
        }

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
                Intent intent2 = new Intent(MainActivity.this, HotelActivity.class);
                intent2.putExtra("province",province);
                intent2.putExtra("city",city);
                startActivity(intent2);
                break;
            case R.id.loginButton:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    //启动定位
    public void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    //初始化定位的配置
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int results : grantResults) {
                        if (results != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this,"拒绝权限部分功能就无法正常使用",Toast.LENGTH_SHORT).show();
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBrodcaseReceiver);
        //停止定位
        mLocationClient.stop();
    }

    //监听及获取定位信息
    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            province = bdLocation.getProvince();
            province = province.split("省")[0];
            city = bdLocation.getCity();
            city = city.split("市")[0];

        }
    }

}
