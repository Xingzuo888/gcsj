package com.example.gcsj3.gson.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/12.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
