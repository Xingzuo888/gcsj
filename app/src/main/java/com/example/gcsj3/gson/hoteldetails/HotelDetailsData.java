package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/12/22.
 */

public class HotelDetailsData {
    @SerializedName("englishName")
    public String englishName;

    @SerializedName("longitude")
    public String longitude; //经度

    @SerializedName("roomCount")
    public String roomCount; //房间数量

    @SerializedName("address")
    public String address; //酒店地址

    @SerializedName("pictures")
    public List<PicturesList> picturesList; //图片列表

    @SerializedName("services")
    public List<ServicesList> servicesList; //服务项目

    @SerializedName("tel")
    public String tel; //电话

    @SerializedName("latitude")
    public String latitude; //纬度

    @SerializedName("chineseName")
    public String chineseName;

    @SerializedName("debutYear")
    public String debutYear; //开业年份

    @SerializedName("policy")
    public Policy policy; //政策

    @SerializedName("poiInfos")
    public List<PoiInfos> poiInfosList; //周边交通和位置

    @SerializedName("instruction")
    public String instruction; //酒店介绍

    @SerializedName("facilities")
    public List<Facilities> facilitiesList; //酒店设备

    @SerializedName("star")
    public String star; //星级

    @SerializedName("decorateDate")
    public String decorateDate; //装修时间

    @SerializedName("starName")
    public String starName; //星级名称
}
