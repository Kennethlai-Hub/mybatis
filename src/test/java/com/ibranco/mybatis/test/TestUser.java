package com.ibranco.mybatis.test;

import com.ibranco.mybatis.dao.UserDao;
import com.ibranco.mybatis.dao.impl.UserDaoImpl;
import com.ibranco.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestUser {

    private Logger logger = Logger.getLogger(MybatisDemo01.class);
    private InputStream inputStream;
    private UserDao userDao;

    @Before
    public void init() throws IOException {

        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        userDao = new UserDaoImpl(sqlSessionFactory);
    }
    @After
    public void destory() throws IOException {

        inputStream.close();

    }
    @Test
    public void findAll(){
        List<User> userList = userDao.findAll();
        for(User user:userList){
            logger.info(user);
        }
    }
    @Test
    public void saveUser() {
         User user = new User();
         user.setUsername("abc");
         user.setPassword("888888");
         user.setName("box");
         user.setAge(30);
         user.setSex(0);
         user.setCredate(new Date());
         userDao.saveUser(user);
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(7);
        user.setUsername("889");
        user.setPassword("3166");
        user.setName("king");
        user.setAge(32);
        user.setSex(1);
        user.setCredate(new Date());
        userDao.updateUser(user);

    }
    @Test
    public void deleteById(){
        userDao.deleteById(7);
    }
}
