package com.example.gcsj3.LitePal;

import com.example.gcsj3.Bean.ResultBean;

import java.util.List;

public class setScenicFromLitePal {
    public void setScenic(List<ResultBean> beanList){
        for (ResultBean bean:beanList){
            bean.save();
        }
    }
}
