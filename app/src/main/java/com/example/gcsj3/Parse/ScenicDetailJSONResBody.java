package com.example.gcsj3.Parse;

import com.example.gcsj3.Bean.DetailShowapiResBodyBean;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenicDetailJSONResBody {
    public DetailShowapiResBodyBean dealJSON(String jsonData){
        DetailShowapiResBodyBean bodyBean = null;
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            if (jsonObject.getString("showapi_res_code").equals("0")){//接口请求成功
                String sBody = jsonObject.getString("showapi_res_body");
                //分开处理bookNotice和body
                JSONObject body = new JSONObject(sBody);
                bodyBean = isHasObject(body);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bodyBean;
    }

    public DetailShowapiResBodyBean isHasObject(JSONObject body){
        DetailShowapiResBodyBean bodyBean = new DetailShowapiResBodyBean();
        try{
            //处理body
            if (body.has("glocation")){
                bodyBean.setGlocation(body.getString("glocation"));
            }
            if (body.has("scenicName")){
                bodyBean.setScenicName(body.getString("scenicName"));
            }
            if (body.has("trafficBus")){
                bodyBean.setTrafficBus(body.getString("trafficBus"));
            }
            if (body.has("recommend")){
                bodyBean.setRecommend(body.getString("recommend"));
            }
            if (body.has("cityName")){
                bodyBean.setCityName(body.getString("cityName"));
            }
            if (body.has("scenicAddress")){
                bodyBean.setScenicAddress(body.getString("scenicAddress"));
            }
            if (body.has("openTime")){
                Pattern pattern = Pattern.compile("\\d{2}:\\d{2}—\\d{2}:\\d{2}");
                Matcher m = pattern.matcher(body.getString("openTime"));
                String time = "";
                while(m.find()){
                    time = m.group();
                }
                bodyBean.setOpenTime(time);
            }
            if (body.has("defaultPic")){
                bodyBean.setDefaultPic(body.getString("defaultPic"));
            }
            if (body.has("scenicDescription")){
                bodyBean.setScenicDescription(body.getString("scenicDescription"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bodyBean;
    }
}
