package com.example.gcsj3.TangyActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcsj3.Bean.ResultBean;
import com.example.gcsj3.LitePal.LitePalFactory;
import com.example.gcsj3.Parse.JSONFactory;
import com.example.gcsj3.R;
import com.example.gcsj3.Show.MyAdapterScenic;
import com.example.gcsj3.weather.ChooseCityActivity;
import com.example.gcsj3.weather.WeatherActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static com.example.gcsj3.Parse.JSONFactory.getJSONFactory;

public class Scenic_Spot extends AppCompatActivity implements View.OnClickListener{

    public static String page = "1";
    public static String cityName = null;
    private EditText input_city = null;
    private ListView iv = null;
    private ImageView home = null;
    private Button cityButton = null;
    private Button lookfor = null;
    private TextView weatherText = null;
    Handler handler = null;
    MyAdapterScenic myAdapterScenic = null;
    List<ResultBean> resultList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic__spot);
        init();
        cityName = getIntent().getStringExtra("city");
        if (cityName == null){
            cityName = "昆明";
            Toast.makeText(this, "定位失败", Toast.LENGTH_SHORT).show();
        }
        cityButton.setText(cityName);
        weatherText.setText(cityName);
        //创建LitePal
        LitePal.getDatabase();
        //读取本地的page
        //page = FileFactory.getFileFactory().getFile(this);
        SharedPreferences preferences = getSharedPreferences("page", MODE_PRIVATE);
        page = preferences.getString("page", "1");

        if (page.equals("1")){
            showInter(cityName);
        }
        Log.i("Mainc", page);
        if (!isNetworkAvailable()){
            Toast.makeText(this, "网络不给力", Toast.LENGTH_SHORT).show();
        }
        //读取本地的Scenic信息
        List<ResultBean>  data= LitePalFactory.getLitePalProxy().getScenicLitePal();
        //合并数据
        resultList.addAll(data);
        handler = new Handler();
        myAdapterScenic = new MyAdapterScenic(this,resultList);
        iv.setAdapter(myAdapterScenic);
        //判断是否有网络
    }

    public void init() {
        weatherText = (TextView) findViewById(R.id.weather_right);
        input_city = (EditText) findViewById(R.id.input_city);
        lookfor = (Button) findViewById(R.id.lookfor);
        home = (ImageView) findViewById(R.id.backhome);
        iv = (ListView) findViewById(R.id.scenic_ListView);
        cityButton = (Button) findViewById(R.id.city_button);
        home.setOnClickListener(this);
        cityButton.setOnClickListener(this);
        weatherText.setOnClickListener(this);
        lookfor.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedDate = data.getStringExtra("data_return");
                    cityButton.setText(returnedDate);
                    weatherText.setText(returnedDate);
                    showInter(returnedDate);
                }
                break;
        }
    }

    public void showInter(final String city){

        //adapter
        new Thread(new Runnable() {
            @Override
            public void run() {
                //联网下
                //1.请求API接口数据
                String Jsondata = getJSONFactory().getAPIRespString(city, page, "10");
                Log.i("Mainc", Jsondata);
                //2.处理数据
                List<ResultBean> data= JSONFactory.getJSONFactory().dealJSON(Jsondata);
                if (data != null){
                    //保存page
                    int pageI = Integer.parseInt(page);
                    pageI++;
                    String pageS = String.valueOf(pageI);
                    page = pageS;
                    //将新的数据保存在本地的LitePal中
                    LitePalFactory.getLitePalProxy().setScenicLitePal(data);
                    //将page保存在本地中
                    //FileFactory.getFileFactory().setFile(pageS, Scenic_Spot.this);
                    SharedPreferences.Editor editor = getSharedPreferences("page",MODE_PRIVATE).edit();
                    editor.putString("page",pageS);
                    editor.apply();
                    //合并数据
                    resultList.addAll(data);
                    handler.post(new Runnable() {//3.显示在UI上
                        @Override
                        public void run() {
                            Toast.makeText(Scenic_Spot.this, "加载数据成功", Toast.LENGTH_SHORT).show();
                            myAdapterScenic.addData(resultList);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Scenic_Spot.this, "请求数据失败!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    public boolean isNetworkAvailable(){//判断是否联网x
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null){
            return false;
        }
        if (networkInfo.getType() == connectivityManager.TYPE_WIFI){
            return true;
        }
        if (networkInfo.getType() == connectivityManager.TYPE_MOBILE){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backhome:
                finish();
                break;
            case R.id.weather_right:
                Intent intent = new Intent(Scenic_Spot.this, WeatherActivity.class);
                intent.putExtra("choose","1");
                intent.putExtra("province", getIntent().getStringExtra("province"));
                intent.putExtra("city",getIntent().getStringExtra("city"));
                startActivity(intent);
                break;
            case R.id.city_button:
                Intent intent1 = new Intent(Scenic_Spot.this, ChooseCityActivity.class);
                startActivityForResult(intent1, 1);
                break;
            case R.id.lookfor:
                String getcity = input_city.getText().toString().trim();
                cityButton.setText(getcity);
                weatherText.setText(getcity);
                showInter(getcity);
            default:
                break;
        }
    }
}
