package com.example.gcsj3.TangyActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;
import com.example.gcsj3.Parse.JSONFactory;
import com.example.gcsj3.R;
import com.example.gcsj3.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class DetailScenic extends AppCompatActivity {
    private String scenicId = "";
    private DetailShowapiResBodyBean scenicDetail;
    private Handler handler = null;
    private ImageView image = null;
    private TextView name = null;
    private TextView city = null;
    private TextView addr = null;
    private TextView time = null;
    private TextView traffic = null;
    private TextView recommend = null;
    private TextView description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scenic);
        init();
        Intent intent = getIntent();
        scenicId = intent.getStringExtra("scenicId");
        handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String JsonData = JSONFactory.getJSONFactory().getAPIRespDetailString(scenicId);
                scenicDetail = JSONFactory.getJSONFactory().dealJSONDetail(JsonData);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(scenicDetail.getScenicName());
                        city.setText(scenicDetail.getCityName());
                        addr.setText(scenicDetail.getScenicAddress());
                        time.setText(scenicDetail.getOpenTime());
                        traffic.setText(scenicDetail.getTrafficBus());
                        recommend.setText(scenicDetail.getRecommend());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            description.setText(Html.fromHtml(scenicDetail.getScenicDescription(), Html.FROM_HTML_MODE_LEGACY));
                        }else {
                            description.setText(Html.fromHtml(scenicDetail.getScenicDescription()));
                        }
                        final String picUrl = scenicDetail.getDefaultPic();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HttpUtil.SendOkHttpRequest(picUrl, new okhttp3.Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        byte[] bytes = response.body().bytes();
                                        final Bitmap bit = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                                        image.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                image.setImageBitmap(bit);
                                            }
                                        });
                                    }
                                });
                            }
                        }).start();
                    }
                });
            }
        }).start();
    }

    public void init(){
        image = (ImageView) findViewById(R.id.scenicDetail_image);
        name = (TextView) findViewById(R.id.scenicDetail_name);
        city = (TextView) findViewById(R.id.scenicDetail_city);
        addr = (TextView) findViewById(R.id.scenicDetail_addr);
        time = (TextView) findViewById(R.id.scenicDetail_time);
        traffic = (TextView) findViewById(R.id.scenicDetail_traffic);
        recommend = (TextView) findViewById(R.id.scenicDetail_recommend);
        description = (TextView) findViewById(R.id.scenicDetail_description);
    }
}
