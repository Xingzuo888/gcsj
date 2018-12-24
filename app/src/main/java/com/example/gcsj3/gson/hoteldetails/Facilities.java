package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/22.
 */

public class Facilities { //酒店设备
    @SerializedName("code")
    public String code; //设备标识

    @SerializedName("name")
    public String name; //设备名称

    @SerializedName("typeName")
    public String typeName; //设备类型名

    @SerializedName("typeCode")
    public String typeCode; //设备类型标志

    @SerializedName("status")
    public String status;
}
