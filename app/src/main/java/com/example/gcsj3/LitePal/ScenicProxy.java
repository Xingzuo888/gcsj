package com.example.gcsj3.LitePal;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;
import com.example.gcsj3.Bean.ResultBean;

import java.util.List;

public class ScenicProxy implements LitePalInfer {
    private getScenicDetailFromLitePal getScenicDetail = new getScenicDetailFromLitePal();
    private getScenicFromLitePal getScenic = new getScenicFromLitePal();
    private setScenicDetailFromLitePal setScenicDetail = new setScenicDetailFromLitePal();
    private setScenicFromLitePal setScenic = new setScenicFromLitePal();
    @Override
    public List<DetailShowapiResBodyBean> getScenicDetailLitePal() {
        return getScenicDetail.getScenic();
    }

    @Override
    public List<ResultBean> getScenicLitePal() {
        return getScenic.getScenic();
    }

    @Override
    public void setScenicDetailLitePal(List<DetailShowapiResBodyBean> beanList) {
        setScenicDetail.setScenic(beanList);
    }

    @Override
    public void setScenicLitePal(List<ResultBean> beanList) {
        setScenic.setScenic(beanList);
    }
}
