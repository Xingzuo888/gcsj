package com.example.gcsj3.Bean;

import java.util.List;

public class ScenicShowapiResBodyBean {

    private String msg = "";//返回说明
    private int totalCount = 0;//总数量
    private int ret_code = 0;//接口调用是否成功,0:扣费，表示成功,-1:不扣费，表示查询失败
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
}
