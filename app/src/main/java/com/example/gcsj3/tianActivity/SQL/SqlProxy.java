package com.example.gcsj3.tianActivity.SQL;

import android.util.Log;

import com.example.gcsj3.tianActivity.bean.User;

import java.util.List;

public class SqlProxy implements SqlInter {
    DaoConnection dao = null;
    dealJson deal = null;
    public SqlProxy(){
        dao = new DaoConnection();
        deal = new dealJson();
    }

    @Override
    public List<User> findAll()
    {
        String data = dao.findAll();
        return deal.findAll(data);
    }

    @Override
    public User findById(String id) {
        String data = dao.findById(id);
        return deal.findById(data);
    }

    @Override
    public List<User> findByName(String name) {
        String data = dao.findByName(name);
        return deal.findByName(data);
    }

    @Override
    public boolean updatePwd(User user) {
        String data = dao.updatePwd(user);
        return deal.updatePwd(data);
    }

    @Override
    public boolean addUser(User user) {
        String data = dao.addUser(user);
        Log.i("MainDeal upwd", data);
        return deal.addUser(data);
    }
}
