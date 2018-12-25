package com.example.gcsj3.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.gcsj3.MyApplication;
import com.example.gcsj3.gson.hotel.ShowapiResBodyHotel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/20.
 */

public class HttpUtil {
    public static void SendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

//    public static ShowapiResBodyHotel SendOkHttpRequest(String address) {
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(address).build();
//            Response response = client.newCall(request).execute();
//            String responseText = response.body().string();
//            if (responseText != null) {
//                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
//                editor.putString("showapihotel",responseText);
//                editor.apply();
//                return Utility.handleShowApiHotelResponse(responseText);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
