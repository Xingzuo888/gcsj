package com.example.gcsj3.gson.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/12.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
