package com.example.gcsj3.gson.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/12.
 */

public class Forecast {
    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;

    public class Temperature {
        public String max;
        public String mim;
    }

    public class More {
        @SerializedName("txt_d")
        public String info;
    }
}
