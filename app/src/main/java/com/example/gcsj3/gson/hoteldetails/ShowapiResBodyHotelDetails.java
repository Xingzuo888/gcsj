package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/21.
 */

public class ShowapiResBodyHotelDetails {

        @SerializedName("data")
        public HotelDetailsData hotelDetailsData; //返回数据

        @SerializedName("remark")
        public String remark; //返回说明

        @SerializedName("updateTime")
        public String updateTime; //更新时间

        @SerializedName("ret_code")
        public String ret_code; //0为成功、其余为失败

}
