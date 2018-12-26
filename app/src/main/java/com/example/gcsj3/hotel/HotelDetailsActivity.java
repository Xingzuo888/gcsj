package com.example.gcsj3.hotel;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gcsj3.R;
import com.example.gcsj3.gson.hoteldetails.Facilities;
import com.example.gcsj3.gson.hoteldetails.PoiInfos;
import com.example.gcsj3.gson.hoteldetails.ServicesList;
import com.example.gcsj3.gson.hoteldetails.ShowapiResBodyHotelDetails;
import com.example.gcsj3.gson.hoteldetails.SubPoiInfos;
import com.example.gcsj3.util.HttpUtil;
import com.example.gcsj3.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/23.
 */

public class HotelDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private final String showapi_appid = "83638";
    private final String showapi_sign = "c45adefbd34d4f27965cd8e6b4e9d561";

    private ScrollView hotelDetailsLayout;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private ImageView hotelImage;
    private ImageView hotelDetailsImage1;
    private ImageView hotelDetailsImage2;
    private ImageView hotelDetailsImage3;
    private TextView themeText;
    private TextView starText;
    private TextView addressandperimeterText;
    private TextView dateText;
    private TextView facilitiesandservicesText;
    private TextView muchpicText;
    private Button backButton;
    private TextView titleCenterText;

    private String hotelId;
    private String chineseName;
    private String themeImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_details);
        hotelDetailsLayout = (ScrollView) findViewById(R.id.hotel_detail);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.hotel_detail_layout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.hotel_detail_layout2);
        hotelImage = (ImageView) findViewById(R.id.theme_image);
        hotelDetailsImage1 = (ImageView) findViewById(R.id.hotel_detail_image1);
        hotelDetailsImage2 = (ImageView) findViewById(R.id.hotel_detail_image2);
        hotelDetailsImage3 = (ImageView) findViewById(R.id.hotel_detail_image3);
        themeText = (TextView) findViewById(R.id.hotel_detail_theme);
        starText = (TextView) findViewById(R.id.hotel_detail_star);
        addressandperimeterText = (TextView) findViewById(R.id.hotel_detail_addressandperimeter);
        dateText = (TextView) findViewById(R.id.hotel_detail_date);
        facilitiesandservicesText = (TextView) findViewById(R.id.hotel_detail_facilitiesandservices);
        muchpicText = (TextView) findViewById(R.id.hotel_detail_muchpic);
        backButton = (Button) findViewById(R.id.title_left);
        titleCenterText = (TextView) findViewById(R.id.title_center);

        backButton.setOnClickListener(this);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        muchpicText.setOnClickListener(this);
        titleCenterText.setText("详细信息");

        //获取传入的信息
        hotelId = getIntent().getStringExtra("hoteId");
        chineseName = getIntent().getStringExtra("chineseName");
        themeImage = getIntent().getStringExtra("themeImage");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String showApiText = prefs.getString("showapihoteldetails",null);
        if (showApiText != null) {
            ShowapiResBodyHotelDetails hotelDetails = Utility.handleShowApiHotelDetailsResponse(showApiText);
            if (hotelDetails.hotelDetailsData.chineseName.equals(chineseName)) {
                showHotelDetailsInfo(hotelDetails);
            }else {
                requestHotelDetails(hotelId);
            }
        }else {
            requestHotelDetails(hotelId);
            hotelDetailsLayout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 向服务器请求并处理数据
     * @param hotelid
     */
    private void requestHotelDetails(String hotelid) {
        String url = "http://route.showapi.com/1653-3?showapi_appid=" + showapi_appid +
                "&showapi_sign=" + showapi_sign + "&hotelId=" + hotelid;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HotelDetailsActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseContent = response.body().string();
                final ShowapiResBodyHotelDetails showapiResBody_hotelDetails = Utility.handleShowApiHotelDetailsResponse(responseContent);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(HotelDetailsActivity.this).edit();
                        editor.putString("showapihoteldetails",responseContent);
                        editor.apply();
                        showHotelDetailsInfo(showapiResBody_hotelDetails);
                    }
                });
            }
        });
    }

    /**
     * 显示信息
     * @param showapiResBody_hotelDetails
     */
    private void showHotelDetailsInfo(ShowapiResBodyHotelDetails showapiResBody_hotelDetails) {
        StringBuilder add = new StringBuilder();
        Glide.with(HotelDetailsActivity.this).load(themeImage).into(hotelImage);
        themeText.setText(showapiResBody_hotelDetails.hotelDetailsData.chineseName);
        starText.setText(showapiResBody_hotelDetails.hotelDetailsData.starName);
        dateText.setText(showapiResBody_hotelDetails.hotelDetailsData.debutYear + "年开业  " +
                showapiResBody_hotelDetails.hotelDetailsData.decorateDate + "年装修");
        StringBuilder addressandperimeter = new StringBuilder();
        addressandperimeter.append(showapiResBody_hotelDetails.hotelDetailsData.address);
        for (PoiInfos poiInfos : showapiResBody_hotelDetails.hotelDetailsData.poiInfosList) {
            if ("地铁站".equals(poiInfos.name)) {
                int i=1;
                for (SubPoiInfos subPoiInfos : poiInfos.subPoiInfosList) {
                    if (i == poiInfos.subPoiInfosList.size()) {
                        add.append(subPoiInfos.name);
                    }else {
                        add.append(subPoiInfos.name + "、");
                    }
                    i++;
                }
                add.append("地铁站");
            }
        }
        if (add != null && add.length() > 0) {
            addressandperimeter.append("(" + add + ")");
        }else {
            addressandperimeter.substring(0,addressandperimeter.length()-1);
        }
        addressandperimeterText.setText(addressandperimeter.toString());
        add.delete(0,add.length());
        StringBuilder facilitiesandservices = new StringBuilder();
        for (Facilities facilities : showapiResBody_hotelDetails.hotelDetailsData.facilitiesList) {
            if ("127".equals(facilities.code)) {
                add.append(" 客房WiFi免费 ");
                break;
            }
        }
        for (ServicesList servicesList : showapiResBody_hotelDetails.hotelDetailsData.servicesList) {
            if ("104".equals(servicesList.code) || "99".equals(servicesList.code)) {
                add.append(" " + servicesList.name + " ");
            }
        }
        facilitiesandservices.append(add);
        facilitiesandservicesText.setText(facilitiesandservices);
        Glide.with(HotelDetailsActivity.this).load(showapiResBody_hotelDetails.hotelDetailsData.picturesList.get(0).path).into(hotelDetailsImage1);
        Glide.with(HotelDetailsActivity.this).load(showapiResBody_hotelDetails.hotelDetailsData.picturesList.get(1).path).into(hotelDetailsImage2);
        Glide.with(HotelDetailsActivity.this).load(showapiResBody_hotelDetails.hotelDetailsData.picturesList.get(2).path).into(hotelDetailsImage3);
        hotelDetailsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //返回上一个activity
                        Instrumentation inst = new Instrumentation();
                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    }
                }).start();
                break;
            case R.id.hotel_detail_layout1:
                SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
                String showApiText = prefs1.getString("showapihoteldetails",null);
                if (showApiText != null) {
                    Intent intent = new Intent(this,HotelDetailsperimeterActivity.class);
                    intent.putExtra("perimeter",showApiText);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"暂无更多信息",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hotel_detail_layout2:
                SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
                String services = prefs2.getString("showapihoteldetails",null);
                if (services != null) {
                    Intent intent = new Intent(this,HotelServicesActivity.class);
                    intent.putExtra("services",services);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"暂无更多信息",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hotel_detail_muchpic:
                SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
                String picture = prefs3.getString("showapihoteldetails",null);
                if (picture != null) {
                    Intent intent = new Intent(this,HotelPictureActivity.class);
                    intent.putExtra("picture",picture);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"暂无更多图片",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
