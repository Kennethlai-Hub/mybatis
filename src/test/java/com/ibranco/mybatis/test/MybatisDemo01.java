package com.ibranco.mybatis.test;

import com.ibranco.mybatis.dao.UserDao;
import com.ibranco.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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

public class MybatisDemo01 {
    private Logger logger = Logger.getLogger(MybatisDemo01.class);
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void init() throws IOException {

        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();

    }
    @Test
    public void test() {
        List<User> userList = userDao.findAll();
        for (User user :userList){
            System.out.println(user);
            logger.info(user);
        }
    }

    @Test
    public void testSaveUser() throws IOException {
        User user = new User();
        user.setUsername("lai");
        user.setPassword("654321");
        user.setName("kenneth");
        user.setSex(1);
        user.setAge(20);
        user.setCredate(new Date());
        userDao.saveUser(user);
    }
}
