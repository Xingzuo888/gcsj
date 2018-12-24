package com.example.gcsj3.gson.hotel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 */

public class FilterInfo { //筛选条件信息

    @SerializedName("filterName")
    public String filterName; //筛选条件的说明

    @SerializedName("filterId")
    public String filterId; //筛选条件的标识

    @SerializedName("pros")
    public List<FilterProsList> filterProsLists; //筛选条件的详细信息列表
}
