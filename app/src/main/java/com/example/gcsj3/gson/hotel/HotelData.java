package com.example.gcsj3.gson.hotel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 */

public class HotelData { //酒店的数据

    @SerializedName("hotelList")
    public List<HotelList> hotelLists; //酒店列表

    @SerializedName("count")
    public String count; //酒店数量

    @SerializedName("filter")
    public List<FilterInfo> filterInfos; //当returnFilter=1时才会存在，表示筛选条件
}
