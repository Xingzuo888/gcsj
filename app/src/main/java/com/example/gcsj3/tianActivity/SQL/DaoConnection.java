package com.example.gcsj3.tianActivity.SQL;

import com.example.gcsj3.tianActivity.bean.User;
import com.example.gcsj3.util.StreamTools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DaoConnection {
    public static String ip = "http://10.0.10.23:8090/";
    public String findAll(){
        String url = ip + "getAllUser";//http://10.0.10.23:8090/getAllUser
        String content = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200){//请求成功
                InputStream in = conn.getInputStream();
                content = StreamTools.readString(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public String findById(String id){
        String url = ip + "findByid";
        String content = null;
        try {
            String data = "id=" + URLEncoder.encode(id, "utf-8");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();//打开链接
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置属性
            conn.setRequestProperty("Content-Length", data.length() + "");
            conn.setConnectTimeout(5000);
            conn.getOutputStream().write(data.getBytes());//添加请求的输入流
            int code = conn.getResponseCode();//获得请求的代码
            if (code == 200){//请求成功
                InputStream in = conn.getInputStream();
                content = StreamTools.readString(in);//把得到的数据流转化为STring类型
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public String findByName(String name){
        String url = ip + "findByName";
        String content = null;
        try {
            String data = "name=" + URLEncoder.encode(name, "utf-8");//添加name数据
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);//可以写入
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", data.length() + "");
            conn.setConnectTimeout(5000);
            conn.getOutputStream().write(data.getBytes());
            int code = conn.getResponseCode();
            if (code == 200){//请求成功
                InputStream in = conn.getInputStream();
                content = StreamTools.readString(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public String updatePwd(User user){
        String id = user.getIdcard();
        String pwd = user.getPwd();
        String url = ip + "updatePwd";
        String content = null;
        try {
            String data = "id=" + URLEncoder.encode(id, "utf-8") + "&pwd=" + URLEncoder.encode(pwd, "utf-8");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", data.length() + "");
            conn.setConnectTimeout(5000);
            conn.getOutputStream().write(data.getBytes());
            int code = conn.getResponseCode();
            if (code == 200){//请求成功
                InputStream in = conn.getInputStream();
                content = StreamTools.readString(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public String addUser(User user){
        String name = user.getUsername();
        String id = user.getIdcard();
        String pwd = user.getPwd();
        String url = ip + "addUser";
        String content = null;
        try {
            String data = "name=" + URLEncoder.encode(name, "utf-8") + "&id=" + URLEncoder.encode(id, "utf-8") + "&pwd=" + URLEncoder.encode(pwd, "utf-8");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", data.length() + "");
            conn.setConnectTimeout(5000);
            conn.getOutputStream().write(data.getBytes());
            int code = conn.getResponseCode();
            if (code == 200){//请求成功
                InputStream in = conn.getInputStream();
                content = StreamTools.readString(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
