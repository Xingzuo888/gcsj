package com.example.gcsj3.LitePal;

import com.example.gcsj3.Bean.ResultBean;

import org.litepal.crud.DataSupport;

import java.util.List;

public class getScenicFromLitePal {
    public List<ResultBean> getScenic(){
        return DataSupport.limit(20).find(ResultBean.class);
    }
}
