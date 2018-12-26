package com.example.gcsj3.Parse;

import com.example.gcsj3.Bean.ResultBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenicJSONResultBean {

    public List<ResultBean> dealJSON(String jsonData){
        List<ResultBean> list = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String getBoay = jsonObject.getString("showapi_res_body");
            JSONObject object = new JSONObject(getBoay);
            if (jsonObject.getString("showapi_res_code").equals("0") && object.getString("ret_code").equals("0")){//接口请求成功
                String getResult = object.getString("result");
                JSONArray resultArray = new JSONArray(getResult);
                for (int i = 0; i < resultArray.length(); i++){//将获得的GSON——Reulst数据添加到List中返回
                    JSONObject result = resultArray.getJSONObject(i);
                    list.add(isHasObject(result));
                }
            }else{
                list = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ResultBean isHasObject(JSONObject result) {
        ResultBean bean = new ResultBean();
        try{
            if (result.has("glocation")){
                bean.setGlocation(result.getString("glocation"));
            }
            if (result.has("scenicName")){
                bean.setScenicName(result.getString("scenicName"));
            }
            if (result.has("salePrice")){
                bean.setSalePrice(result.getInt("salePrice"));
            }
            if (result.has("blocation")){
                bean.setBlocation(result.getString("blocation"));
            }
            if (result.has("address")){
                bean.setAddress(result.getString("address"));
            }
            if (result.has("bizTime")){
                Pattern pattern = Pattern.compile("\\d{2}:\\d{2}—\\d{2}:\\d{2}");
                Matcher m = pattern.matcher(result.getString("bizTime"));
                String time = "";
                while(m.find()){
                    time = m.group();
                }
                bean.setBizTime(time);
            }
            if (result.has("newPicUrl")){
                bean.setNewPicUrl(result.getString("newPicUrl"));
            }
            if (result.has("scenicId")){
                bean.setScenicId(result.getString("scenicId"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }
}
