package com.example.gcsj3.tianActivity.SQL;

import com.example.gcsj3.tianActivity.bean.User;

import java.util.List;

public interface SqlInter {
    public List<User> findAll();
    public User findById(String id);
    public List<User> findByName(String name);
    public boolean updatePwd(User user);
    public boolean addUser(User user);
}
