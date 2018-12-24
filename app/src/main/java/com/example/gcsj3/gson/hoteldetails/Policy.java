package com.example.gcsj3.gson.hoteldetails;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/12/22.
 */

public class Policy { //政策 数组
    @SerializedName("children")
    public String children;

    @SerializedName("pet")
    public String pet; //宠物

    @SerializedName("arrivalDeparture")
    public String arrivalDeparture; //入住与离店时间

    @SerializedName("requirements")
    public String requirements; //支付信息

    @SerializedName("depositPrepaid")
    public String depositPrepaid; //出示证件

    @SerializedName("checkOutTime")
    public String checkOutTime; //办理离店时间

    @SerializedName("checkInTime")
    public String checkInTime; //办理入住时间

    @SerializedName("cancel")
    public String cancel; //客房政策

    @SerializedName("acceptCreditCards")
    public String acceptCreditCards;
}
