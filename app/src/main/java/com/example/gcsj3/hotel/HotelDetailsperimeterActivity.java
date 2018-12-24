package com.example.gcsj3.hotel;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gcsj3.R;
import com.example.gcsj3.gson.hoteldetails.PoiInfos;
import com.example.gcsj3.gson.hoteldetails.ShowapiResBodyHotelDetails;
import com.example.gcsj3.gson.hoteldetails.SubPoiInfos;
import com.example.gcsj3.util.Utility;

/**
 * Created by Administrator on 2018/12/23.
 */

public class HotelDetailsperimeterActivity extends AppCompatActivity {

    private ShowapiResBodyHotelDetails showapiResBodyhotelDetails;

    private Button backButton;
    private TextView titleContent;
    private TextView addressText;
    private TextView telText;
    private TextView instructionText;
    private TextView policyText;
    private LinearLayout linearlayout;
    private ScrollView perimeterlayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_addressandperimeter);
        backButton = (Button) findViewById(R.id.title_left);
        titleContent = (TextView) findViewById(R.id.title_center);
        addressText = (TextView) findViewById(R.id.hotel_detail_address);
        telText = (TextView) findViewById(R.id.hotel_detail_tel);
        instructionText = (TextView) findViewById(R.id.hotel_detail_instruction);
        policyText = (TextView) findViewById(R.id.hotel_detail_policy);
        linearlayout = (LinearLayout) findViewById(R.id.hotel_detail_linearlayout);
        perimeterlayout = (ScrollView) findViewById(R.id.perimeterlayout);
        String response = getIntent().getStringExtra("perimeter");
        if (response != null) {
            showapiResBodyhotelDetails = Utility.handleShowApiHotelDetailsResponse(response);
            show();
        }else {
            perimeterlayout.setVisibility(View.INVISIBLE);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void show() {
        linearlayout.removeAllViews();
        titleContent.setText(showapiResBodyhotelDetails.hotelDetailsData.chineseName);
        addressText.setText(showapiResBodyhotelDetails.hotelDetailsData.address);
        telText.setText(showapiResBodyhotelDetails.hotelDetailsData.tel);
        instructionText.setText(showapiResBodyhotelDetails.hotelDetailsData.instruction);
        StringBuilder policy = new StringBuilder();
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.children!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.children + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.pet!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.pet + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.arrivalDeparture!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.arrivalDeparture + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.requirements!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.requirements + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.depositPrepaid!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.depositPrepaid + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.checkOutTime!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.checkOutTime + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.checkInTime!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.checkInTime + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.cancel!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.cancel + "\n");
        }
        if (showapiResBodyhotelDetails.hotelDetailsData.policy.acceptCreditCards!=null){
            policy.append("•" + showapiResBodyhotelDetails.hotelDetailsData.policy.acceptCreditCards + "\n");
        }

        policyText.setText(policy);
        for (PoiInfos poiInfos : showapiResBodyhotelDetails.hotelDetailsData.poiInfosList) {
            View view = LayoutInflater.from(this).inflate(R.layout.hotel_perimeter_item, linearlayout, false);
            TextView typename = (TextView) view.findViewById(R.id.hotel_detail_perimetertype);
            TextView names = (TextView) view.findViewById(R.id.hotel_detail_perimetername);
            typename.setText(poiInfos.name);
            StringBuilder n = new StringBuilder();
            for (SubPoiInfos subPoiInfos : poiInfos.subPoiInfosList) {
                n.append(" 离 " + subPoiInfos.name + " " + subPoiInfos.distance + " 公里\n");
            }
            names.setText(n);
            linearlayout.addView(view);
        }
        perimeterlayout.setVisibility(View.VISIBLE);
    }


}
