package com.example.gcsj3.gson.hotel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 */

public class FilterProsList { //筛选条件的详细信息列表

    @SerializedName("poiName")
    public String poiName; //详细信息名字

    @SerializedName("poiKey")
    public String poiKey; //详细信息标识

    @SerializedName("filter")
    public List<SubProsList> subProsLists; //详细信息下子信息列表
}
