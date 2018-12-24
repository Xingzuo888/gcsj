package com.example.gcsj3.util;

import com.example.gcsj3.gson.hotel.ShowapiResBodyHotel;
import com.example.gcsj3.gson.hotelcity.HotelCity;
import com.example.gcsj3.gson.hoteldetails.ShowapiResBodyHotelDetails;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/12/20.
 */

public class Utility { //该类用来处理返回的数据

    /**
     * 将返回的JSON数据解析成酒店的ShowapiResBodyHotel实体类
     */
    public static ShowapiResBodyHotel handleShowApiHotelResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("showapi_res_code") == 0) {
                String showapiResBodyContent = jsonObject.getString("showapi_res_body");
                return new Gson().fromJson(showapiResBodyContent, ShowapiResBodyHotel.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成酒店详情的ShowapiResBodyHotelDetails实体类
     */
    public static ShowapiResBodyHotelDetails handleShowApiHotelDetailsResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("showapi_res_code") == 0) {
                String showapiResBodyContent = jsonObject.getString("showapi_res_body");
                return new Gson().fromJson(showapiResBodyContent, ShowapiResBodyHotelDetails.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成支持酒店的城市HotelCity实体类
     */
    public static HotelCity handleHotelCityResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("showapi_res_code") == 0) {
                String showapiResBodyContent = jsonObject.getString("showapi_res_body");
                return new Gson().fromJson(showapiResBodyContent, HotelCity.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
