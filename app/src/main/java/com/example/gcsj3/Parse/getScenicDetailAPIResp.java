package com.example.gcsj3.Parse;

import com.show.api.ShowApiRequest;

public class getScenicDetailAPIResp extends getAPIRes{

    public getScenicDetailAPIResp(String Url) {
        super(Url);
    }

    public getScenicDetailAPIResp(String Url, String appId, String appSecret) {
        super(Url, appId, appSecret);
    }

    public String getAPIRespString(String scenicId){
        StringBuilder sb = new StringBuilder();
        ShowApiRequest api = new ShowApiRequest(super.Url, super.appId,super.appSecret);
        api.addTextPara("scenicId", scenicId);
        String res = api.post();
        sb.append(res);
        return sb.toString();
    }
}
