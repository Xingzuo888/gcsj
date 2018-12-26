package com.example.gcsj3.Parse;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;
import com.example.gcsj3.Bean.ResultBean;

import java.util.List;

/**
 * Created by tangy on 2018/12/23.
 */

public class JSONProxy implements JSONInter {
    private getScenicAPIResp getScenic = new getScenicAPIResp("http://route.showapi.com/1681-1");
    private getScenicDetailAPIResp getScenicDetail = new getScenicDetailAPIResp("http://route.showapi.com/1681-2");
    private ScenicJSONResultBean JSONScenic = new ScenicJSONResultBean();
    private ScenicDetailJSONResBody JSONScenicDetail = new ScenicDetailJSONResBody();
    @Override
    public String getAPIRespString(String city, String page, String pageSize) {
        return getScenic.getAPIRespString(city, page, pageSize);
    }

    @Override
    public String getAPIRespDetailString(String scenicId) {
        return getScenicDetail.getAPIRespString(scenicId);
    }

    @Override
    public DetailShowapiResBodyBean dealJSONDetail(String jsonData) {
        return JSONScenicDetail.dealJSON(jsonData);
    }

    @Override
    public List<ResultBean> dealJSON(String jsonData) {
        return JSONScenic.dealJSON(jsonData);
    }
}
