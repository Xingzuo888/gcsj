package com.example.gcsj3.hotel;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gcsj3.R;
import com.example.gcsj3.gson.hoteldetails.Facilities;
import com.example.gcsj3.gson.hoteldetails.ServicesList;
import com.example.gcsj3.gson.hoteldetails.ShowapiResBodyHotelDetails;
import com.example.gcsj3.util.Utility;

/**
 * Created by Administrator on 2018/12/23.
 */

public class HotelServicesActivity extends AppCompatActivity {

    private ShowapiResBodyHotelDetails showapiResBodyHotelDetails;

    private Button backButton;
    private TextView titleContent;
    private ScrollView servicesLayout;
    private TextView servicesNameText;
    private TextView facilitiesNameText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_services);
        backButton = (Button) findViewById(R.id.title_left);
        titleContent = (TextView) findViewById(R.id.title_center);
        servicesLayout = (ScrollView) findViewById(R.id.services_layout);
        servicesNameText = (TextView) findViewById(R.id.hotel_services_name);
        facilitiesNameText = (TextView) findViewById(R.id.hotel_facilities_name);
        String services = getIntent().getStringExtra("services");
        if (services != null) {
            showapiResBodyHotelDetails = Utility.handleShowApiHotelDetailsResponse(services);
            show();
        }else {
            servicesLayout.setVisibility(View.INVISIBLE);
        }
        titleContent.setText("服务/设施");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //返回上一个activity
                        Instrumentation inst = new Instrumentation();
                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    }
                }).start();
            }
        });
    }

    private void show() {
        StringBuilder ser = new StringBuilder();
        int s = 1;
        for (ServicesList servicesList : showapiResBodyHotelDetails.hotelDetailsData.servicesList) {
            if ("1".equals(servicesList.typeCode)) {
                if (s == showapiResBodyHotelDetails.hotelDetailsData.servicesList.size()) {
                    ser.append(servicesList.name);
                }else {
                    ser.append(servicesList.name + "、");
                }
            }
            s++;
        }
        if (ser == null) {
            ser.append("暂无其他服务");
        }
        servicesNameText.setText(ser);
        StringBuilder fac = new StringBuilder();
        int f = 1;
        for (Facilities facilities : showapiResBodyHotelDetails.hotelDetailsData.facilitiesList) {
            if ("4".equals(facilities.typeCode)) {
                if (f == showapiResBodyHotelDetails.hotelDetailsData.facilitiesList.size()) {
                    fac.append(facilities.name);
                }else {
                    fac.append(facilities.name + "、");
                }
            }
            f++;
        }
        if (fac == null) {
            fac.append("暂无其他服务");
        }
        facilitiesNameText.setText(fac);
        servicesLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
