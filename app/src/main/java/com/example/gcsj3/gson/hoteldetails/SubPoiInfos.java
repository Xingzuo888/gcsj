package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/24.
 */

public class SubPoiInfos {
    @SerializedName("name")
    public String name; //poi名称

    @SerializedName("trafficeDesc")
    public String trafficeDesc; //poi描述

    @SerializedName("distance")
    public String distance; //距离酒店多少公里
}
