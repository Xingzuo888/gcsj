package com.example.gcsj3.gson.hotel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/21.
 */

public class ShowapiResBodyHotel {

        @SerializedName("cityName")
        public String cityName; //城市名字

        @SerializedName("remark")
        public String remark; //返回说明

        @SerializedName("data")
        public HotelData hotelData; //酒店的数据

        @SerializedName("ret_code")
        public String ret_code; //0为成功、其余为失败

}
