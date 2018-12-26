package com.example.gcsj3.Parse;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;
import com.example.gcsj3.Bean.ResultBean;

import java.util.List;

/**
 * Created by tangy on 2018/12/23.
 */

public interface JSONInter {
    public String getAPIRespString(String city, String page, String pageSize);
    public String getAPIRespDetailString(String scenicId);
    public DetailShowapiResBodyBean dealJSONDetail(String jsonData);
    public List<ResultBean> dealJSON(String jsonData);
}
