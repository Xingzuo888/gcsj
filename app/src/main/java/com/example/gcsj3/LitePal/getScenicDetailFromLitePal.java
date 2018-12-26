package com.example.gcsj3.LitePal;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;

import org.litepal.crud.DataSupport;

import java.util.List;


public class getScenicDetailFromLitePal {
    public List<DetailShowapiResBodyBean> getScenic(){
        return DataSupport.findAll(DetailShowapiResBodyBean.class);
    }

}
