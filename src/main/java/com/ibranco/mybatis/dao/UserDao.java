package com.ibranco.mybatis.dao;

import com.ibranco.mybatis.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    void saveUser(User user);
}
