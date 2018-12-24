package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/22.
 */

public class PicturesList { //图片列表
    @SerializedName("path")
    public String path; //图片存储路径。

    @SerializedName("name")
    public String name; //图片名称
}
