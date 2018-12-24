package com.example.gcsj3.gson.hotelcity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/12/24.
 */

public class HotelCity {

    @SerializedName("remark")
    public String remark; //返回说明

    @SerializedName("ret_code")
    public String ret_code;//0为成功、其余为失败

    @SerializedName("cityNameList")
    public String[] cityNameList; //返回支持的城市

}
