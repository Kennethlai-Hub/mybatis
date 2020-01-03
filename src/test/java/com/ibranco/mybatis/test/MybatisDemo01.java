package com.ibranco.mybatis.test;

import com.ibranco.mybatis.dao.UserDao;
import com.ibranco.mybatis.entity.QueryVo;
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
        sqlSession = sqlSessionFactory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);
    }
    @After
    public void destory() throws IOException {
//        sqlSession.commit();
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
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(4);
        user.setUsername("laikenneth");
        user.setPassword("Kk888888");
        user.setName("idea");
        user.setSex(0);
        user.setAge(36);
        user.setCredate(new Date());
        Integer updateUser = userDao.updateUser(user);
        logger.info("结果行数："+updateUser);
    }

    @Test
    public void testFindById(){
        User user = userDao.findByID(4);
        logger.info(user);
    }
    @Test
    public void testDeleteById(){
        Integer deleteById = userDao.deleteById(4);
        logger.info("删除结果："+deleteById);
    }
    @Test
    public void testFindByUserName(){
        User user = new User();
        user.setUsername("kenneth");
        List<User> userList = userDao.findByUserName(user);
        for (User item:userList
             ) {
            logger.info(item);
        }
    }
    @Test
    public void testFindByUserName2(){
        List<User> userList = userDao.findByUserName2("lai");
        for (User item:userList
        ) {
            logger.info(item);
        }
    }

    @Test
    public void testGetCount(){
        Integer count = userDao.getCount();
        logger.info(count);
    }
    @Test
    public void testQueryVo(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("lai");
        queryVo.setUser(user);
        List<User> userList = userDao.findByVo(queryVo);
        for (User item:userList){
            logger.info(item);
        }
    }
}
