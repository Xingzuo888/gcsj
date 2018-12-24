package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/22.
 */

public class ServicesList {
    @SerializedName("code")
    public String code; //服务类型标志

    @SerializedName("name")
    public String name; //服务类型名称

    @SerializedName("typeName")
    public String typeName; //服务名

    @SerializedName("typeCode")
    public String typeCode; //服务标志

    @SerializedName("status")
    public String status; //服务状态

}
