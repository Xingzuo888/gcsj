package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/12/22.
 */

public class PoiInfos { //周边交通和位置

    @SerializedName("subPoiInfos")
    public List<SubPoiInfos> subPoiInfosList; //poi信息

    @SerializedName("name")
    public String name; //poi类型名称

    @SerializedName("type")
    public String type; //类型

}
