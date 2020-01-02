package com.ibranco.mybatis.dao;

import com.ibranco.mybatis.entity.QueryVo;
import com.ibranco.mybatis.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    void saveUser(User user);
    Integer updateUser(User user);
    User findByID(Integer id);
    Integer deleteById(Integer id);
    List<User> findByUserName(User user);
    List<User> findByUserName2(String user);
    Integer getCount();
    List<User> findByVo(QueryVo queryVo);
}
