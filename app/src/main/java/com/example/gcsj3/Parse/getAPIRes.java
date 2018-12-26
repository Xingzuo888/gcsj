package com.example.gcsj3.Parse;

public class getAPIRes {
    public String Url;
    public String appId;
    public String appSecret;
    public getAPIRes(String Url){
        this.Url = Url;
        this.appId = "83457";
        this.appSecret = "cae6c679456647b3ba75a8b94221dbd4";
    }

    public getAPIRes(String Url, String appId, String appSecret){
        this.Url = Url;
        this.appId = appId;
        this.appSecret = appSecret;
    }
}
