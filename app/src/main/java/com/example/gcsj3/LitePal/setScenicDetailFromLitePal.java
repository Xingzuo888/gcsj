package com.example.gcsj3.LitePal;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;

import java.util.List;

/**
 * Created by tangy on 2018/12/23.
 */

public class setScenicDetailFromLitePal {
    public void setScenic(List<DetailShowapiResBodyBean> beanList){
        for (DetailShowapiResBodyBean bean:beanList){
            bean.save();
        }
    }
}
