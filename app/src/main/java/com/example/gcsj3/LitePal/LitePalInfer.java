package com.example.gcsj3.LitePal;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;
import com.example.gcsj3.Bean.ResultBean;

import java.util.List;

/**
 * Created by tangy on 2018/12/23.
 */

public interface LitePalInfer {
    public List<DetailShowapiResBodyBean> getScenicDetailLitePal();
    public List<ResultBean> getScenicLitePal();
    public void setScenicDetailLitePal(List<DetailShowapiResBodyBean> beanList);
    public void setScenicLitePal(List<ResultBean> beanList);
}
