package com.example.gcsj3.gson.hotel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/21.
 */

public class HotelList {

    @SerializedName("englishName")
    public String englishName; //酒店英文名

    @SerializedName("hotelId")
    public String hotelId; //酒店ID

    @SerializedName("longitude")
    public String longitude; //地图经度

    @SerializedName("facilities")
    public String[] facilities; //设施 枚举如下： 1.免费wifi 2.餐厅 3.停车场 4.接机服务 5.免费洗漱用品 7.游泳池 8.健身房 9.会议室 10.SPA 12.24小时热水

    @SerializedName("address")
    public String address; //酒店地址

    @SerializedName("latitude")
    public String latitude; //地图纬度

    @SerializedName("price")
    public String price; //价格

    @SerializedName("chineseName")
    public String chineseName;  //酒店中文名

    @SerializedName("star")
    public String star; //星级

    @SerializedName("picture")
    public String picture; //图片

    @SerializedName("starName")
    public String starName; //星级名称

}
