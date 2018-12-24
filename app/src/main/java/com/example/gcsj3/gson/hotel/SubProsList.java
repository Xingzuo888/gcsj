package com.example.gcsj3.gson.hotel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/21.
 */

public class SubProsList { //详细信息下子信息列表

    @SerializedName("longitude")
    public String longitude; //经度

    @SerializedName("code")
    public String code; //标识

    @SerializedName("hotelCount")
    public String hotelCount; //酒店数量

    @SerializedName("name")
    public String name; //酒店名称或者地点名称【根据参数位置定义】

    @SerializedName("heat")
    public String heat; //0为成功、其余为失败

    @SerializedName("latitude")
    public String latitude; //纬度
}
