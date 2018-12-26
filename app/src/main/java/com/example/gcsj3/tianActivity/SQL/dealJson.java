package com.example.gcsj3.tianActivity.SQL;

import android.util.Log;

import com.example.gcsj3.tianActivity.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class dealJson {

   public List<User> findAll(String data) {
      List<User> userList = null;
      try {
         JSONArray jsonArray = new JSONArray(data);
         userList = new ArrayList<>();
         for (int i = 0; i < jsonArray.length(); i++){
            User user = new User();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has("idcard")){
               user.setIdcard(jsonObject.getString("idcard"));
            }
            if (jsonObject.has("username")){
               user.setUsername(jsonObject.getString("username"));
            }
            if (jsonObject.has("pwd")){
               user.setPwd(jsonObject.getString("pwd"));
            }
            userList.add(user);
         }
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return userList;
   }

   public User findById(String data) {
      User user = null;
      try {
         JSONObject jsonObject = new JSONObject(data);
         user = new User();
         if (jsonObject.has("idcard")){
            user.setIdcard(jsonObject.getString("idcard"));
         }
         if (jsonObject.has("username")){
            user.setUsername(jsonObject.getString("username"));
         }
         if (jsonObject.has("pwd")){
            user.setPwd(jsonObject.getString("pwd"));
         }
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return user;
   }

   public List<User> findByName(String data) {
      List<User> userList = null;
      try {
         JSONArray jsonArray = new JSONArray(data);
         userList = new ArrayList<>();
         for (int i = 0; i < jsonArray.length(); i++){
            User user = new User();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Log.i("MainSQL", jsonObject.toString());
            if (jsonObject.has("idcard")){
               Log.i("MainSQL", "data" + jsonObject.getString("idcard"));
               user.setIdcard(jsonObject.getString("idcard"));
            }
            if (jsonObject.has("username")){
               user.setUsername(jsonObject.getString("username"));
            }
            if (jsonObject.has("pwd")){
               user.setPwd(jsonObject.getString("pwd"));
            }
            if (user != null){
               userList.add(user);
            }
         }
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return userList;
   }

   public boolean updatePwd(String user) {
      if (user.trim().equals("true"))
      {
         return true;
      }
      return false;
   }

   public boolean addUser(String user) {
      Log.i("MainDeal update", user);
      if (user.trim().equals("true"))
      {
         Log.i("MainDeal update", "true");
         return true;
      }
      return false;
   }
}
