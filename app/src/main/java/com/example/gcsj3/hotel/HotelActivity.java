package com.example.gcsj3.hotel;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcsj3.R;
import com.example.gcsj3.adapter.ListAdapter;
import com.example.gcsj3.gson.hotel.HotelList;
import com.example.gcsj3.gson.hotel.ShowapiResBodyHotel;
import com.example.gcsj3.util.HttpUtil;
import com.example.gcsj3.util.Utility;
import com.example.gcsj3.weather.ChooseCityActivity;
import com.example.gcsj3.weather.WeatherActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/21.
 */

public class HotelActivity extends AppCompatActivity implements View.OnClickListener {

    private final String showapi_appid = "83638";
    private final String showapi_sign = "c45adefbd34d4f27965cd8e6b4e9d561";

    private TextView weatherText;
    private Button backHostButton;
    private Button hotelPositionCityButton;
    private Button hotelQueryButton;
    private EditText hotelInputCityEditText;
    private ListView listView;
    private ShowapiResBodyHotel showapiResBodyHotel;
    private ListAdapter adapter;
    private String cityName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel);
        backHostButton = (Button) findViewById(R.id.title_left);
        hotelPositionCityButton = (Button) findViewById(R.id.hotel_position_city);
        hotelQueryButton = (Button) findViewById(R.id.hotel_query);
        hotelInputCityEditText = (EditText) findViewById(R.id.hotel_input_city);
        weatherText = (TextView) findViewById(R.id.title_right);

        backHostButton.setOnClickListener(this);
        hotelQueryButton.setOnClickListener(this);
        hotelPositionCityButton.setOnClickListener(this);
        weatherText.setOnClickListener(this);

        cityName = getIntent().getStringExtra("city");
        hotelPositionCityButton.setText(cityName+" ▼");
        weatherText.setText(cityName + "-天气");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(HotelActivity.this);
        String showApiText = prefs.getString("showapihotel",null);
        if (showApiText != null) { //有缓存情况下
            showapiResBodyHotel = Utility.handleShowApiHotelResponse(showApiText);
            if (showapiResBodyHotel.cityName.equals(cityName)) {//所在的城市是否与缓存的城市一样，一样就显示，不一样就向服务器发起请求
                adapter = new ListAdapter(this,R.layout.hotel_card,showapiResBodyHotel.hotelData.hotelLists);
            } else {
                requestHotel(cityName);
            }
        } else { //无缓存 ，向服务器请求数据
            requestHotel(cityName);
        }

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HotelList hotelList = showapiResBodyHotel.hotelData.hotelLists.get(position);
                Intent intent = new Intent(HotelActivity.this,HotelDetailsActivity.class);
                intent.putExtra("hoteId",hotelList.hotelId);
                intent.putExtra("chineseName",hotelList.chineseName);
                intent.putExtra("themeImage",hotelList.picture);
                startActivity(intent);
            }
        });
        

    }

    /**
     * 向服务器请求并处理数据
     * @param cityName
     */
    private void requestHotel(String cityName){
        String url = "http://route.showapi.com/1653-1?showapi_appid=" + showapi_appid +
                "&showapi_sign=" + showapi_sign + "&cityName=" + cityName;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HotelActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseContent = response.body().string();
                final ShowapiResBodyHotel showapiResBodyHotel1 = Utility.handleShowApiHotelResponse(responseContent);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (showapiResBodyHotel1 != null && "0".equals(showapiResBodyHotel1.ret_code)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(HotelActivity.this).edit();
                            editor.putString("showapihotel",responseContent);
                            editor.apply();
                            hotelPositionCityButton.setText(showapiResBodyHotel1.cityName+" ▼");
                            hotelInputCityEditText.setHint(showapiResBodyHotel1.cityName);
                            adapter = new ListAdapter(HotelActivity.this,R.layout.hotel_card,showapiResBodyHotel1.hotelData.hotelLists);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    HotelList hotelList = showapiResBodyHotel1.hotelData.hotelLists.get(position);
                                    Intent intent = new Intent(HotelActivity.this,HotelDetailsActivity.class);
                                    intent.putExtra("hoteId",hotelList.hotelId);
                                    intent.putExtra("chineseName",hotelList.chineseName);
                                    intent.putExtra("themeImage",hotelList.picture);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            Toast.makeText(HotelActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                        }
                        
                    }
                });
            }
        });
    }


    private void queryCity(){
        final String input = hotelInputCityEditText.getText().toString();
        int i;
        if (input != null && !input.equals("")) {
            requestHotel(input);
            Toast.makeText(HotelActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
        } else if (input.equals("")) {
            Toast.makeText(this, "您输入的信息为空", Toast.LENGTH_SHORT).show();
        }
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
            case R.id.hotel_query:
                queryCity();
                break;
            case R.id.title_right:
                Intent intent = new Intent(HotelActivity.this, WeatherActivity.class);
                intent.putExtra("choose","1");
                intent.putExtra("province", getIntent().getStringExtra("province"));
                intent.putExtra("city",getIntent().getStringExtra("city"));
                startActivity(intent);
                break;
            case R.id.hotel_position_city:
                Intent intent1 = new Intent(HotelActivity.this, ChooseCityActivity.class);
                startActivityForResult(intent1, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedDate = data.getStringExtra("data_return");
                    requestHotel(returnedDate);
                }
                break;
        }
    }

    /**
     * 处理点击输入框以外隐藏输入法
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] location = {0, 0};
            v.getLocationOnScreen(location);
            int left = location[0];
            int top = location[1];

            if (event.getX() < left || (event.getX() > left + v.getWidth())
                    || event.getY() < top || (event.getY() > top + v.getHeight())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
