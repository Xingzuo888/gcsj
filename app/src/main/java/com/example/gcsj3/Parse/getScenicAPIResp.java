package com.example.gcsj3.Parse;

import com.show.api.ShowApiRequest;

public class getScenicAPIResp extends getAPIRes{

    public getScenicAPIResp(String Url) {
        super(Url);
    }

    public getScenicAPIResp(String Url, String appId, String appSecret) {
        super(Url, appId, appSecret);
    }

    public String getAPIRespString(String city, String page, String pageSize){
        StringBuilder sb = new StringBuilder();
        ShowApiRequest api = new ShowApiRequest(super.Url, super.appId,super.appSecret);
        api.addTextPara("key", city)
                .addTextPara("page", page)
                .addTextPara("pageSize", pageSize);
        String res = api.post();
        sb.append(res);
        return sb.toString();
    }
}
