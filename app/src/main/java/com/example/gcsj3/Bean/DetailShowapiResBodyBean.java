package com.example.gcsj3.Bean;

import org.litepal.crud.DataSupport;

public class DetailShowapiResBodyBean extends DataSupport{
    private String msg = "";//返回说明
    private int ret_code = -1;//接口调用是否成功,0:扣费，表示成功,-1:不扣费，表示查询失败
    private String glocation = "";//谷歌经纬度
    private String scenicName = "";//景点名称
    private String trafficBus = "";//交通信息
    private int scenicId = 0;//景点ID
    private String recommend = "";//推荐
    private String cityName = "";//景点所在城市
    private String scenicAddress = "";//景点地址
    private String scenicDescription = "";//景点描述（为html）
    private String openTime = "";//景点营业时间
    private String defaultPic = "";//景点图片
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getGlocation() {
        return glocation;
    }

    public void setGlocation(String glocation) {
        this.glocation = glocation;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getTrafficBus() {
        return trafficBus;
    }

    public void setTrafficBus(String trafficBus) {
        this.trafficBus = trafficBus;
    }

    public int getScenicId() {
        return scenicId;
    }

    public void setScenicId(int scenicId) {
        this.scenicId = scenicId;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getScenicAddress() {
        return scenicAddress;
    }

    public void setScenicAddress(String scenicAddress) {
        this.scenicAddress = scenicAddress;
    }

    public String getScenicDescription() {
        return scenicDescription;
    }

    public void setScenicDescription(String scenicDescription) {
        this.scenicDescription = scenicDescription;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getDefaultPic() {
        return defaultPic;
    }

    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }
}

