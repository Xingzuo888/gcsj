package com.example.gcsj3.TangyActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gcsj3.Bean.ResultBean;
import com.example.gcsj3.LitePal.LitePalFactory;
import com.example.gcsj3.Parse.JSONFactory;
import com.example.gcsj3.R;
import com.example.gcsj3.Show.MyAdapterScenic;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static com.example.gcsj3.Parse.JSONFactory.getJSONFactory;

public class Scenic_Spot extends AppCompatActivity{

    public static String page = "1";
    private EditText input_city = null;
    private ListView iv = null;
    Handler handler = null;
    MyAdapterScenic myAdapterScenic = null;
    List<ResultBean> resultList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic__spot);
        input_city = (EditText) findViewById(R.id.input_city);
        iv = (ListView) findViewById(R.id.scenic_ListView);
        LitePal.getDatabase();
        //读取本地的page
        //page = FileFactory.getFileFactory().getFile(this);
        SharedPreferences preferences = getSharedPreferences("page", MODE_PRIVATE);
        page = preferences.getString("page", "0");
//        if (page == null||page == ""){//在第一次进入时
//            FileFactory.getFileFactory().setFile("1", this);
//            page = "0";
//        }
        //读取本地的Scenic信息
        List<ResultBean>  data= LitePalFactory.getLitePalProxy().getScenicLitePal();
        //合并数据
        resultList.addAll(data);

        handler = new Handler();
        myAdapterScenic = new MyAdapterScenic(this,resultList,handler);
        iv.setAdapter(myAdapterScenic);
        //判断是否有网络
        if (isNetworkAvailable()){
            showInter("成都");
        }else{
            Toast.makeText(this, "网络不给力", Toast.LENGTH_SHORT).show();
        }
    }

    public void showInter(final String city){

        //adapter
        new Thread(new Runnable() {
            @Override
            public void run() {
                //联网下
                //1.请求API接口数据
                int pageI = Integer.parseInt(page);
                pageI++;
                String pageS = String.valueOf(pageI);
                page = pageS;
                String Jsondata = getJSONFactory().getAPIRespString(city, pageS, "10");
                //2.处理数据
                List<ResultBean> data= JSONFactory.getJSONFactory().dealJSON(Jsondata);
                if (data != null){
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

    public void findCityScenic(View v){
        String getcity = input_city.getText().toString();
        showInter(getcity);
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
}
