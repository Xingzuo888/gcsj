package com.example.gcsj3.hotel;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gcsj3.R;
import com.example.gcsj3.adapter.HotelPictureAdapter;
import com.example.gcsj3.gson.hoteldetails.PicturesList;
import com.example.gcsj3.gson.hoteldetails.ShowapiResBodyHotelDetails;
import com.example.gcsj3.util.Utility;

import java.util.List;

/**
 * Created by Administrator on 2018/12/23.
 */

public class HotelPictureActivity extends AppCompatActivity {

    private Button backButton;
    private TextView titleContent;
    private HotelPictureAdapter adapter;
    private List<PicturesList> picturesLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_picture);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hotel_picrecyclerview);
        backButton = (Button) findViewById(R.id.title_left);
        titleContent = (TextView) findViewById(R.id.title_center);
        String picture = getIntent().getStringExtra("picture");
        if (picture != null) {
            ShowapiResBodyHotelDetails showapiResBodyHotelDetails = Utility.handleShowApiHotelDetailsResponse(picture);
            picturesLists = showapiResBodyHotelDetails.hotelDetailsData.picturesList;
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HotelPictureAdapter(picturesLists);
        recyclerView.setAdapter(adapter);
        titleContent.setText("图片");
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
}
